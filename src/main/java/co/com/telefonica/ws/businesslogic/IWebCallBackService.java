package co.com.telefonica.ws.businesslogic;

import co.com.telefonica.ws.dto.request.RQNotifyCallBackDTO;
import co.com.telefonica.ws.dto.response.RSNotifyWebCallBackDTO;
import reactor.core.publisher.Mono;

public interface IWebCallBackService {
    Mono<RSNotifyWebCallBackDTO> sendNotification(RQNotifyCallBackDTO notifyCallBackDTO);
}
