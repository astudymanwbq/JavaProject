/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package action;


import java.util.ArrayList;

import idao.IBookDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.queryByPriceForm;


public class querypriceAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	private IBookDao ibdao;
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		queryByPriceForm myForm = (queryByPriceForm) form;// TODO Auto-generated method stub
		
		int low = myForm.getLow();
		int high = myForm.getHigh();
		
		ArrayList res = ibdao.queryByPriceRange(low, high);
		request.setAttribute("list", res);
		return new ActionForward("/result.jsp");
	}
	public IBookDao getIbdao() {
		return ibdao;
	}
	public void setIbdao(IBookDao ibdao) {
		this.ibdao = ibdao;
	}
}