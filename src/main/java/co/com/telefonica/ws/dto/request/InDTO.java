package co.com.telefonica.ws.dto.request;

import com.fasterxml.jackson.annotation.JsonSetter;
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
    @JsonSetter("respuesta_1")          // Tipo de producto que desea cancelar (Fija o Movil).
    private String respuestaOne;

    @NotNull
    @JsonSetter("codigosalida")         // Para elegir el trunk de salida.
    private String codigoSalida;
}
