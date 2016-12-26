package filter.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import print.Print;

public class Main {

	 static Map<String, Integer> map = new HashMap<>();
	 
	public static String getContentByUrl(String url) throws Exception{
		
		return Url.getContentByUrl(url);
	}

	public static Map<String, Integer> getWordList(String content){//Print.print(content);
		return Word.getWordList(content);
	}
	
	public static Map<String, Integer> getWordList(List<String> urls) throws Exception{
		 Map<String, Integer> temp = new HashMap<>();
		for(String s: urls){
			temp = Word.getWordListUrl(getContentByUrl(s));
			for(String s1 : temp.keySet()){
				if(map.get(s1) > 0) {
					int x = map.get(s1);
					map.replace(s1, x + temp.get(s1));
				}
				else map.put(s1, temp.get(s1));
			}
		}
		return map;
	}
	
	public static void main (String args[]) throws Exception{
		String query = "https://www.tutorialspoint.com/";
		Print.print(getWordList(getContentByUrl(query)));
		//String s2 = KeywordFilter.getWordList(query, true);
		//Print.print(getWordList(Url.inputUrls()));
		
	}
}
