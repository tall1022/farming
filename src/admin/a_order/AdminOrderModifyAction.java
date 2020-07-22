package admin.a_order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dao.AdminOrderDAO;
import order.model.Order;

public class AdminOrderModifyAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//자바빈 객체 생성 OrderBean orderbean
		Order orderbean = new Order();
		// trade_num  status   trans_num
		orderbean.setO_trade_num(request.getParameter("o_trade_num"));
		orderbean.setO_status(Integer.parseInt(request.getParameter("o_status")));
		orderbean.setO_trans_num(Integer.parseInt(request.getParameter("o_trans_num")));
		
		//폼 => 자바빈 멤버변수 저장
		//디비객체 생성 AdminOrderDAO aodao
		AdminOrderDAO aodao=new AdminOrderDAO();
		//메서드호출 updateOrder(orderbean)
		aodao.updateOrder(orderbean);
		//이동 ./AdminOrderList.ao
		
		return "AdminOrderList.ao";
	}

}
