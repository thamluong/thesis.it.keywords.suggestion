package filter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Url {

	final static String prefix = "https://www.google.com.vn/search?num=10&tbm=&q=";
	final static List<String> urls = new ArrayList<String>();
	
	// list of URLs which is the input to get content of its page and start filter words with their frequency (one by one) 
	public static List<String> input(){
		
		urls.add("http://www.journaldev.com/");
		urls.add("https://www.mkyong.com");
		urls.add("https://examples.javacodegeeks.com");
		urls.add("https://www.tutorialspoint.com");
		urls.add("http://www.javaworld.com");
		urls.add("http://hibernate.org/search/documentation/getting-started/");
		urls.add("docs.spring.io/spring/docs/current/spring-framework-reference/html/orm.html");
		urls.add("https://dzone.com/tutorials/java/hibernate/hibernate-example/");
		urls.add("https://docs.jboss.org/hibernate/orm");
		urls.add("https://netbeans.org/kb/docs/web/hibernate-webapp.html");
		urls.add("http://docs.oracle.com/");
		
		return urls;
	}

	// Get list related-URL in a URL
	public static List<String> getUrls(String url) throws Exception	{
		List<String> result = new ArrayList<String>();
		String[] keys ;
		Document doc;
		List<String> list = new ArrayList<String>();

			doc = Jsoup.connect(url).timeout(30000).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();
			// String text = doc.text(); //Get content in interface webpage

			result = getWebpageLinks(url);
			for (String s: result){
				keys = s.split("(http://)|(https://)|(www.)|(.com)|(.org)|[/ . | - | _]");
				for(String s1 : keys){		//System.out.print(s1+", ");
					if(!list.contains(s1)) list.add(s1);
				}}
			return list;
	}

	public static List<String> getWebpageLinks(String url) throws Exception{

		List<String> links = new ArrayList<String>();
		Document doc;

		doc = Jsoup.connect(url).timeout(30000).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();

		Elements link = doc.body().select("a[href]");
		for (Element l : link) {

			String temp = l.attr("href");
			if ((temp.startsWith("http") || temp.startsWith("com") || temp.startsWith("org")) 
					&& (!temp.contains("google") && !temp.contains("youtube"))){ 
				links.add(temp);//System.out.println("" +temp);
			}
		}

		return links;
	}

	public static String getContent(String url) throws Exception{

		Document doc;
		String text = "";

		doc = Jsoup.connect(url).timeout(30000)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2")
				.get();
		text = doc.text(); //return content in interface webpage

		//			System.out.println("body = "+doc.body());// return html of url
		//			System.out.println("outerHTML = "+doc.outerHtml());// return all of html + javascript of url
		//			System.out.println("title = "+doc.title());// return title

		return text;
	}
	
	public static Element getHtml(String url) throws Exception{

		Document doc;
		Element content;

		doc = Jsoup.connect(url).timeout(30000)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2")
				.get();

		content = doc.body(); // return html of url
		
		return content;
	
	}
}
