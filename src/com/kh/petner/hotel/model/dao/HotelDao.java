package com.kh.petner.hotel.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.petner.hotel.controller.HotelBoardDbServlet;
import com.kh.petner.hotel.model.vo.Hotel;
import com.kh.petner.hotel.model.vo.HotelKimsuninBoard;
import com.kh.petner.market.model.vo.Market;

import static com.kh.petner.common.JDBCTemplate.*;
public class HotelDao {
	
	private Properties prop;
	
	public HotelDao() {
		prop = new Properties();
		
		String filePath = Hotel.class.getResource("/config/hotel-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filePath));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getListCount(Connection con) {
		int listCount = 0;
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("listCount");
		
		try {
			
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}
	
	public ArrayList<Market> selectList(Connection con, int currentPage, int limit) {
		ArrayList<Market> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		try {
			pstmt = con.prepareStatement(sql);
			
			int startRow = (currentPage-1)*limit + 1; // 1, 11 
			int endRow = startRow + limit -1;
			
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			System.out.println("Hotel Dao selectList(전체 보기) endRow : " + endRow);
			System.out.println("Hotel Dao selectList(전체 보기) startRow : " + startRow);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Market>();
			
			while(rset.next()) {
				Market h = new Market();
				
				h.setMarketId(rset.getInt("MARKET_ID"));
				h.setMarketName(rset.getString("MARKET_NAME"));
				h.setMarketPwd(rset.getString("MARKET_PASSWORD"));
				h.setMarketEmail(rset.getString("MARKET_EMAIL"));
				h.setMarketType(rset.getString("MARKET_TYPE"));
				h.setMarketTel(rset.getString("MARKET_TEL"));
				h.setMarketCellPhone(rset.getString("MARKET_CELLPHONE"));
				h.setMarketAddress(rset.getString("MARKET_ADDRESS"));
				h.setMarketPlace(rset.getString("MARKET_PLACE"));
				h.setMarketMainPhoto(rset.getString("MARKET_MAINPHOTO"));
				
				list.add(h);
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("sikim HotelDao selectList(전체 불러오기) : " + list);
		return list;
	}
	
	public ArrayList<Market> searchHotelLocation(Connection con, String[] locationArr) {
		ArrayList<Market> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = null;
		
		sql = prop.getProperty("searchLocationGroupFirst");
		
		try {
			pstmt = con.prepareStatement(sql);
			list = new ArrayList<Market>();
			for(int i=0; i<locationArr.length; i++) {
					
				pstmt.setString(1, locationArr[i]);
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Market h = new Market();
					
					h.setMarketId(rset.getInt("MARKET_ID"));
					h.setMarketName(rset.getString("MARKET_NAME"));
					h.setMarketPwd(rset.getString("MARKET_PASSWORD"));
					h.setMarketEmail(rset.getString("MARKET_EMAIL"));
					h.setMarketType(rset.getString("MARKET_TYPE"));
					h.setMarketTel(rset.getString("MARKET_TEL"));
					h.setMarketCellPhone(rset.getString("MARKET_CELLPHONE"));
					h.setMarketAddress(rset.getString("MARKET_ADDRESS"));
					h.setMarketPlace(rset.getString("MARKET_PLACE"));
					h.setMarketMainPhoto(rset.getString("MARKET_MAINPHOTO"));
					
					list.add(h);
				}
			}	
			

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		System.out.println("sikim Hotel Dao searchHotelLocation list : " + list.size());
		return list;
	}
	
	public int getListCountHotelName(Connection con, String name) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("listCountHotelName");
		
		try {
			System.out.println("sikim getListCountHotelName try 들어옴 ");
			pstmt = con.prepareStatement(sql);
			
			if(name.equals("null")) name = "";
			
			pstmt.setString(1, name);
			pstmt.setString(2, name);
			pstmt.setString(3, name);
			System.out.println("sikim getListCountHotelName name : " + name);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public ArrayList<Market> searchHotelName(Connection con, String name,int currentPage, int limit) {
		ArrayList<Market> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = null;
		
		sql = prop.getProperty("searchHotelName");
		System.out.println("sikim HotelDao searchHotelName sql : " + sql);
		try {
			System.out.println("sikim HotelDao searchHotelName try문 들어옴 ");
			pstmt = con.prepareStatement(sql);
			
			int startRow = (currentPage-1)*limit + 1; // 1, 11 
			int endRow = startRow + limit -1;
			System.out.println("sikim HotelDao searchHotelName startRow : " + startRow);
			System.out.println("sikim HotelDao searchHotelName endRow : " + endRow);
			System.out.println("sikim HotelDao searchHotelName name : " + name);
			pstmt.setString(1, name);
			pstmt.setString(2, name);
			pstmt.setString(3, name);
			pstmt.setInt(4, endRow);
			pstmt.setInt(5, startRow);
			
			rset = pstmt.executeQuery();
			System.out.println("sikim HotelDao searchHotelName rset : " + rset);
			list = new ArrayList<Market>();
			while(rset.next()) {
				System.out.println("sikim HotelDao searchHotelName rset.next() while문 들어옴 ");
				Market h = new Market();
								
				h.setMarketId(rset.getInt("MARKET_ID"));
				h.setMarketName(rset.getString("MARKET_NAME"));
				h.setMarketPwd(rset.getString("MARKET_PASSWORD"));
				h.setMarketEmail(rset.getString("MARKET_EMAIL"));
				h.setMarketType(rset.getString("MARKET_TYPE"));
				h.setMarketTel(rset.getString("MARKET_TEL"));
				h.setMarketCellPhone(rset.getString("MARKET_CELLPHONE"));
				h.setMarketAddress(rset.getString("MARKET_ADDRESS"));
				h.setMarketPlace(rset.getString("MARKET_PLACE"));
				h.setMarketMainPhoto(rset.getString("MARKET_MAINPHOTO"));
				
				list.add(h);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		System.out.println("Hotel Dao searchHotelName list : " +list);
		return list;
	}

	public int getListCountkimsuninBoard(Connection con) {
		int listCount = 0;
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("listCountkimsuninBoard");
		System.out.println("sql : " + sql);
		try {
			
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public ArrayList<HotelKimsuninBoard> selectListkimsuninboard(Connection con, int currentPage, int limit) {
		ArrayList<HotelKimsuninBoard> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList2");
		
		try {
			pstmt = con.prepareStatement(sql);
			int startRow = (currentPage-1)*limit + 1; // 1, 11 
			int endRow = startRow + limit -1;
			
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<HotelKimsuninBoard>();
			
			while(rset.next()) {
				HotelKimsuninBoard b = new HotelKimsuninBoard();
	
				b.setBoard_number(rset.getInt(2));
				b.setBoard_email(rset.getString(3));
				b.setBoard_title(rset.getString(4));
				b.setBoard_date(rset.getInt(5));
				
				list.add(b);
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertkimsuninBoard(Connection con, HotelKimsuninBoard b) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertSuninBoard");
		
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, b.getBoard_email());
			pstmt.setString(2, b.getBoard_title());
			pstmt.setString(3, b.getBoard_content());
			pstmt.setString(4, b.getBoard_file());
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public HotelKimsuninBoard selectOne(Connection con, int bno) {
		HotelKimsuninBoard b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new HotelKimsuninBoard();
				
				b.setBno(bno);
				b.setBtype(1);
				b.setBtitle(rset.getString("BTITLE"));
				b.setBcontent(rset.getString("BCONTENT"));
				b.setBwriter(rset.getString("USERNAME"));
				b.setBwriterId(rset.getString("BWRITER"));
				b.setBcount(rset.getInt("BCOUNT"));
				b.setBoardfile(rset.getString("BOARDFILE"));
				b.setDdate(rset.getDate("BDATE"));
				
				
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}


	
/*	전체 목록 조회 (페이징 처리 때문에 삭제)
	public ArrayList<Hotel> selectList(Connection con) {
		ArrayList<Hotel> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			
			

			list = new ArrayList<Hotel>();
			
			while(rset.next()){
					
				
				Hotel h = new Hotel();
				
				h.setHotel_num(rset.getInt(1));
				h.setHotel_name(rset.getString(2));
				
				list.add(h);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}*/
	 








}

































