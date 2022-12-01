package co.com.telefonica.ws.dto.request;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    @NotNull
    @JsonSetter("_customer_number")
    private int customerNumber;

    @Override
    public String toString() {
        return "customerNumber='" + customerNumber;
    }
}
