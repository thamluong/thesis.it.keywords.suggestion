package filter.database;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import print.Print;

public class Test {

	public static int count(String content, String s){
		int i = 0;
		Pattern p = Pattern.compile(s);
		Matcher m = p.matcher(content);
		while (m.find()) {
			i++;
		}
		return i;
	}

	public static void getWordGroupList(String content){
		Map<String, Integer> map = new HashMap<>();
		int begin = 0, end = content.indexOf(" "), next_begin = 0, time = 0;
		String s = "";

		while(begin >= 0 && end >= 0){

		if(end + 1 < content.length())
			do {
				next_begin = end + 1;
				end = content.indexOf(" ", end + 1);
				s = content.substring(begin, end);Print.print(s);
				if((time = count(content, s)) <= 1) 
					next_begin = end + 1;
				else
					break;

			}while(end >= 0 && end*2 < content.length());

		else s = content.substring(begin, end);
		map.put(s, time);		
		begin = next_begin;
		}

		Print.print(map);

	}

	public static void main(String args[]){
		getWordGroupList("toi la s tin hay xuongs ty cantin");
	}
}
