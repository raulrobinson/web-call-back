package co.com.telefonica.ws.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InDTO {

    @NotNull
    private String customerNumber;

    @NotNull
    private String gvpzDocumento;

    @NotNull
    private String gvpzCuelgue;

    @NotNull
    private Long gvpzPostdiscado;

    @NotNull
    @Email
    private String gvpzTipoCliente;

    @NotNull
    private String respuestaOne;

    @NotNull
    private String codigoSalida;

    @NotNull
    private String gvpzSuspension;

    @NotNull
    private String gvpzIvrNavegacion;

    @NotNull
    private String setFijaAgent;

    @NotNull
    private String setDesiredtime;

    public void setDesiredtime(String setDesiredtime) {
        this.setDesiredtime = setDesiredtime;
    }
}
