package co.com.telefonica.ws.util;

import co.com.telefonica.ws.dto.response.OutSentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TelcoConstants {

    public ResponseEntity<OutSentDTO> responseFail(String code, String message, String content) {

        if (code.equals("406")) {
            return new ResponseEntity<>(OutSentDTO.builder()
                    .code(code)
                    .message(message)
                    .content(content)
                    .build(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(OutSentDTO.builder()
                .code(code)
                .message(message)
                .content(content)
                .build(), HttpStatus.OK);
    }
}
