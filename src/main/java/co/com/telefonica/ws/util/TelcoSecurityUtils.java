package co.com.telefonica.ws.util;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

public class TelcoSecurityUtils {
	
	private TelcoSecurityUtils() {}

	public static String blindParameter(String headerValue) {
		PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
	    return policy.sanitize( headerValue );
	}

}
