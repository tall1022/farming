package order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import order.dao.OrderDAO;

public class OrderListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		//세션 가져오기
				HttpSession session=request.getSession();
				String o_m_id=(String)session.getAttribute("m_id");
				//세션 없으면 ./MemberLogin.me
				if(o_m_id==null){

					return "MemberLogin.me";
				}
				//OrderDAO orderdao 객체 생성
				OrderDAO orderdao=new OrderDAO();
				//List orderList=메서드호출 getOrderList(id)
				List orderList=orderdao.getOrderList(o_m_id);
				//저장 orderList
				request.setAttribute("orderList", orderList);
				//이동 ./goods_order/order_list.jsp
				return "order_list";
			}
		}
