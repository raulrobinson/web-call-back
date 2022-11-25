package co.com.telefonica.ws.businesslogic;

import co.com.telefonica.ws.dto.ResponseDTO;

public interface WebcallbackService {
    ResponseDTO createCallback(String customerNumber);
}
