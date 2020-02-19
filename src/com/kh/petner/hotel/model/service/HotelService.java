package com.kh.petner.hotel.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.petner.hotel.controller.HotelBoardDbServlet;
import com.kh.petner.hotel.model.dao.HotelDao;
import com.kh.petner.hotel.model.vo.Hotel;
import com.kh.petner.hotel.model.vo.HotelKimsuninBoard;
import com.kh.petner.market.model.vo.Market;

import static com.kh.petner.common.JDBCTemplate.*;

public class HotelService {

	private HotelDao hDao = new HotelDao();
	
	public int getListCount() {
		
		Connection con = getConnection();
		
		int listCount = hDao.getListCount(con);
	
		close(con);
		
		return listCount;
	}
	
	public ArrayList<Market> selectList(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Market> list = hDao.selectList(con,currentPage,limit);
		
		close(con);
		
		return list;
		
	}
	
	public ArrayList<Market> searchHotelLocation(String[] locationArr) {
		Connection con = getConnection();
		ArrayList<Market> list = null;
		
		if(locationArr.length > 0) {
			list = hDao.searchHotelLocation(con,locationArr);
		}/*else {
			list = hDao.selectList(con);
		}*/
		close(con);
		
		return list;
	}
	
	public int getListCountHotelName(String name) {
	
		Connection con = getConnection();
		
		int listCount = hDao.getListCountHotelName(con,name);
	
		close(con);
		
		return listCount;
	}
	
	public ArrayList<Market> searchHotelName(String name,int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Market> list = null;
		System.out.println("name.length(): " + name.length());
		System.out.println("name : " + name);
		if(name.length() > 0 && !(name.equals("null"))) {
			System.out.println("@@@@@@@@@@@@@@@@@@@");
			list = hDao.searchHotelName(con,name,currentPage,limit);
			
		}else  {
			System.out.println("#####################");
			list = hDao.selectList(con,currentPage,limit);
			
		}
		
		close(con);
		
		return list;
}

	public int getListCountkimsuninBoard() {
		
		Connection con = getConnection();
		
		int listCount = hDao.getListCountkimsuninBoard(con);
	
		close(con);
		
		return listCount;
	}

	public ArrayList<HotelKimsuninBoard> selectListkimsuninboard(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<HotelKimsuninBoard> list = hDao.selectListkimsuninboard(con,currentPage,limit);
		
		close(con);
		
		return list;
	}

	public int insertkimsuninBoard(HotelKimsuninBoard b) {
		Connection con = getConnection();
		
		int result = hDao.insertkimsuninBoard(con,b);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public HotelKimsuninBoard selectOne(int bno) {
		Connection con = getConnection();
		
		HotelKimsuninBoard b = hDao.selectOne(con,bno);
		
		close(con);
		
		return b;
	}


	
	
/*	 전체 목록 조회 (페이징 처리 때문에 삭제)
	public ArrayList<Hotel> selectList() { //강사님은 한개 선택하는 메소드 이름으로 했는데 나는 전체 선택 메소드로 만듬 
		Connection con = getConnection();
		
		ArrayList<Hotel> list = hDao.selectList(con);
		
		close(con);
		
		
		return list;
	}*/







	
}
