package co.com.telefonica.ws.businesslogic.impl;

import co.com.telefonica.ws.businesslogic.IWebCallBackService;
import co.com.telefonica.ws.dto.request.RQNotifyCallBackDTO;
import co.com.telefonica.ws.dto.response.RSNotifyWebCallBackDTO;
import co.com.telefonica.ws.ui.model.factory.SendNotificationFactory;
import co.com.telefonica.ws.ui.model.factory.send.ISendNotificationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class WebCallBackServiceImpl implements IWebCallBackService {

    @Autowired
    private SendNotificationFactory sendNotificationFactory;

    @Override
    public Mono<RSNotifyWebCallBackDTO> sendNotification(RQNotifyCallBackDTO request) {
        ISendNotificationFactory sendNotification = sendNotificationFactory.notificationFactory();
        sendNotification.sendNotify(request);

        return Mono.just(new RSNotifyWebCallBackDTO(request));
    }
}
