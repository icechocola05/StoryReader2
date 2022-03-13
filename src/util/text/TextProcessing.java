package util.text;

import java.util.*;

public class TextProcessing {
	
	//1. 줄 바꿈 별 분리
	public static ArrayList<String> processByEnter(String text) {
		String enterSplit[] = text.split("\n");
        ArrayList<String> enterSplitList = new ArrayList<String>(Arrays.asList(enterSplit));
        
        for(int i=0; i<enterSplitList.size(); i++) {
                 System.out.println(i + " class: " + enterSplitList.get(i));
             }
            
		
		return enterSplitList;
	}
	
	//2. 따옴표 별 분리
	public static ArrayList<String> processBySpeaker(String text) {
		
		ArrayList<String> speakerSplitList = new ArrayList<String>();
        char nowChar = '.';
        int startIndex = -2;
        int endIndex = -1;
        int flag = 0; //따옴표 있는 문장에서는 1
        char nowSpeaking = '\'';
        String rawSentence = "";

        for(int i=0; i<text.length(); i++) {
            nowChar = text.charAt(i);

            //띄어쓰기면 건너뛰기
            if(nowChar == ' ' && nowChar == '\r' && nowChar == '\n') continue;

            //따옴표가 있는 문장
            if(nowChar == '\"' || nowChar == '\'' ) {
                if(flag == 1 && nowChar == nowSpeaking) { //같은 짝의 따옴표이고 끝나는 따옴표일 경우
                    flag = 0;
                    endIndex = i;
                    rawSentence = text.substring(startIndex, endIndex+1).trim();
                    rawSentence = rawSentence.replaceAll("\n", "");
                    rawSentence = rawSentence.replaceAll("\r", " ");
                    speakerSplitList.add(rawSentence);
                }
                else if(flag == 0 && startIndex < i) { //따옴표의 시작
                    //따옴표 앞에 종결부호 없는 문장이 있었을 경우
                    if(startIndex > endIndex) {
                        //마지막 인덱스에 문장 합치기
                        String lastSentence = speakerSplitList.get(speakerSplitList.size() - 1);
                        String addSentence = lastSentence + " " + text.substring(startIndex, i).trim();
                        speakerSplitList.set(speakerSplitList.size() - 1, addSentence);
                    }
                    //따옴표로 문장이 시작되는 경우
                    startIndex = i;
                    nowSpeaking = nowChar;
                    flag = 1;
                }
            }

            //따옴표가 없는 문장
            else if (flag == 0) {
                //시작 인덱스 정해주기
                if(endIndex > startIndex) {
                    if(nowChar != '.' && nowChar != '!' && nowChar != '?' || nowChar == '~') {
                        startIndex = i;
                        continue;
                    }
                }
                //마지막 인덱스
                if(nowChar == '.' || nowChar == '!' || nowChar == '?' || nowChar == '~') {
                    //종결문자가 위 세 개 중에 없을 때 (종결문자끼리 혼합되었을 때)
                    if(endIndex == i -1) {
                        endIndex = i;
                        String lastSentence = speakerSplitList.get(speakerSplitList.size() - 1);
                        String addSentence = lastSentence + nowChar;
                        speakerSplitList.set(speakerSplitList.size() - 1, addSentence);
                    }
                    else {
                        endIndex = i;
                        rawSentence = text.substring(startIndex, endIndex+1).trim();
                        speakerSplitList.add(rawSentence);
                    }
                    
                }
            }
        }
        
        return speakerSplitList;
	}

}
