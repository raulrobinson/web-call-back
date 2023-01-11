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
    private String gvpzSuspension;      // Tipologia de la solicitud (cancelacion).

    @NotNull
    private String codigoSalida;        // Para elegir el trunk de salida.
}
