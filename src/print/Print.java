package print;

import java.util.List;
import java.util.Map;

public class Print {


	public static void print(Object s){
		System.out.println(s.toString());
	}

	public static void print(Object temp, Object s){
		System.out.println(temp.toString() + " \t= " + s.toString());
	}

	public static void print(List<String> list){
		for(String s : list) 
			System.out.println(s);
	}

	public static void print(Map<String, Integer> map){
		for(Map.Entry<String, Integer> e : map.entrySet())
			Print.print(e.getKey(), e.getValue());
	}

}
