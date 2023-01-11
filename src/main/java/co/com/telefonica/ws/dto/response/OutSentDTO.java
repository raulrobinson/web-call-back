package co.com.telefonica.ws.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutSentDTO {
    private String code;
    private String message;
    private Object content;
}
