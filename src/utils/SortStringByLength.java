package utils;

import java.util.Comparator;

public class SortStringByLength implements Comparator<String>{
	
	public int compare(String s1, String s2)
	{
		if (s1.length() < s2.length())
		return 1;
		else if (s1.length() > s2.length())
		return -1;
		else
		return 0;
	}
	

}
