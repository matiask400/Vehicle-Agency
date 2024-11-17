import com.example.notificaciones.services.NotificacionService;
import com.example.notificaciones.services.PruebaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehiculos/configuracion")
public class VehiculoController {

    private final PruebaService pruebaService;
    private final NotificacionService notificacionService;

    public VehiculoController(PruebaService pruebaService, NotificacionService notificacionService) {
        this.pruebaService = pruebaService;
        this.notificacionService = notificacionService;
    }

    @PostMapping("/posicion")
    public ResponseEntity<String> recibirPosicion(@RequestBody DTOPosicion posicionDTO) {
        boolean estaDentroDeLimites = pruebaService.verificarPosicion(posicionDTO);

        if (!estaDentroDeLimites) {
            notificacionService.enviarNotificacion(posicionDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Posición fuera de los límites. Se envió una notificación.");
        }

        return ResponseEntity.ok("Posición dentro de los límites.");
    }
}
