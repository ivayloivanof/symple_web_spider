package web_spider.validator;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

public class EmailValidator {
	
	public static HashSet<String> parse(Document document) {
		
		HashSet<String> emails = new HashSet<String>();
		
		Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
        Matcher matcher = p.matcher(document.text());
        while (matcher.find()) {
            emails.add(matcher.group());
        }
		
		return emails;
	}

}
