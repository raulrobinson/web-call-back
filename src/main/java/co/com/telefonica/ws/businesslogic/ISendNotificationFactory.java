package co.com.telefonica.ws.businesslogic;

import co.com.telefonica.ws.dto.request.RequestDTO;
import co.com.telefonica.ws.dto.response.ResponseOutDTO;
import org.springframework.http.ResponseEntity;

public interface ISendNotificationFactory {
    ResponseEntity<ResponseOutDTO> sendNotify(RequestDTO request);
}
