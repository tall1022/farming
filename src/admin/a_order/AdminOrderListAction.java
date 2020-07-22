package admin.a_order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.AdminOrderDAO;

public class AdminOrderListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		//�����ڼ�������
				//������� ����� net.admin.order.db AdminOrderDAO
				//��ü ���� AdminOrderDAO aodao
				AdminOrderDAO aodao=new AdminOrderDAO();
				//List adminOrderList= �޼��� ȣ��  getAdminOrderList()
				List adminOrderList=aodao.getAdminOrderList();
				//���� adminOrderList
				request.setAttribute("adminOrderList", adminOrderList);
				//�̵� ./adminorder/admin_order_list.jsp
				
				return "admin_order_list";
	}

}
