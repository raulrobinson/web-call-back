package co.com.telefonica.ws.dto.request;

import com.fasterxml.jackson.annotation.JsonSetter;
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
    @JsonSetter("_customer_number")     // Numero de Contacto.
    private String customerNumber;

    @NotNull
    @JsonSetter("gvpz_documento")       // Nombre del Cliente.
    private String gvpzDocumento;

    @NotNull
    @JsonSetter("gvpz_cuelgue")         // Tipo de Documento.
    private String gvpzCuelgue;

    @NotNull
    @JsonSetter("gvpz_postdiscado")     // Cedula del Cliente.
    private Long gvpzPostdiscado;

    @NotNull
    @Email
    @JsonSetter("gvpz_tipo_cliente")    // Correo e-mail.
    private String gvpzTipoCliente;

    @NotNull
    @JsonSetter("respuesta_1")          // Tipo de producto que desea cancelar (fijo o movil).
    private String respuestaOne;

    @NotNull
    @JsonSetter("gvpz_ivr_navegacion")  // Categoria de tramite (tramite sobre mis productos).
    private String gvpzIvrNavegacion;

    @NotNull
    @JsonSetter("gvpz_suspension")      // Tipologia de la solicitud (cancelacion).
    private String gvpzSuspension;

    @JsonSetter("_desired_time")        // Fecha de la solicitud.
    private String desiredtime;

    @NotNull
    @JsonSetter("codigosalida")         // Para elegir el trunk de salida.
    private String codigoSalida;

    @NotNull
    @JsonSetter("FIJA_AGENT")           // Para reporteria y debe tener el valor "WCB_UNF".
    private String fijaAgent;
}
