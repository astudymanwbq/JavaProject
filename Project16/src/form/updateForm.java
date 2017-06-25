package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class updateForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bid;
	private int bamount;
	
	public String getBid() {
		return bid;
	}
	public int getBamount() {
		return bamount;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public void setbamount(int bamount) {
		this.bamount = bamount;
	}
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		return null;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
	}
}
