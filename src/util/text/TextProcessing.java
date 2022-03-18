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
        //줄바꿈 문자 제거
        text = text.replace(System.getProperty("line.separator").toString(), " ");
        //특수 문자의 경우 변환
        text = text.replace("”", "\"");
        text = text.replace("“", "\"");

        for(int i=0; i<text.length(); i++) {
            nowChar = text.charAt(i);

            //띄어쓰기면 건너뛰기
            if(nowChar == '\0') continue;

            //따옴표가 있는 문장
            if(nowChar == '\"' || nowChar == '\'' ) {
                if(flag == 1 && nowChar == nowSpeaking) { //같은 짝의 따옴표이고 끝나는 따옴표일 경우
                    flag = 0;
                    endIndex = i;
                    rawSentence = text.substring(startIndex, endIndex+1).trim();
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
	
	//3. 문장 부호 별 분리
	public static ArrayList<String> processByMark(String text) {
		ArrayList<String> markSplitList = new ArrayList<String>();
		char nowChar = '.';
        int startIndex = -2;
        int endIndex = -1;
        int flag = 0; //따옴표 있는 문장에서는 1
        char nowSpeaking = '\'';
        String rawSentence = "";
        int speakingSum = 0;
        text = text.replace(System.getProperty("line.separator").toString(), " ");
        //특수 문자의 경우 변환
        text = text.replace("”", "\"");
        text = text.replace("“", "\"");

        for(int i=0; i<text.length(); i++) {
            nowChar = text.charAt(i);

            //띄어쓰기면 건너뛰기
            if(nowChar == ' ') continue;

            //따옴표가 포함되어 있는 문장
            if(nowChar == '\"' || nowChar == '\'' ) {
                if(flag == 1 && nowChar == nowSpeaking) { //같은 짝의 따옴표이고 끝나는 따옴표일 경우
                    flag = 0;
                    endIndex = i;
                    continue;
                }
                else if(flag == 0) { //따옴표의 시작
                    nowSpeaking = nowChar;
                    flag = 1;
                    continue;
                }
            }

            //따옴표 내부 문장
            
            else if (flag == 1) {
                //문장의 끝
                if(nowChar == '.' || nowChar == '!' || nowChar == '?' || nowChar == '~') {
                    //종결문자가 위 세 개 중에 없을 때 (종결문자끼리 혼합되었을 때)
                    if(endIndex == i -1) {
                        endIndex = i;
                        String lastSentence = markSplitList.get(markSplitList.size() - 1);
                        StringBuilder builder = new StringBuilder(lastSentence);
                        builder.setCharAt(lastSentence.length() - 1, '\0');
                        String subSent = builder.toString();
                        String addSentence = subSent + nowChar + nowSpeaking;
                        markSplitList.set(markSplitList.size() - 1, addSentence);
                    }
                    else {
                        endIndex = i;
                        rawSentence = text.substring(startIndex, endIndex+1).trim();
                        
                        for(int j=0; j<rawSentence.length(); j++) {
                            //문장 내부에 따옴표 존재하는 경우
                            if(rawSentence.charAt(j) == nowSpeaking) {
                                speakingSum++;
                            }
                        }

                        if(speakingSum % 2 == 0) { //따옴표가 존재하지 않는 경우
                            rawSentence = text.substring(startIndex, endIndex+1).trim();
                            rawSentence = nowSpeaking + rawSentence + nowSpeaking;
                            markSplitList.add(rawSentence);
                        }
                        else {
                            rawSentence = text.substring(startIndex, endIndex+1).trim();
                            rawSentence = rawSentence + nowSpeaking;
                            markSplitList.add(rawSentence);
                        }
                        
                        speakingSum = 0;
                    }
                }
                //문장의 시작
                else if(endIndex > startIndex) {
                    startIndex = i;
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
                        String lastSentence = markSplitList.get(markSplitList.size() - 1);
                        String addSentence = lastSentence + nowChar;
                        markSplitList.set(markSplitList.size() - 1, addSentence);
                    }
                    //세 개 중에 있을 때
                    else {
                        endIndex = i;
                        rawSentence = text.substring(startIndex, endIndex+1).trim();
                        markSplitList.add(rawSentence);
                    }
                }
            }
        }
        
        return markSplitList;
	}
	

}
