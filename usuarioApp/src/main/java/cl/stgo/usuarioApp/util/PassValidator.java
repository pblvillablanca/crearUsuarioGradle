package cl.stgo.usuarioApp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PassValidator {

	private PassValidator() {
	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z][a-z]+\\d{2}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

}
