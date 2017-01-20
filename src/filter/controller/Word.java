package filter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import print.Print;

public class Word {

	// regex chua pbiet version, vd: 4.0 van bi chia thanh 4 & 0
	public static Map<String, Integer> getWords(String content) throws Exception{

		Map<String, Integer> map = new HashMap<>();
		String[] list = content.split("([;, ]{1,})|(\\. )");
		String s = "";

		int i = 0;
		for(String s1 : list){//System.out.println(++i + "." + s + " " + s1);
			s = s1.toLowerCase();

			if (s.matches("\\d+"))
				continue;

			Integer n = map.get(s);
			n = (n == null) ? 1 : ++n;
			map.put(s, n);
		}

		return map;
	}

	public static Map<String, Integer> getPhrases(String content) throws Exception{
		//Print.print(content);
		content = content.replaceAll("([;, ]{1,})|(\\. )", " ").toLowerCase();
		//Print.print(content);
		Map<String, Integer> map = getWords(content);
		Map<String, Integer> map2 = new HashMap<>();
		int n, begin, end;
		String s2 = "";

		Print.print("length = "+content.length());
		for(String s1: map.keySet()){

			if (map.get(s1) >= 2) {//Print.print(s1);
				s2 = s1;
				do{
					begin = content.indexOf(s2);//Print.print(begin);
					if(begin + s2.length()+1 < content.length()) {
						end = content.indexOf(" ", begin + s2.length()+1);
						if(end == -1) end = content.length()-1 ;//Print.print(end);

						s2 = content.substring(begin, end);
						int value = count(content, s2);
						if(value >= 2) 
							map2.put(s2, value);
						else break;
					}
				}while(true);
			}
		}

		map.putAll(map2);

		SortedSet<String> keys = new TreeSet<String>(map2.keySet());
		for (String key : keys) 
			Print.print(key);


		//Print.print(map2);
		return map2;
	}

	private static int count(String content, String s){
		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1){

			lastIndex = content.indexOf(s,lastIndex);

			if(lastIndex != -1){
				count ++;
				lastIndex += s.length();
			}
		}
		return count;
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

	public static void main(String args[]) throws Exception{
		//getWords("toi la ai ke toi toi la phai di an day");
		getPhrases("toi la ai ke toi toi la phai di an day");

	}
}
