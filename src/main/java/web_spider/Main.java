package web_spider;

import java.util.HashSet;

import web_spider.parser.PageReader;

public class Main {

	public static void main(String[] args) {
		HashSet<PageReader> pages = new HashSet<PageReader>();
		PageReader pageReader = new PageReader("https://emailblastgenerator.blogspot.com/2017/07/email-list-txt-download.html");
		pages.add(pageReader);
		
		for (String url : pageReader.getPageLinks()) {
			pages.add(new PageReader(url));
		}
		
		for (PageReader pReader : pages) {
			for (String link : pReader.getPageLinks()) {
				System.out.println(link);
			}
			for (String emal : pReader.getPageEmails()) {
				System.out.println(emal);
			}
		}
		
		System.out.println();
	}

}
