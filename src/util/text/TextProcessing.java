package util.text;

import java.util.*;

public class TextProcessing {
	
	public static ArrayList<String> processByEnter(String text) {
		String enterSplit[] = text.split("\n");
        ArrayList<String> enterSplitList = new ArrayList<String>(Arrays.asList(enterSplit));
		
		return enterSplitList;
	}

}
