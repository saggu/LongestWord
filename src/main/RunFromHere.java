package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.SortStringByLength;
import datastructure.Trie;

public class RunFromHere {
	
	private static Trie root = new Trie('$'); //mark the root with '$'
	
	public static void main(String args[])
	{
		BufferedReader br = null;
		
		try
		{
			String[] sortedWords = null;
			List<String> longestWordList = new ArrayList<String>();
			List<String> wordList = new ArrayList<String>();
			String sCurrentLine;
			
			br = new BufferedReader(new FileReader("wordsforproblem.txt"));
			
			while ((sCurrentLine = br.readLine()) != null) 
			{
            	wordList.add(sCurrentLine);
            }
            br.close();
            
            sortedWords = (String[])wordList.toArray(new String[wordList.size()]);
            
            Arrays.sort(sortedWords, new SortStringByLength());
            
            for(String word : sortedWords)
            {
            	root.insertString(root, word);
            }
            
            longestWordList = LongestWordList(sortedWords);
            

            System.out.println("Longest Word: "+ longestWordList.get(0));
            System.out.println("Second longest Word: "+ longestWordList.get(1));
            System.out.println("Total such words: " +longestWordList.size());
     }
		catch(FileNotFoundException fne)
		{
			fne.printStackTrace();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	
	
	public static List<String> LongestWordList(String[] wordList)
	{
		List<String> wList = new ArrayList<String>();
		
		for(String word : wordList)
		{
			if(isPrefix(word, true))
			{
				wList.add(word);
			}
		}
		
		return wList;
	}
	
	public static boolean isPrefix(String s, boolean isActualWord) //is the string present in the word list
	{
		if(isActualWord)
		{
			root.deleteString(root, s);
		}
		
		for(int i=0; i < s.length(); i++)
		{
			if(root.searchString(root, s.substring(0,i+1)))
			{
				if(1+1 == s.length() || isPrefix(s.substring(i+1,s.length()),false))
				{
					return true;
				}
			}
		}
		
		if(isActualWord)
		{
			root.insertString(root, s);
		}
		return false;
	}
}
