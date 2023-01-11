package co.com.telefonica.ws.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestDTO {
    @NotNull
    private String customerNumber;
    @NotNull
    private String gvpzDocumento;
    @NotNull
    private String gvpzCuelgue;
    @NotNull
    private Long gvpzPostdiscado;
    @NotNull
    private String gvpzTipoCliente;
    @NotNull
    private String respuestaOne;
    @NotNull
    private String codigoSalida;
    @NotNull
    private String gvpzSuspension;
}
