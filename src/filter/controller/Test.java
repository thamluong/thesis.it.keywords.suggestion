package filter.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import print.Print;

public class Test {

	public static int count(String content, String s){
		int i = 0;
		Pattern p = Pattern.compile("\\W"+s+"\\W|^("+s+"\\W+)|(\\W+"+s+")$|^("+s+")$");
		Matcher m = p.matcher(content);
		while (m.find()) {
			i++;
		}
		return i;
	}

	public static void print (int i){
		if( i>= 0){
		//System.out.println("i = " + i);
		print(--i);
		}
	}

	public static void time(){
		long startTime = System.currentTimeMillis();

	      print(20000);

	      long stopTime = System.currentTimeMillis();
	      long elapsedTime = stopTime - startTime;
	      System.out.println("time = " +elapsedTime);
	}
	
	public static void main(String args[]){
		//time();
		
		String s = "toi";
		String s2 = s;
		s2 = s +" la";
		System.out.println("s = "+ s);
		System.out.println("s2 = "+ s2);
	}
}
