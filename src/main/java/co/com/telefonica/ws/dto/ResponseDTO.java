package co.com.telefonica.ws.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private String _id;
    private String _ok_title;
    private String _action;
    private String _text;
    private String _dialog_id;
}
