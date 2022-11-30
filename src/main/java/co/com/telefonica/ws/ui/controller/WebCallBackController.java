package co.com.telefonica.ws.ui.controller;

import co.com.telefonica.ws.businesslogic.IWebCallBackService;
import co.com.telefonica.ws.dto.request.RQNotifyCallBackDTO;
import co.com.telefonica.ws.dto.response.RSNotifyWebCallBackDTO;
import co.com.telefonica.ws.ui.model.enums.EResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("${controller.properties.base-path}")
public class WebCallBackController {

    @Autowired
    private IWebCallBackService generateCallBackService;

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> sendNotification(
            @Valid @RequestBody Mono<RQNotifyCallBackDTO> monoCallback) {
        return monoCallback.flatMap(callback ->
                generateCallBackService.sendNotification(callback)
                        .map(p ->
                                ResponseEntity.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(buildResponse(
                                                p, EResponseType.SUCCESS
                                                , "A phone call has been scheduled for number: " + callback.getCustomerNumber()
                                                , "201"
                                        ))
                        )
        ).onErrorResume(t ->
                Mono.just(t).cast(WebExchangeBindException.class)
                        .flatMap(e -> Mono.just(e.getFieldErrors()))
                        .flatMapMany(Flux::fromIterable)
                        .map(fieldError -> "The field ".concat(fieldError.getField()).concat(" ")
                                .concat(Objects.requireNonNull(fieldError.getDefaultMessage())))
                        .collectList()
                        .flatMap(list ->
                                Mono.just(ResponseEntity.badRequest()
                                        .body(buildResponse(new RSNotifyWebCallBackDTO()
                                                ,EResponseType.BAD_REQUEST
                                                ,list
                                                ,"400"
                                        ))
                                )
                        )
        );
    }

    private Map<String, Object> buildResponse(RSNotifyWebCallBackDTO rsCallBackDTO, EResponseType eResponseType
            , Object message, String code) {
        Map<String, Object> response = new HashMap<>();
        response.put("type", eResponseType);
        response.put("message", message);
        response.put("code", code);
        response.put("info", rsCallBackDTO);

        return response;
    }
}
