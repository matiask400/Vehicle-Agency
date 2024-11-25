package ar.edu.utn.frc.bda.services;

import ar.edu.utn.frc.bda.config.Configuracion;
import ar.edu.utn.frc.bda.config.ZonaRestringida;
import ar.edu.utn.frc.bda.models.*;
import ar.edu.utn.frc.bda.repositories.PosicionesCustomDAO;
import ar.edu.utn.frc.bda.repositories.PosicionesDAO;
import ar.edu.utn.frc.bda.repositories.PruebasDAO;
import ar.edu.utn.frc.bda.repositories.VehiculosDAO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PosicionesService {

    private final VehiculosDAO vehiculoRepository;
    private final PosicionesDAO posicionRepository;
    private final ConfiguracionService configuracionService;
    private final PruebasDAO pruebaRepository;
    private final NotificacionService notificacionService;
    private final PosicionesCustomDAO posicionesCustomDAO;

    public PosicionesService(VehiculosDAO vehiculoRepository,
                           PosicionesDAO posicionRepository,
                           ConfiguracionService configuracionService,
                           PruebasDAO pruebaRepository,
                           NotificacionService notificacionService,
                           PosicionesCustomDAO posicionesCustomDAO) {
        this.vehiculoRepository = vehiculoRepository;
        this.posicionRepository = posicionRepository;
        this.configuracionService = configuracionService;
        this.pruebaRepository = pruebaRepository;
        this.notificacionService = notificacionService;
        this.posicionesCustomDAO = posicionesCustomDAO;
    }


    @Transactional
    public Posiciones crearNuevaPosicion(Long id_vehiculo, Double longitud, Double latitud) {
        try {
            Vehiculos vehiculo = vehiculoRepository.findByID(id_vehiculo);

            LocalDateTime fechaActual = LocalDateTime.now();

            //BUSCAR LA PREUBA ASOCIADA AL VEHICULO
            Pruebas prueba = pruebaRepository.findPruebaActivaByVehiculo(id_vehiculo);
            Long numEmpleado = prueba.getEmpleado().getTelefonoContacto();

            if (prueba != null){
                System.out.println("EL VEHICULO ESTA SIENDO PROBADO");
            } else {
                System.out.println("EL VEHICULO NO ESTA SIENDO PROBADO");
                return null;
            }

            Interesados interesado = prueba.getInteresado();
            Configuracion configuracion = configuracionService.obtenerConfiguracion();

            Posiciones posicion = new Posiciones(vehiculo, fechaActual, longitud, latitud);
            System.out.println("SE CREO LA POSICION ANTES DE SER GUARDADO" + posicion);
            posicionRepository.save(posicion);


            if (estaDentroDelRadioAdmitido(posicion, configuracion) && !estaEnZonaRestringida(posicion, configuracion.getZonasRestringidas())) {
                System.out.println("ESTA DENTRO DE LO PERMITIDO");
            } else {
                // CUANDO PASA ALGUNAS DE ESTAS COSAS DEBO AGREGAR AL CLIENTE A LA LISTA DE CLIENTES RESTRINGIDOS
                // MANDAR NOTIFICACION
                if(estaDentroDelRadioAdmitido(posicion,configuracion)){
                    String mensaje = "EL VEHICULO: " + vehiculo.getPatente() + " DEBE REGRESAR INMEDIATAMENTE " + "TELEFONO : " + numEmpleado;
                    String tipo = "FUERA DE RADIO ADMITIDO";
                    Notificaciones notificacion = new Notificaciones(mensaje, tipo);
                    notificacionService.enviarNotificacion(notificacion);
                } else{
                    String mensaje = "EL VEHICULO: " + vehiculo.getPatente() + " DEBE REGRESAR INMEDIATAMENTE " + "TELEFONO : " + numEmpleado;
                    String tipo = "ZONA RESTRINGIDA";
                    Notificaciones notificacion = new Notificaciones(mensaje, tipo);
                    notificacionService.enviarNotificacion(notificacion);
                }

                System.out.println("NO ESTA DENTRO DE LO PERMITIDO");
                interesado.setRestringido(true);
                prueba.setInsidente(true);
            }

            return posicion;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean estaDentroDelRadioAdmitido(Posiciones posicion, Configuracion configuracion) {
        double distancia = posicionesCustomDAO.calcularDistancia(
                posicion.getLatitud(), posicion.getLongitud(),
                configuracion.getCoordenadasAgencia().getLat(),
                configuracion.getCoordenadasAgencia().getLon()
        );
        return distancia <= configuracion.getRadioAdmitidoKm();
    }

    private boolean estaEnZonaRestringida(Posiciones posicion, List<ZonaRestringida> zonasRestringidas) {
        for (ZonaRestringida zona : zonasRestringidas) {
            if (posicion.getLatitud() >= zona.getSureste().getLat() && posicion.getLatitud() <= zona.getNoroeste().getLat() &&
                    posicion.getLongitud() >= zona.getNoroeste().getLon() && posicion.getLongitud() <= zona.getSureste().getLon()) {
                return true;
            }
        }
        return false;
    }



    public String obtenerCantidadKilometros(Long idVehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        Double cantidadKilometros = posicionesCustomDAO.calcularDistanciaTotal(idVehiculo, fechaInicio, fechaFin);

        Vehiculos vehiculo = vehiculoRepository.findByID(idVehiculo);
        String patente = vehiculo.getPatente();

        StringBuilder reporte = new StringBuilder();
        reporte.append("REPORTE DE KILOMETROS PARA EL VEHICULO: " + patente  ).append("\n");
        reporte.append("Fecha Actual :").append(LocalDateTime.from(Instant.now())).append("\n\n");
        reporte.append("LA CANTIDAD DE KILOMETROS RECORRIDO EN PRUEBAS ES : " + cantidadKilometros);

        return reporte.toString();
    }


}
