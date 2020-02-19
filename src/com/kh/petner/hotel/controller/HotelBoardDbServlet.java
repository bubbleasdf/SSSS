package com.kh.petner.hotel.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.petner.hotel.model.service.HotelService;
import com.kh.petner.hotel.model.vo.HotelKimsuninBoard;
import com.kh.petner.hotel.model.vo.HotelPageInfo;

/**
 * Servlet implementation class HotelKimsuninBoard
 */
@WebServlet("/sunin.go")
public class HotelBoardDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotelBoardDbServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<HotelKimsuninBoard> list = null;
		HotelService hs = new HotelService();
		
		int startPage;
		int endPage;
		int maxPage; 
		int currentPage;
		int limit;
		currentPage = 1; 
		limit = 5;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int listCount = hs.getListCountkimsuninBoard();
		
		System.out.println(" 선인 보드 총 페이지 개수 : " + listCount);
		
		maxPage = (int)((double)listCount/ limit + 0.9);
	
		startPage = (int)((double)currentPage/limit + 0.9-1) * limit + 1;
		
		endPage = startPage + limit -1;
		
		if(endPage > maxPage){
			endPage = maxPage;
		}
		
		list = hs.selectListkimsuninboard(currentPage, limit);
		
		String page = "";
		
		if(list != null) {
			page = "views/hotel/hotel_suninboard.jsp";
			request.setAttribute("list", list);
			
			HotelPageInfo hpi = new HotelPageInfo(currentPage,listCount,limit,maxPage,startPage,endPage);
			
			request.setAttribute("hpi", hpi);
			
		}else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("list", list);
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
