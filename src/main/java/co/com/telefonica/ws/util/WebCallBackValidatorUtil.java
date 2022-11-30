package co.com.telefonica.ws.util;

import co.com.telefonica.ws.dto.response.RSCreatedOrderDTO;
import co.com.telefonica.ws.exception.GlobalRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WebCallBackValidatorUtil {

    @Value("${msg.error.internal.204}")
    private String errorNotContent;

    @Value("${msg.error.internal.400}")
    private String errorBadRequest;

    @Value("${msg.error.internal.500}")
    private String errorInternal;

    public Object validateStatus(String code, String channel) {
        switch (code) {
            case "204":
                throw new GlobalRuntimeException(errorNotContent.concat(" ").concat(channel));
            case "400":
                throw new GlobalRuntimeException(errorBadRequest.concat(" ").concat(channel));
            case "200":
            case "202":
            case "201":
                log.info("Its OK");
                return "Code response: " + code;
            default:
                throw new GlobalRuntimeException(errorInternal.concat(" ").concat(channel));
        }
    }

    public RSCreatedOrderDTO validateBody(ResponseEntity<RSCreatedOrderDTO> response, String channel) {
        RSCreatedOrderDTO bodyResponse = (response.getBody() != null) ? response.getBody() : null;
        if (bodyResponse == null) {
            throw new GlobalRuntimeException(errorNotContent.concat(" ").concat(channel));
        }
        return  bodyResponse;
    }
}
