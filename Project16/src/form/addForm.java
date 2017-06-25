package form;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class addForm extends ActionForm{
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
	public String getBname() {
		return Bname;
	}
	public String getBtype() {
		return Btype;
	}
	public String getBauthor() {
		return Bauthor;
	}
	public int getBprice() {
		return Bprice;
	}
	public int getBamount() {
		return Bamount;
	}
	public Date getBdate() {
		return Bdate;
	}
	public void setBid(String bid) {
		Bid = bid;
	}
	public void setBname(String bname) {
		Bname = bname;
	}
	public void setBtype(String btype) {
		Btype = btype;
	}
	public void setBauthor(String bauthor) {
		Bauthor = bauthor;
	}
	public void setBprice(int bprice) {
		Bprice = bprice;
	}
	public void setBamount(int bamount) {
		Bamount = bamount;
	}
	public void setBdate(Date bdate) {
		Bdate = bdate;
	}
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		return null;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
	}
}
