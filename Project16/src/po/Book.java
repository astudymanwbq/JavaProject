package po;

import java.sql.Date;

public class Book {
	private String Bid;
	private String Bname;
	private String Btype;
	private String Bauthor;
	private int Bprice;
	private int Bamount;
	private Date Bdate;
	public String getBid() {
		return Bid;
	}
	public void setBid(String bid) {
		Bid = bid;
	}
	public String getBname() {
		return Bname;
	}
	public void setBname(String bname) {
		Bname = bname;
	}
	public String getBtype() {
		return Btype;
	}
	public void setBtype(String btype) {
		Btype = btype;
	}
	public String getBauthor() {
		return Bauthor;
	}
	public void setBauthor(String bauthor) {
		Bauthor = bauthor;
	}
	public int getBprice() {
		return Bprice;
	}
	public void setBprice(int bprice) {
		Bprice = bprice;
	}
	public int getBamount() {
		return Bamount;
	}
	public void setBamount(int bamount) {
		Bamount = bamount;
	}
	public Date getBdate() {
		return Bdate;
	}
	public void setBdate(Date bdate) {
		Bdate = bdate;
	}
	
	
}
