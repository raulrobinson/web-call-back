package co.com.telefonica.ws.businesslogic.impl;

import co.com.telefonica.ws.businesslogic.WebcallbackService;
import co.com.telefonica.ws.dto.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class WebcallbackServiceImpl implements WebcallbackService {

    @Override
    public ResponseDTO createCallback(String customerNumber) {
        return null;
    }
}
