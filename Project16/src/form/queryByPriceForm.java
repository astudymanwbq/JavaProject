package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class queryByPriceForm extends ActionForm{
	private int low;
	private int high;
	
	public int getLow() {
		return low;
	}
	public int getHigh() {
		return high;
	}
	public void setLow(int low) {
		this.low = low;
	}
	public void setHigh(int high) {
		this.high = high;
	}
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		return null;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
	}
}
