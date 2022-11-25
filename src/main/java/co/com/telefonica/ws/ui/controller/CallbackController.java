package co.com.telefonica.ws.ui.controller;

import co.com.telefonica.ws.businesslogic.WebcallbackService;
import co.com.telefonica.ws.dto.ResponseDTO;
import co.com.telefonica.ws.dto.WebcallbackInDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Clase encargada de mapear las operaciones verbos del servicio REST.
 *
 * @autor: COE-Arquitectura-Telefonica
 * @date: 19-10-2020
 * @version 2.0.0
 */
@Slf4j
@Service
@Validated
@RestController
@RequestMapping(path = "${controller.properties.base-path}", produces = MediaType.APPLICATION_JSON_VALUE)
public class CallbackController {

    @Autowired
    private WebcallbackService webcallbackService;

    @ApiOperation(value = "", notes = "")
    @PostMapping
    public ResponseEntity<ResponseDTO> createCallback(
            //@RequestHeader HttpHeaders headers,
            //@ApiParam("Información de la entidad para que se vea en swagger")
            //@RequestParam String customerNumber) {
            @Valid @RequestBody WebcallbackInDTO webcallbackInDTO){
        return new ResponseEntity<>(new ResponseDTO().builder().build(), HttpStatus.OK);
    }
}
