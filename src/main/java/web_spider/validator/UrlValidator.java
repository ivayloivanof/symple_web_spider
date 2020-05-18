package web_spider.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlValidator {
	
	public static boolean isValid(String url) {
		//Pattern to check if this is a valid URL address
		Pattern p = Pattern.compile("(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})");
		Matcher matcher = p.matcher(url);
		
		return matcher.matches();
	}

}
