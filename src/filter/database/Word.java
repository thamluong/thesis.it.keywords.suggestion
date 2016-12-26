package filter.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import print.Print;

public class Word {

	public static Map<String, Integer> getWordList(String content){
		// filter word-by-word in content is ok

		Map<String, Integer> map = new HashMap<>();
		List<String> keywords = new ArrayList<String>();
		String[] list = content.split("([;, ]{1,})|(\\. )");
		String s = "";
		try { 
			int i = 0;
			for(String s1 : list){//System.out.println(++i + "." + s + " " + s1);
			s = s1.toLowerCase();
			
			if (s.matches("\\d+")) {
				continue;
			}
			
			if(map.containsKey(s) == false)
				map.put(s, 1);
			else {
				int x = map.get(s);
				map.replace(s, x + 1);
			}
			}
		}catch(Exception e){
			System.out.println(e.getLocalizedMessage());
		}
		/*System.out.println("count = "+map.size());
		for(String s1: map.keySet())
			System.out.println(s1 + " , "+ map.get(s1) + "\t");*/
		
		return map;
	}

	public static Map<String, Integer> getWordListUrl(String content){
		// filter word-by-word in content is ok

		Map<String, Integer> map = new HashMap<>();
		List<String> keywords = new ArrayList<String>();
		String[] list = content.split("([;, ]{1,})|(\\. )");
		String keywords_list_str = "", s = "";
		try { int i = 0;
		for(String s1 : list){Print.print(++i+".");
		s = s1.toLowerCase();Print.print(s);
		if (s.matches("\\d+")) {
			Print.print("continue");
			continue;
		}
		if(keywords_list_str.contains(s) == false){ 
			map.put(s, 1);Print.print("count", map.size());
			keywords_list_str += ", "+ s;
		}
		else {
			int x = map.get(s);Print.print("value before", x);
			map.replace(s, x + 1);Print.print("VALUE after", map.get(s));Print.print("count", map.size());
		}
		}
		}catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
		/*System.out.println("count = "+map.size());
		for(String s1: map.keySet())
			System.out.println(s1 + " , "+ map.get(s1) + "\t");*/

		return map;
	}

}
