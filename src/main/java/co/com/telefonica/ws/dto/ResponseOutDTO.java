package co.com.telefonica.ws.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOutDTO {
    private String code;
    private String message;
    private Object content;
}
