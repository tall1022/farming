package admin.a_order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.AdminOrderDAO;

public class AdminOrderDetailAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// trade_num 가져오기
				String o_trade_num=request.getParameter("o_trade_num");
				// 객체 생성 AdminOrderDAO aodao객체 생성
				AdminOrderDAO aodao=new AdminOrderDAO();
				// List adminOrderDetail 
				//   =메서드호출 getAdminOrderDetail(trade_num)
				List adminOrderDetail=aodao.getAdminOrderDetail(o_trade_num);
				//저장 adminOrderDetail 
				request.setAttribute("adminOrderDetail", adminOrderDetail);
				//이동 ./adminorder/admin_order_modify.jsp
				
				return "admin_order_modify";
	}

}
