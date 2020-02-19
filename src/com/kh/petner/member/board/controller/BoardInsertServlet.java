package com.kh.petner.member.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.petner.member.board.model.service.mpBoardService;
import com.kh.petner.member.board.model.vo.mpBoard;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/mpInsert.bo")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("checked : " +(!ServletFileUpload.isMultipartContent(request)));
		
		System.out.println("request getContentType : "+ request.getContentType());
		
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "multipart를 통한 전송이 아닙니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}

		int maxSize=1024*1024*10;
		String root =request.getServletContext().getRealPath("/");
		
		System.out.println("root : "+ root);
		String savePath=root+"resources/img/member/mpBoard";
		System.out.println("savePath : " + savePath);
		
		MultipartRequest mrequest = new MultipartRequest(request,savePath,maxSize,"UTF-8", new DefaultFileRenamePolicy());
		
		String userEmail= mrequest.getParameter("userEmail");
		String Type_ = mrequest.getParameter("Type");
		String title = mrequest.getParameter("bTitle");
		String jqteArea = mrequest.getParameter("jqteArea");
		int Type = Integer.parseInt(Type_);
		
		System.out.println("userEmail : "+userEmail);
		System.out.println("Type : "+Type);
		System.out.println("title : "+title);
		System.out.println("jqteArea : "+jqteArea);
	
		String b_file = mrequest.getFilesystemName("b_file");
		
		mpBoard b = null;
		
		b=new mpBoard(Type, title, jqteArea, userEmail, b_file);
		
		int result=0;
		result= new mpBoardService().insertmpBoard(b);
		
		if(result>0) {
			System.out.println("mpBoard등록 성공");
			
			response.sendRedirect("views/member/mypage.jsp");
		}else {
			System.out.println("mpBoard등록 실패");
			request.setAttribute("msg", "글 쓰기 실패 ㅠㅠ");
			request.getRequestDispatcher("../../../views/common/errorPage.jsp");
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
