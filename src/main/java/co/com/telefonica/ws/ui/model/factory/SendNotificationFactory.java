package co.com.telefonica.ws.ui.model.factory;

import co.com.telefonica.ws.ui.model.factory.impl.GenesysNotificationClient;
import co.com.telefonica.ws.ui.model.factory.send.ISendNotificationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendNotificationFactory {

    @Autowired
    private GenesysNotificationClient genesysNotificationClient;

    public ISendNotificationFactory notificationFactory() {
        return genesysNotificationClient;
    }
}
