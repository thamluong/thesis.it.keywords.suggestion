package filter.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeywordFilter {

	private static Map<String, Integer> map = new HashMap<>();
	private static List<String> keywords = new ArrayList<String>();
	private static String keywords_list_str = "";

	public static void getWordList(String content){
		String[] list = content.split("([\\., ]{1,})|(\\. )");
		String s = "";
		try { 
			for(String s1 : list){
				s = s1.toLowerCase();//System.out.println(s + "--");
				if (s.matches("\\d+")) continue;
				if(keywords_list_str.contains(s) == false){ 
					map.put(s, 1);
					keywords_list_str += ", "+ s;
				}
				else {
					int x = map.get(s);
					map.replace(s, x + 1);	//System.out.println(s + " , "+ map.get(s) + "\t");
				}
			}
		}catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
		System.out.println("count = "+map.size());for(String s1: map.keySet()) System.out.println(s1 + " , "+ map.get(s1) + "\t");
	}
	
	public static String getWordList(String url, boolean bl) throws Exception{
		List<String> urls = Url.getWebpageLinks(url);
		List<String> titles = new ArrayList<String>();
		String s = Url.getHtmlByUrl(url).toString(), title = "";
		
		int n = s.length();
		int i = 0, beginIndex = 0, endIndex = -1, title_index = 0;
		String keys_str = "";
		
		while (title_index > -1){
			title_index = s.indexOf("title", endIndex + 1);
			beginIndex = s.indexOf("\"",title_index + 1);
			endIndex = s.indexOf("\"", beginIndex + 1);
			title = s.substring(beginIndex + 1, endIndex);
			//System.out.println("title = " + title);
			keys_str += title +", ";
		}
		
		return keys_str;
	}
	
	public static void getWordListInRecursiveUrl(String url) throws Exception{
		List<String> urls = Url.getWebpageLinks(url);

		for(String u : urls){
			getWordList(Url.getContentByUrl(u));
		}
	}
	
	public static void main(String args[]) throws Exception{
		String url = "https://www.tutorialspoint.com/";
		//System.out.println(Url.getHtmlByUrl(url));
		
		String content = getWordList(url, true);System.out.println("content = "+ content);
		getWordList(content);
	
	}
}
