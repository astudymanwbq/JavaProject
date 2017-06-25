package dao;

import idao.IBookDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.Book;

public class BookDao implements IBookDao{
	private ArrayList query(String sql) {
		Connection conn = null;
		ArrayList books = new ArrayList();
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url = "jdbc:odbc:DSSchool";
			
			conn = DriverManager.getConnection(url);
			
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				Book book = new Book();
				book.setBid(rs.getString("BID"));
				book.setBname(rs.getString("BNAME"));
				book.setBauthor(rs.getString("BAUTHOR"));
				book.setBprice(rs.getInt("BPRICE"));
				book.setBamount(rs.getInt("BAMOUNT"));
				book.setBdate(rs.getDate("BDATE"));
				book.setBtype(rs.getString("BTYPE"));
				books.add(book);
			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try{
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				
			}
		}
		return books;
	}
	public ArrayList queryByPriceRange(int low, int high) {
		String sql = "SELECT * FROM T_BOOK WHERE BPRICE >= " + low + " AND BPRICE <= " + high + ";";
		return query(sql);
	}
	public ArrayList queryByName(String name) {
		name.replaceAll(" ", "");
		String sql = "SELECT * FROM T_BOOK WHERE BNAME LIKE '%" + name + "%';";
		return query(sql);
	}
	public int update(String Bid, int amount) {
		Bid.replaceAll(" ", "");
		Connection conn = null;
		int res = 0;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url = "jdbc:odbc:DSSchool";
			conn = DriverManager.getConnection(url);
			Statement stat = conn.createStatement();
			String sql = "UPDATE T_BOOK SET BAMOUNT = " + amount + " WHERE BID = '" + Bid + "';";
			res = stat.executeUpdate(sql);
			stat.close();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try{
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				
			}
		}
		return res;
	}
	public int add(Book book) {
		Connection conn = null;
		int res = 0;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url = "jdbc:odbc:DSSchool";
			conn = DriverManager.getConnection(url);
			Statement stat = conn.createStatement();
			String sql = "INSERT INTO T_BOOK(BID, BNAME, BAUTHOR, BTYPE, BPRICE, BAMOUNT, BDATE) VALUES(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, book.getBid());
			ps.setString(2, book.getBname());
			ps.setString(3, book.getBauthor());
			ps.setString(4, book.getBtype());
			ps.setInt(5, book.getBprice());
			ps.setInt(6, book.getBamount());
			ps.setDate(7, book.getBdate());
			res = ps.executeUpdate();
			stat.close();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(2333);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(2344433);
		} finally {
			try{
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				
			}
		}
		return res;
	}
}
