package admin.a_order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.AdminOrderDAO;

public class AdminOrderListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		//관리자세션제어
				//디비파일 만들기 net.admin.order.db AdminOrderDAO
				//객체 생성 AdminOrderDAO aodao
				AdminOrderDAO aodao=new AdminOrderDAO();
				//List adminOrderList= 메서드 호출  getAdminOrderList()
				List adminOrderList=aodao.getAdminOrderList();
				//저장 adminOrderList
				request.setAttribute("adminOrderList", adminOrderList);
				//이동 ./adminorder/admin_order_list.jsp
				
				return "admin_order_list";
	}

}
