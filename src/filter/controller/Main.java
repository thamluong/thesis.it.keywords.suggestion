package filter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import print.Print;

public class Main {

	 static Map<String, Integer> map = new HashMap<>();
	 
	public static String getContent(String url) throws Exception{
		
		return Url.getContent(url);
	}

	public static Map<String, Integer> getWords(String content) throws Exception{//Print.print(content);
		return Word.getWords(content);
	}
	
	public static Map<String, Integer> getPhrases(String content) throws Exception{//Print.print(content);
		return Word.getPhrases(content);
	}
	
	public static Map<String, Integer> getWords(List<String> urls) throws Exception{
		 Map<String, Integer> temp = new HashMap<>();
		for(String s: urls){
			temp = Word.getWordListUrl(getContent(s));
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
		String url = "https://www.tutorialspoint.com/";
		//Print.print(getWords(getContent(url)));
		Print.print(getPhrases(getContent(url)));


		
	}
}
