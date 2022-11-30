package co.com.telefonica.ws.dto.response;

import co.com.telefonica.ws.dto.request.RQNotifyCallBackDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RSNotifyWebCallBackDTO {

    @JsonProperty("_customer_number")
    private String customerNumber;

    @JsonProperty("_id")
    private String id;

    @JsonProperty("_ok_title")
    private String okTitle;

    @JsonProperty("_action")
    private String action;

    @JsonProperty("_text")
    private String text;

    @JsonProperty("_dialog_id")
    private String dialogId;

    public RSNotifyWebCallBackDTO(RQNotifyCallBackDTO request) {
        this.customerNumber = request.getCustomerNumber();
    }

}
