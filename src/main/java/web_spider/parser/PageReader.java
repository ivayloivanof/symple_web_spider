package web_spider.parser;

import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import web_spider.validator.EmailValidator;
import web_spider.validator.UrlValidator;

public class PageReader {
	
	private final static Logger LOG = Logger.getLogger(PageReader.class.getName());
	
	private String url;
	private Document page;
	private Elements links;
	private HashSet<String> pageLinks;
	private HashSet<String> pageEmails;
	
	public String getUrl() {
		return this.url;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	public Document getPage() {
		return this.page;
	}

	private void setPage(Document page) {
		this.page = page;
	}

	public Elements getLinks() {
		return this.links;
	}

	private void setLinks(Elements links) {
		this.links = links;
	}

	public HashSet<String> getPageLinks() {
		return this.pageLinks;
	}

	private void setPageLinks(HashSet<String> pageLinks) {
		this.pageLinks = pageLinks;
	}

	public HashSet<String> getPageEmails() {
		return this.pageEmails;
	}

	private void setPageEmails(HashSet<String> pageEmails) {
		this.pageEmails = pageEmails;
	}

	public PageReader(String url) {
		this.setUrl(url);
		this.setPage(this.read(this.url));
		this.setLinks(this.extractLinks(this.getPage()));
		this.setPageLinks(this.readAllLinks());
		this.setPageEmails(this.readAllEmails());
	}

	private Document read(String url) {
		try {
			return Jsoup.connect(url)
					  .data("query", "Java")
					  .userAgent("Mozilla")
					  .cookie("auth", "token")
					  .timeout(3000)
					  .get();
		} catch (IOException e) {
			LOG.info(e.getMessage());
		}
		
		return null;
	}
	
	private Elements extractLinks(Document document) {
		try {

			Elements e = document.select("a[href]");
			if(!e.isEmpty()) {
				return e;
			}
			
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
		
        return new Elements();
	}
	
	private HashSet<String> readAllLinks() {
		HashSet<String> result = new HashSet<String>();
		for (Element link : this.getLinks()) {
			String url = link.attr("abs:href");
			if (UrlValidator.isValid(url)) {
				result.add(url);
			}
        }
		
		return result;
	}
	
	private HashSet<String> readAllEmails() {
        return EmailValidator.parse(this.page);
	}
}
