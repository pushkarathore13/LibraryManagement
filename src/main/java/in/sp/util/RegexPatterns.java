package in.sp.util;

public class RegexPatterns {

	public static final String NAME_REGEX =
			"^[A-Za-z ]+$";

	public static final String EMAIL_REGEX =
			"^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

	public static final String MOBILE_REGEX =
			"^[0-9]{10}$";

	public static final String AADHAAR_REGEX =
			"^[0-9]{12}$";

	public static final String PASSWORD_REGEX =
			"^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
}