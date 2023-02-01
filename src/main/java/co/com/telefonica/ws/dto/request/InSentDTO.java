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
    private String customerNumber;      // Numero de Contacto. _customer_number
    @NotNull
    private String gvpzDocumento;       // Nombre del Cliente. gvpz_documento
    @NotNull
    private String gvpzCuelgue;         // Tipo de Documento. gvpz_cuelgue
    @NotNull
    private Long gvpzPostdiscado;       // Cedula del Cliente. gvpz_postdiscado
    @NotNull
    @Email
    private String gvpzTipoCliente;     // Correo e-mail. gvpz_tipo_cliente
    @NotNull
    private String respuestaOne;        // Tipo de producto que desea cancelar (Fija o Movil). Respuesta_1
    @NotNull
    private String gvpzSuspension;      // Tipologia de la solicitud (cancelacion). gvpz_suspension
    @NotNull
    private String codigoSalida;        // Para elegir el trunk de salida. codigosalida
    @NotNull
    private String gvpzIvrInicio;       // Ciudad. gvpz_ivr_inicio.
    @NotNull
    private String gvpzUltOpcion;       // Fecha Selección Categoría. gvpz_ult_opcion "2023-01-23T15:40:10.954Z"
    @NotNull
    private String gvpzLlamadaTransferida; // Fecha Selección Tipología. gvpz_llamada_transferida "2023-01-23T15:40:10.954Z"
}
