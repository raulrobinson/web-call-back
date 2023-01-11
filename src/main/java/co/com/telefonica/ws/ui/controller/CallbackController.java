package co.com.telefonica.ws.ui.controller;

import co.com.telefonica.ws.businesslogic.ISendNotificationFactory;
import co.com.telefonica.ws.dto.request.InDTO;
import co.com.telefonica.ws.dto.response.OutSentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "${controller.properties.base-path}", produces = MediaType.APPLICATION_JSON_VALUE)
public class CallbackController {

    private final ISendNotificationFactory sendNotification;

    @PostMapping
    public ResponseEntity<OutSentDTO> sendCallback(
            @RequestBody InDTO request) {
        if (null == request) {
            var responseError = OutSentDTO.builder()
                    .code("406 NOT_ACCEPTABLE")
                    .message("Error en la solicitud.")
                    .content("Datos invalidos.")
                    .build();

            return new ResponseEntity<>(responseError, HttpStatus.NOT_ACCEPTABLE);
        }

        return sendNotification.sendNotify(request);
    }

}
