package co.com.telefonica.ws.ui.model.factory.send;


import co.com.telefonica.ws.dto.request.RQNotifyCallBackDTO;
import co.com.telefonica.ws.dto.response.RSNotifyWebCallBackDTO;

public interface ISendNotificationFactory {
    RSNotifyWebCallBackDTO sendNotify(RQNotifyCallBackDTO request);
}
