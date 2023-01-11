package co.com.telefonica.ws.businesslogic;

import co.com.telefonica.ws.dto.request.InSentDTO;
import co.com.telefonica.ws.dto.response.OutSentDTO;
import org.springframework.http.ResponseEntity;

public interface ISendNotificationFactory {
    ResponseEntity<OutSentDTO> sendNotify(InSentDTO request);
}
