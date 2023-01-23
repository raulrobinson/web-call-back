package co.com.telefonica.ws.businesslogic.impl;

import co.com.telefonica.ws.businesslogic.ISendNotificationFactory;
import co.com.telefonica.ws.dto.request.InSentDTO;
import co.com.telefonica.ws.dto.response.SentGenesysDTO;
import co.com.telefonica.ws.dto.response.OutSentDTO;
import co.com.telefonica.ws.util.TelcoConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Slf4j
@Component
public class GenesysNotificationClient implements ISendNotificationFactory {

    @Autowired
    private RestTemplate genesysClient;

    @Value("${url.notification.genesys}")
    private String url;

    @Autowired
    TelcoConstants telcoConstants;

    private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Override
    public ResponseEntity<OutSentDTO> sendNotify(InSentDTO request) {

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "SolucionesAgiles");

        String errorMessage = "Error..";

        if (request.getCustomerNumber().isBlank()) {
            log.info("_customer_number: Customer number not found");
            return telcoConstants.responseFail("406", errorMessage, "_customer_number: Customer number not found..");

        } else if (request.getGvpzDocumento().isBlank()) {
            log.info("gvpz_documento: Customer name not found");
            return telcoConstants.responseFail("406", errorMessage, "gvpz_documento: Customer name not found..");

        } else if (request.getCodigoSalida().isBlank()) {
            log.info("codigosalida: Output code not found");
            return telcoConstants.responseFail("406", errorMessage, "codigosalida: Output code not found..");

        } else if (request.getGvpzCuelgue().isBlank()) {
            log.info("gvpz_cuelgue: Document type not found");
            return telcoConstants.responseFail("406", errorMessage, "gvpz_cuelgue: Document type not found..");

        } else if (request.getGvpzPostdiscado() == 0) {
            log.info("gvpz_postdiscado: Identification number client not found");
            return telcoConstants.responseFail("406", errorMessage, "gvpz_postdiscado: Identification number client not found..");

        } else if (request.getGvpzTipoCliente().isBlank()) {
            log.info("gvpz_tipo_cliente: Email customer not found");
            return telcoConstants.responseFail("406", errorMessage, "gvpz_tipo_cliente: Email customer not found..");

        } else if (request.getGvpzSuspension().isBlank()) {
            log.info("gvpz_suspension: Reason for procedure");
            return telcoConstants.responseFail("406", errorMessage, "gvpz_suspension: Reason for procedure not found..");

        } else if (request.getRespuestaOne().isBlank()) {
            log.info("respuesta_1: Fija or Movil not found");
            return telcoConstants.responseFail("406", errorMessage, "respuesta_1: Fija or Movil not found..");
        }

        var customerNumber = String.valueOf(request.getCustomerNumber());

        if (Objects.equals(request.getRespuestaOne(), "Movil")) {
            if (customerNumber.length() < 10) {
                return telcoConstants.responseFail("406", "Request Failed..", "Phone number invalid length..");
            }
        } else if (Objects.equals(request.getRespuestaOne(), "Fija")){
            customerNumber = String.valueOf(request.getCustomerNumber());
        } else {
            return telcoConstants.responseFail("406", "Request Failed..", "Phone format number invalid..");
        }

        log.info(customerNumber);
        log.info(formatDate(new Date()));

        var reqBody = buildRequest(request);
        var reqEntity = new HttpEntity<>(reqBody, headers);
        var resGenesys = genesysClient.exchange(url, HttpMethod.POST, reqEntity, new ParameterizedTypeReference<SentGenesysDTO>() { });

        var resOut = OutSentDTO.builder()
                .code(resGenesys.getStatusCode().toString())
                .message(request.getCustomerNumber() + ", In a few moments one of our agents will contact you..")
                .content(resGenesys)
                .build();

        return new ResponseEntity<>(resOut, HttpStatus.OK);
    }

    private Object buildRequest(InSentDTO request) {
        var req = new HashMap<String, String>();
        req.put("_customer_number", request.getCustomerNumber());
        req.put("gvpz_documento", request.getGvpzDocumento());
        req.put("gvpz_cuelgue", request.getGvpzCuelgue());
        req.put("gvpz_postdiscado", String.valueOf(request.getGvpzPostdiscado()));
        req.put("gvpz_tipo_cliente", request.getGvpzTipoCliente());
        req.put("Respuesta_1", request.getRespuestaOne()); // Línea Producto Solicitud
        req.put("gvpz_ivr_navegacion", "Tramite sobre mis productos");
        req.put("gvpz_suspension", request.getGvpzSuspension());
        req.put("codigosalida", request.getCodigoSalida());
        req.put("_desired_time", formatDate(new Date()));
        req.put("gvpz_ivr_inicio", "Barranquilla"); // Ciudad
        req.put("gvpz_ult_opcion", ""); // Fecha Selección Categoría
        req.put("gvpz_llamada_transferida", ""); // Fecha Selección Tipología
        req.put("FIJA_AGENT", "WCB_UNF"); // Siempre

        return req;
    }

    public String formatDate(Date date) {
        synchronized(DATE_FMT) {
            return DATE_FMT.format(date);
        }
    }

}

