package order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import order.dao.OrderDAO;

public class OrderListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		//���� ��������
				HttpSession session=request.getSession();
				String o_m_id=(String)session.getAttribute("m_id");
				//���� ������ ./MemberLogin.me
				if(o_m_id==null){

					return "MemberLogin.me";
				}
				//OrderDAO orderdao ��ü ����
				OrderDAO orderdao=new OrderDAO();
				//List orderList=�޼���ȣ�� getOrderList(id)
				List orderList=orderdao.getOrderList(o_m_id);
				//���� orderList
				request.setAttribute("orderList", orderList);
				//�̵� ./goods_order/order_list.jsp
				return "order_list";
			}
		}
