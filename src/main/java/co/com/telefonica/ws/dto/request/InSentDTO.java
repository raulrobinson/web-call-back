package co.com.telefonica.ws.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InSentDTO {

    @NotNull
    private String customerNumber;      // Numero de Contacto.

    @NotNull
    private String gvpzDocumento;       // Nombre del Cliente.

    @NotNull
    private String gvpzCuelgue;         // Tipo de Documento.

    @NotNull
    private Long gvpzPostdiscado;       // Cedula del Cliente.

    @NotNull
    @Email
    private String gvpzTipoCliente;     // Correo e-mail.

    @NotNull
    private String respuestaOne;        // Tipo de producto que desea cancelar (fijo o movil).

    @NotNull
    private String gvpzIvrNavegacion;   // Categoria de tramite (tramite sobre mis productos).

    @NotNull
    private String gvpzSuspension;      // Tipologia de la solicitud (cancelacion).

    private String desiredtime;         // Fecha de la solicitud.

    @NotNull
    private String codigoSalida;        // Para elegir el trunk de salida.

    @NotNull
    private String fijaAgent;           // Para reporteria y debe tener el valor "WCB_UNF".

    @Override
    public String toString() {
        return "InSentDTO(" +
                "_customer_number='" + customerNumber + '\'' +
                ", gvpz_documento='" + gvpzDocumento + '\'' +
                ", gvpz_cuelgue='" + gvpzCuelgue + '\'' +
                ", gvpz_postdiscado=" + gvpzPostdiscado +
                ", gvpz_tipo_cliente='" + gvpzTipoCliente + '\'' +
                ", respuesta_1='" + respuestaOne + '\'' +
                ", gvpz_ivr_navegacion='" + gvpzIvrNavegacion + '\'' +
                ", gvpz_suspension='" + gvpzSuspension + '\'' +
                ", _desired_time='" + desiredtime + '\'' +
                ", codigosalida='" + codigoSalida + '\'' +
                ", FIJA_AGENT='" + fijaAgent + '\'' +
                ')';
    }
}
