package co.com.telefonica.ws.ui.model.factory.impl;

import co.com.telefonica.ws.dto.request.RQCallBackNotificationDTO;
import co.com.telefonica.ws.dto.request.RQNotifyCallBackDTO;
import co.com.telefonica.ws.dto.response.RSCreatedOrderDTO;
import co.com.telefonica.ws.dto.response.RSNotifyWebCallBackDTO;
import co.com.telefonica.ws.ui.model.factory.send.ISendNotificationFactory;
import co.com.telefonica.ws.util.WebCallBackValidatorUtil;
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

    @Autowired
    private WebCallBackValidatorUtil validator;

    @Value("${url.notification.genesys}")
    private String url;

    @Override
    public RSNotifyWebCallBackDTO sendNotify(RQNotifyCallBackDTO request) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RQCallBackNotificationDTO requestG = buildRequest(request);
        headers.set("Authorization", "Go");

        var requestEntity = new HttpEntity<>(requestG, headers);
        ResponseEntity<RSCreatedOrderDTO> responseWS = genesysClient.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<RSCreatedOrderDTO>() { });

        var orderStatus = validator.validateStatus(String.valueOf(responseWS.getStatusCodeValue()), request.getCustomerNumber());
        var order = validator.validateBody(responseWS, request.getCustomerNumber());

        log.info(orderStatus.toString());
        var res = new RSNotifyWebCallBackDTO();
        res.setDialogId(order.getCustomerNumber());
        log.info(order.getCustomerNumber());

        return res;
    }

    private RQCallBackNotificationDTO buildRequest(RQNotifyCallBackDTO request) {
        RQCallBackNotificationDTO requestW = new RQCallBackNotificationDTO();
        requestW.setCustomerNumber(request.getCustomerNumber());

        return requestW;
    }
}

