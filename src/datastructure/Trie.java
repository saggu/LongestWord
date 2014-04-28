package datastructure;

import java.util.ArrayList;
import java.util.List;

public class Trie {
	
	char charValue;
	boolean isLeaf;
	int intPrefixNum;
	List<Trie> children;
	
	public Trie(char c)
	{
		this.charValue = c;
		this.children = new ArrayList<Trie>();
		this.isLeaf = false;
		this.intPrefixNum = 1;
		
	}
	
	public Trie(char charValue, boolean isLeaf, int intPrefixNum, List<Trie> children) {
		this.charValue = charValue;
		this.isLeaf = isLeaf;
		this.intPrefixNum = intPrefixNum;
		this.children = children;
	}
	
	
	public Trie getChildNode(char c)
	{
		if(children != null)
		{
			for(Trie child : children)
			{
				if(child.charValue == c)
				{
					return child;
				}
			}
		}
		return null;
	}
	
	
	public void insertString(Trie root,String s)
	{
		Trie current = root;
		if(s.length() == 0)
		{
			current.isLeaf = true;
		}
		
		for(int i=0 ; i<s.length(); i++)
		{
			char cCurrent = s.charAt(i);
			Trie child = current.getChildNode(cCurrent);
			
			if(child != null)
			{
				current = child;
				current.intPrefixNum++;
			}
			else
			{
				current.children.add(new Trie(cCurrent));
				current = current.getChildNode(cCurrent);
			}
			
			if(1==s.length()-1)
			{
				current.isLeaf = true;
			}
		}
		
	}
	
	
	public boolean searchString(Trie root,String s)
	{
		Trie current = root;
		
		while (current != null) 
		{
			for (int i = 0; i < s.length(); i++) 
			{
				if (current.getChildNode(s.charAt(i)) == null)
					return false;
				else
					current = current.getChildNode(s.charAt(i));
			}

			if (current.isLeaf == true)
				return true;
			else
				return false;
		}
		
		return false;
	}
	
	public boolean deleteString(Trie root, String s)
	{
		if(searchString(root, s))
		{
			Trie current = root;
			Trie runner;
			while(current != null)
			{
				for(int i=0; i < s.length(); i++)
				{
					runner = current;
					current= current.getChildNode(s.charAt(i));
					current.intPrefixNum--;
					
					if(current.intPrefixNum == 0)
					{
						runner.children.remove(current);
						break;
					}
				}
				
				current.isLeaf = false;
				return true;
			}
		}
		return false;
	}
	

}
