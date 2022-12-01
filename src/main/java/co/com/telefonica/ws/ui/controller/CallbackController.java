package co.com.telefonica.ws.ui.controller;

import co.com.telefonica.ws.businesslogic.ISendNotificationFactory;
import co.com.telefonica.ws.dto.request.RequestDTO;
import co.com.telefonica.ws.dto.response.ResponseOutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "${controller.properties.base-path}")
public class CallbackController {

    private final ISendNotificationFactory sendNotification;

    @PostMapping
    public ResponseEntity<ResponseOutDTO> sendCallback(@RequestBody RequestDTO request) {
        if (null == request) {
            var responseError = ResponseOutDTO.builder()
                    .code("406 NOT_ACCEPTABLE")
                    .message("Error en la solicitud.")
                    .text("Datos invalidos.")
                    .build();
            return new ResponseEntity<>(responseError, HttpStatus.NOT_ACCEPTABLE);
        }

        return sendNotification.sendNotify(request);
    }
}
