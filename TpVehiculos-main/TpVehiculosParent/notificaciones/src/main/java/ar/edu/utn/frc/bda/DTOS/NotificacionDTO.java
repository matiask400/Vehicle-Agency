package ar.edu.utn.frc.bda.DTOS;


public class NotificacionDTO {
    private String mensaje;

    public NotificacionDTO(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public NotificacionDTO(){}
}
