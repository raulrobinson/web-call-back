package co.com.telefonica.ws.businesslogic.impl;

import co.com.telefonica.ws.businesslogic.ISendNotificationFactory;
import co.com.telefonica.ws.dto.request.RequestDTO;
import co.com.telefonica.ws.dto.response.ResponseDTO;
import co.com.telefonica.ws.dto.response.ResponseOutDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class GenesysNotificationClient implements ISendNotificationFactory {

    @Autowired
    private RestTemplate genesysClient;

    @Value("${url.notification.genesys}")
    private String url;

    @Override
    public ResponseEntity<ResponseOutDTO> sendNotify(RequestDTO request) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Go");

        var customerNumber = String.valueOf(request.getCustomerNumber());
        if (customerNumber.length() < 10) {
            var responseFail = ResponseOutDTO.builder()
                    .code("406 NOT_ACCEPTABLE")
                    .message("Error en la solicitud.")
                    .text("Los datos suministrados no se ajustan a la definicion.")
                    .build();
            return new ResponseEntity<>(responseFail, HttpStatus.NOT_ACCEPTABLE);
        }

        var requestQ = buildRequest(request);
        var requestEntity = new HttpEntity<>(requestQ, headers);
        var responseS = genesysClient.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ResponseDTO>() { });

        var responseF = ResponseOutDTO.builder()
                .code(responseS.getStatusCode().toString())
                .message("Numero: " + request.getCustomerNumber() + ", en breves instantes uno de nuestros asesores le contactara.")
                .text(responseS)
                .build();

        return new ResponseEntity<>(responseF, HttpStatus.OK);
    }

    private ResponseDTO buildRequest(RequestDTO request) {
        var requestW = new ResponseDTO();
        requestW.setCustomerNumber(request.toString());

        return requestW;
    }
}

