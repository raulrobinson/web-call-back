package co.com.telefonica.ws.dto.request;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RQCallBackNotificationDTO {

    @JsonSetter("_customer_number")
    private String customerNumber;

}
