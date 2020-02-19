package com.kh.petner.hotel.model.vo;

import java.io.Serializable;

public class HotelKimsuninBoard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2019502721461850829L;
	private int board_number;
	private String board_email;
	private String board_title;
	private String board_content;
	private int board_date;
	private String board_file;
	
	public HotelKimsuninBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HotelKimsuninBoard(int board_number, String board_email, String board_title, String board_content,
			int board_date, String board_file) {
		super();
		this.board_number = board_number;
		this.board_email = board_email;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_date = board_date;
		this.board_file = board_file;
	}

	public int getBoard_number() {
		return board_number;
	}

	public void setBoard_number(int board_number) {
		this.board_number = board_number;
	}

	public String getBoard_email() {
		return board_email;
	}

	public void setBoard_email(String board_email) {
		this.board_email = board_email;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public int getBoard_date() {
		return board_date;
	}

	public void setBoard_date(int board_date) {
		this.board_date = board_date;
	}

	public String getBoard_file() {
		return board_file;
	}

	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "HotelKimsuninBoard [board_number=" + board_number + ", board_email=" + board_email + ", board_title="
				+ board_title + ", board_content=" + board_content + ", board_date=" + board_date + ", board_file="
				+ board_file + "]";
	}
	
	

	
	
}
