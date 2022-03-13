package controller.makeStory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Block;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.Page;
import com.google.cloud.vision.v1.Paragraph;
import com.google.cloud.vision.v1.Symbol;
import com.google.cloud.vision.v1.TextAnnotation;
import com.google.cloud.vision.v1.Word;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import util.text.TextProcessing;

@WebServlet("/doUploadImage")
public class DoUploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DoUploadImage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadPath = request.getSession().getServletContext().getRealPath("/UploadImg");
	    System.out.println("절대경로 : " + uploadPath);
	    HttpSession session = request.getSession(true);
	     
	    int maxSize =1024 *1024 *10;// 저장 가능한 파일 크기: 10M로 제한
	    
	    String eleName = "";
    	String fileName = ""; // 업로드 파일 이름
    	String originalFileName =""; // 실제 원본 이름
    	String uploadFilePath = ""; // 업로드 파일 경로
    	String pageImage = ""; // 이미지
    	String pageText = ""; //추출된 텍스트
	    
		String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());  //현재시간
		
	    //이미지 파일 서버에 업로드
	    try{
	        // request, 파일저장경로, 용량, 인코딩타입, 중복파일명에 대한 기본 정책 -> 업로드
	    	MultipartRequest multi =new MultipartRequest(request,uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
	    	
	        // 파일 이름 변경
	        Enumeration files = multi.getFileNames();
	        while(files.hasMoreElements()) {
	        	eleName = (String)files.nextElement();
	        	fileName = multi.getFilesystemName(eleName);
	        	originalFileName = multi.getOriginalFileName(eleName);
	        	originalFileName = originalFileName.substring(0, originalFileName.indexOf("."));
	        	
	        	if(fileName != null) {
	        		File originFile = new File(uploadPath + "/" + fileName);
	        		String originFileName = originFile.getName();
	        		String ext = originFileName.substring(originFileName.lastIndexOf("."));
	        		String fileTempName = now + "_" + originalFileName + ext;
	        		File tempFile = new File(uploadPath + "/" + fileTempName); //변경한 파일명
	        		uploadFilePath = "./UploadImg/" + fileTempName;
	        		pageImage = uploadPath + "\\" + fileTempName;
	        		if(!originFile.renameTo(tempFile))
	        			System.out.print("파일명 변경 실패");
	        	}
	        }
	        
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    
	    //이미지에서 텍스트 추출
		System.out.println(pageImage);
		

//		try {
//	         GoogleCredentials credentials = GoogleCredentials.getApplicationDefault(); // fromStream(new FileInputStream(googleCredentialsConfiguration.getLocation()));
//         
//	         List<AnnotateImageRequest> requests = new ArrayList<>();
//	         ByteString imgBytes = ByteString.readFrom(new FileInputStream(pageImage));
//	         Image img = Image.newBuilder().setContent(imgBytes).build();
//	         Feature feat = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
//	         AnnotateImageRequest req = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
//	         requests.add(req);
//	         try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
//	            BatchAnnotateImagesResponse resp = client.batchAnnotateImages(requests);
//	            List<AnnotateImageResponse> resps = resp.getResponsesList();
//	            for (AnnotateImageResponse res : resps) {
//	               if (res.hasError()) {
//	                  System.out.printf("Error: %s\n", res.getError().getMessage());
//	                  return;
//	               }
//	               
//	             // For full list of available annotations, see http://g.co/cloud/vision/docs
//	             TextAnnotation annotation = res.getFullTextAnnotation();
//		             for (Page page: annotation.getPagesList()) {
//		             String pageText1 = "";
//		             for (Block block : page.getBlocksList()) {
//		             String blockText = "";
//		             for (Paragraph para : block.getParagraphsList()) {
//			             String paraText = "";
//			             for (Word word: para.getWordsList()) {
//				             String wordText = "";
//				             for (Symbol symbol: word.getSymbolsList()) {
//					             wordText = wordText + symbol.getText();
//					             System.out.format("Symbol text: %s (confidence: %f)\n", symbol.getText(),
//					             symbol.getConfidence());
//				             }
//				             System.out.format("Word text: %s (confidence: %f)\n\n", wordText, word.getConfidence());
//				             paraText = String.format("%s %s", paraText, wordText);
//			             }
//			             // Output Example using Paragraph:
//			             System.out.println("\nParagraph: \n" + paraText);
//			             System.out.format("Paragraph Confidence: %f\n", para.getConfidence());
//			             blockText = blockText + paraText;
//			          }
//			             pageText = pageText + blockText;
//		             }
//	             }
//	             System.out.println("\nComplete annotation:");
//	             System.out.println(annotation.getText());
//	             
//	               pageText = res.getTextAnnotationsList().get(0).getDescription();
//	               System.out.println("Text : ");
//	               System.out.println(pageText);
//	         
//	            }
//	         }
//	      }
//	      catch(Exception e) {
//	         e.printStackTrace();
//	      }
		session.setAttribute("uploadFilePath", uploadFilePath);
		// Del ) request.setAttribute("pageImage", pageImage);//업로드 파일 경로 + 이름
	    //request.setAttribute("pageText",pageText); //추출 텍스트
		pageText = "비가 추적추적 내리는 어두컴컴한 저녁이었어요.\r\n 무민 가족이 둘러앉아 버섯을 다듬고 있었어요.\r\n 무민파파가 빨간 버섯을 보며 중얼거렸어요.\r\n"
				+ "\"미이가 또 못 먹는 버섯을 따 왔네. 작년에도 그러더니.\"\r\n"
				+ "\"내년에는 맛있는 버섯을 따 올지도 몰라요. 희망을 가질 수 있으니 얼마나 좋아요.\"\r\n"
				+ "무민마마의 대답에 미이가 깔깔댔어요.\r\n"
				+ "그 뒤로 한동안 버섯을 다듬으며 평화로운 시간이 이어졌어요.";
		session.setAttribute("pageText", pageText);
		
		ArrayList<String> sentence_list = new ArrayList<String>();
		sentence_list = TextProcessing.processByEnter(pageText);
		session.setAttribute("sentence_list", sentence_list);
		session.setAttribute("processingMethod", "byEnter");
		
		response.sendRedirect("confirmImage.jsp");
//    	RequestDispatcher rd = request.getRequestDispatcher("/confirmImage.jsp");
//        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
