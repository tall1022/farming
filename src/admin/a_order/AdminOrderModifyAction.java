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
		//�ڹٺ� ��ü ���� OrderBean orderbean
		Order orderbean = new Order();
		// trade_num  status   trans_num
		orderbean.setO_trade_num(request.getParameter("o_trade_num"));
		orderbean.setO_status(Integer.parseInt(request.getParameter("o_status")));
		orderbean.setO_trans_num(Integer.parseInt(request.getParameter("o_trans_num")));
		
		//�� => �ڹٺ� ������� ����
		//���ü ���� AdminOrderDAO aodao
		AdminOrderDAO aodao=new AdminOrderDAO();
		//�޼���ȣ�� updateOrder(orderbean)
		aodao.updateOrder(orderbean);
		//�̵� ./AdminOrderList.ao
		
		return "AdminOrderList.ao";
	}

}
