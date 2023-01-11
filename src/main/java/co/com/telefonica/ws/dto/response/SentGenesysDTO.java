package co.com.telefonica.ws.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SentGenesysDTO {

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
}
