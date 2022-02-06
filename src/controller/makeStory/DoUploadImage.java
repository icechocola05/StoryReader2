package controller.makeStory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/doUploadImage")
public class DoUploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DoUploadImage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadPath = request.getSession().getServletContext().getRealPath("/UploadImg");
	    System.out.println("절대경로 : " + uploadPath);
	     
	    int maxSize =1024 *1024 *10;// 저장 가능한 파일 크기: 10M로 제한
	    
	    String eleName = "";
    	String fileName = ""; // 업로드 파일 이름
    	String originalFileName =""; // 실제 원본 이름
    	String uploadFilePath = ""; // 업로드 파일 경로
	    
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
	        		uploadFilePath = "http://localhost:8080/UploadImg/" + fileTempName;
	        		
	        		if(!originFile.renameTo(tempFile))
	        			System.out.print("파일명 변경 실패");
	        	}
	        }
	        
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    
	    //이미지에서 텍스트 추출
	    
	    request.setAttribute("uploadFilePath", uploadFilePath); //업로드 파일 경로
		
    	RequestDispatcher rd = request.getRequestDispatcher("/confirmImage.jsp");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
