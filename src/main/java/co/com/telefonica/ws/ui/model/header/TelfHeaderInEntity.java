package co.com.telefonica.ws.ui.model.header;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.http.HttpHeaders;

import co.com.telefonica.ws.util.TelcoSecurityUtils;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TelfHeaderInEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String paramAu;
	private String system;
	private String operation;
	private String execId;
	private String timestamp;
	private String msgType;
	public TelfHeaderInEntity(HttpHeaders headers) {
		super();
		this.paramAu = TelcoSecurityUtils.blindParameter(headers.getFirst("authorization"));
		this.system = TelcoSecurityUtils.blindParameter(headers.getFirst("system"));
		this.operation = TelcoSecurityUtils.blindParameter(headers.getFirst("operation"));
		this.execId = TelcoSecurityUtils.blindParameter(headers.getFirst("execId"));
		this.timestamp = TelcoSecurityUtils.blindParameter(headers.getFirst("timestamp"));
		this.msgType = TelcoSecurityUtils.blindParameter(headers.getFirst("msgType"));
	}
}
