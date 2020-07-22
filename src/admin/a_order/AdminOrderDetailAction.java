package admin.a_order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.AdminOrderDAO;

public class AdminOrderDetailAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// trade_num ��������
				String o_trade_num=request.getParameter("o_trade_num");
				// ��ü ���� AdminOrderDAO aodao��ü ����
				AdminOrderDAO aodao=new AdminOrderDAO();
				// List adminOrderDetail 
				//   =�޼���ȣ�� getAdminOrderDetail(trade_num)
				List adminOrderDetail=aodao.getAdminOrderDetail(o_trade_num);
				//���� adminOrderDetail 
				request.setAttribute("adminOrderDetail", adminOrderDetail);
				//�̵� ./adminorder/admin_order_modify.jsp
				
				return "admin_order_modify";
	}

}
