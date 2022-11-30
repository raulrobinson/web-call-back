package co.com.telefonica.ws.ui.model.header;

import java.io.Serializable;

import co.com.telefonica.ws.util.UtilHeader;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TelfHeaderOutEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String timestamp;
	private String msgType;
	public TelfHeaderOutEntity() {
		super();
		this.timestamp = new UtilHeader().getTimestampValue();
		this.msgType = "Response";
	}
	public void updateTimeStamp() {
		this.timestamp = new UtilHeader().getTimestampValue();
	}
}
