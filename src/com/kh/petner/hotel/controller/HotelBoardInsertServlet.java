package com.kh.petner.hotel.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.petner.hotel.model.service.HotelService;
import com.kh.petner.hotel.model.vo.HotelKimsuninBoard;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class HotelBoardInsertServlet
 */
@WebServlet("/hsuninboardinsert")
public class HotelBoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotelBoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://servlets.com/cos/MultipartRequest를 사용하기 위한 라이브러리 다운로드
		// cos.jar 파일을 WEB-INF/lib 폴더에 추가
		
		// 파일 처리용 서블릿
		// 1. 전송할 최대 크기 설정하기
		// 10MB --> (Byte크기로 변환하기)
		// 1MB -> 1024KB / 1KB = 1024 Byte
		// 1024*1024*10;
		int maxSize = 1024*1024*10;
		
		// 2. multipart/form-data 형식으로 전송되었는지 확인
		if(!ServletFileUpload.isMultipartContent(request)) {
			// 만약 올바른 multipart/form-data로 전송 되지 않았을 경우
			// 에러 페이지로 즉시 이동 시킨다
			request.setAttribute("msg", "multipart를 통한 전송이 아닙니다");
			request.getRequestDispatcher("view/common/errorPage.jsp").forward(request,response);
		}
		
		// 3.웹 상의 루트(최상의 경로) 경로를 활용하여 저장할 폴더의 위치 지정하기
		String root = request.getServletContext().getRealPath("/");
		System.out.println("root : " + root);
		// 게시판의 첨부 파일을 저장할 폴더 이름 지정하기
		String savePath = root + "resources/boardUploadFiles";
		
		// 4. 실제 담아온 파일 기타 정보들을 활용하여 
		//    MultipartRequest 생성하기
		//    Request -> MultipartRequest
		
		MultipartRequest mrequest = new MultipartRequest(
										request, //변경하기 위한 원본 객체
										savePath, // 파일 저장 경로
										maxSize, // 저장할 파일의 최대 크기
										"UTF-8", // 저장할 문자셋 설정
										new DefaultFileRenamePolicy()
										// 동일한 이름의 파일을 저장 했을경우
										// 기존의 파일과 구분하기 위해
										// 새로운 파일명 뒤에 숫자를 붙이는 규칙
				                               );
		// --- 파일 업로드 로직 실시 --- //
		// 5-1. 기본 전송값 처리
		String bbsEmail = mrequest.getParameter("bbsEmail");
		String bbsTitle = mrequest.getParameter("bbsTitle");
		String bbsContent = mrequest.getParameter("bbsContent");
		
		System.out.println("bbsEmail : "+bbsEmail);
		System.out.println("bbsTitle : "+bbsTitle);
		System.out.println("bbsContent : "+bbsContent);
		
		String fileName = mrequest.getFilesystemName("file");
		
		HotelKimsuninBoard b = new HotelKimsuninBoard();
		b.setBoard_email(bbsEmail);
		b.setBoard_title(bbsTitle);
		b.setBoard_content(bbsContent);
		b.setBoard_file(fileName);
		
		int result = new HotelService().insertkimsuninBoard(b);
		
		if(result > 0) {
			response.sendRedirect("sunin.go");
		}else {
			request.setAttribute("msg", "게시글 작성 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
