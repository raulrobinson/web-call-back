package co.com.telefonica.ws.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import co.com.telefonica.ws.ui.model.header.TelfHeaderInEntity;
import co.com.telefonica.ws.ui.model.header.TelfHeaderOutEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class UtilHeader {

	private static final int TAM_SOURCE_FIELD = 40;
	private static final String MSGTYPE = "msgType";
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	private static final String REGEX_FORMAT = "[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}";

	private TelfHeaderInEntity headerIn = null;

	public TelfHeaderInEntity processHeader(HttpHeaders headers) throws IOException {
		headerIn = new TelfHeaderInEntity(headers);
		if (validateHeader(headerIn)) {
			return headerIn;
		} else {
			throw new IOException("Bad Request");
		}
	}

	public TelfHeaderOutEntity processHeaderOut(HttpHeaders headers) throws IOException {
		headerIn = new TelfHeaderInEntity(headers);

		if (validateHeader(headerIn)) {
			return new TelfHeaderOutEntity();
		} else {
			throw new IOException("Bad Request");
		}
	}

	public static MediaType getMediaType(HttpHeaders headers) {
		String mediaType = TelcoSecurityUtils.blindParameter(headers.getFirst("mediaType"));

		return new MediaType((mediaType.equals(MediaType.APPLICATION_XML_VALUE)) ? MediaType.APPLICATION_XML_VALUE
				: MediaType.APPLICATION_JSON_VALUE);
	}

	public boolean validateHeader(TelfHeaderInEntity headerIn) {

		if (headerIn == null) {
			return false;
		}

		return validateField(headerIn.getParamAu()) && validateField(headerIn.getSystem())
				&& validateField(headerIn.getOperation()) && validateRegExExecId(headerIn.getExecId())
				&& validateTimeStamp(headerIn.getTimestamp()) && validateField(headerIn.getMsgType());

	}

	public static boolean validateField(String source) {
		return !(source == null || source.isEmpty() || source.length() > TAM_SOURCE_FIELD);
	}

	public static boolean validateTimeStamp(String strDate) {
		if (validateField(strDate)) {
			/*
			 * Set preferred date format, For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.
			 */
			DateFormat sdfrmt = new SimpleDateFormat(DATE_FORMAT);
			sdfrmt.setLenient(false);
			/*
			 * Create Date object parse the string into date
			 */
			try {
				sdfrmt.parse(strDate);

			}
			/* Date format is invalid */
			catch (ParseException e) {
				log.error("Error en formato de fecha: " + e.getMessage());
				return false;
			}

		} else {
			/* Return true if date format is valid */
			return true;

		}
		return true;

	}

	public static boolean validateRegExExecId(String source) {

		return !(Pattern.matches(REGEX_FORMAT, source)) || !(validateField(source));
	}

	public HttpHeaders buildHeaderOut(HttpHeaders headers) {
		HttpHeaders responseHeaders = new HttpHeaders();

		headers.forEach((key, values) -> {
			for (String value : values) {
				if (key.equals(MSGTYPE))
					responseHeaders.add(MSGTYPE, "RESPONSE");

				responseHeaders.add(key, TelcoSecurityUtils.blindParameter(value));
			}
		});

		return responseHeaders;
	}

	public HttpHeaders buildHeaderOut(TelfHeaderOutEntity headers) {
		HttpHeaders responseHeaders = new HttpHeaders();
		headers.updateTimeStamp();

		responseHeaders.add("timestamp", new UtilHeader().getTimestampValue());
		responseHeaders.add(MSGTYPE, "RESPONSE");
		responseHeaders.add("X-XSS-Protection", "1");
		responseHeaders.add("mode", "block");

		return responseHeaders;
	}

	public HttpHeaders buildHeaderOut() {
		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.add(MSGTYPE, "response");
		responseHeaders.add("X-XSS-Protection", "1");
		responseHeaders.add("mode", "block");

		return responseHeaders;
	}

	public String getTimestampValue() {
		ZoneId zoneIdCo = ZoneId.of("America/Bogota");
		ZonedDateTime now = ZonedDateTime.now(zoneIdCo);
		DateTimeFormatter dtf = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

		return now.truncatedTo(ChronoUnit.MILLIS).format(dtf);
	}

}
