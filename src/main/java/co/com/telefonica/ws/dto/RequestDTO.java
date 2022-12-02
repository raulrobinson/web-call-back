package co.com.telefonica.ws.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    @NotNull
    @JsonSetter("_customer_number")
    private Long customerNumber;

    @Override
    public String toString() {
        return "customerNumber='" + customerNumber;
    }
}
