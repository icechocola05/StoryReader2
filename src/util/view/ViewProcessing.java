package util.view;

import java.util.ArrayList;

import model.dto.Sentence;

public class ViewProcessing {
	public static String getEmotionName(Sentence sentence){//설정한 감정의 이모티콘 이름을 받아오는 함수
		//각 문장의 emotion_id에 적절한 emoticon의 이름을 저장
		String emoticon = "";
		switch(sentence.getEmotionId()) {
		case 1://슬픔
			emoticon = "noto:neutral-face";
			break;
		case 2://기쁨
			emoticon = "noto:grinning-face-with-smiling-eyes";
			break;
		case 3:
			emoticon = "noto:angry-face";
			break;
		case 4:
			emoticon = "noto:crying-face";
			break;
		}
		return emoticon;
	}
	
	public static String getColorOpacityList(Sentence sentence){//설정한 감정의 세기에 따라 opacity를 지정하는 함수
		float val = sentence.getIntensity();
		String opacity ="";
		if(val>=(float)0.1&& val<=(float)0.3) {//0.1보다 크고 0.3보다 작은 경우
             opacity = "20%";
          }
          else if(val>=(float)0.4&&val<=(float)0.7) {
            opacity = "70%";
          }
          else if(val>=(float)0.8) {
            opacity = "100%";
         }
          else {
        	  opacity = Float.toString(val);
          }
		return opacity;
	}
	

}
