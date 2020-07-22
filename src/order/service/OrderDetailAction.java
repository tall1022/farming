package order.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basket.dao.BasketDAO;
import goods.dao.GoodsDAO;
import order.dao.OrderDAO;
import order.model.Order;

public class OrderDetailAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		//���ǰ� ��������
				HttpSession session=request.getSession();
				String o_m_id=(String)session.getAttribute("m_id");
				//���ǰ� ������ ./MemberLogin.me
				if(o_m_id==null){
					return "MemberLogin.me";
				}
				//�ڹٺ� OrderBean ��ü ���� orderbean
				Order orderbean=new Order();
				//�ѱ�ó��
				try {
					request.setCharacterEncoding("utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// �� => �ڹٺ� ����
				// o_m_id, o_receive_name   o_receive_addr1
//				  o_receive_addr2   o_receive_phone
//				  o_receive_mobile   o_memo		  o_trade_payer
				orderbean.setO_m_id(o_m_id);
				orderbean.setO_memo(request.getParameter("o_memo"));
				orderbean.setO_trade_payer(request.getParameter("o_trade_payer"));
				orderbean.setO_receive_addr1(request.getParameter("o_receive_addr1"));
				orderbean.setO_receive_addr2(request.getParameter("o_receive_addr2"));
				orderbean.setO_receive_mobile(request.getParameter("o_receive_mobile"));
				orderbean.setO_receive_name(request.getParameter("o_receive_name"));
				orderbean.setO_receive_phone(request.getParameter("o_receive_phone"));
				orderbean.setO_trade_type("�¶����Ա�");
				//��ٱ��� ����۾� ��ü ���� basketdao
				BasketDAO basketdao=new BasketDAO();
				//Vector vector = �޼���ȣ�� getBasketList(id)
				Vector vector=basketdao.getBasketList(o_m_id);
				//List basketList=vector.get(0)
				List basketList=(List)vector.get(0);
				//List goodsList=vector.get(1)
				List goodsList=(List)vector.get(1);
				
				//���� ����  U+���ڰ��� ���÷������� http://ecredit.uplus.co.kr/
				
				// net.order.db OrderDAO ��ü���� orderdao
				OrderDAO orderdao=new OrderDAO();
				//�޼��� ȣ��  => �ֹ���������
				// �޼���ȣ�� addOrder(orderbean,basketList,goodsList)
				orderdao.addOrder(orderbean,basketList,goodsList);
				
				//����,���� ����
				
				//��ǰ��ü���� ����goodsdao    updateAmount(basketList)
				GoodsDAO goodsdao=new GoodsDAO();
				//goodsdao.updateAmount(basketList);
				
				//��ٱ��� ���� ���� basketdao//  deleteBasket(id)
				basketdao.deleteBasket(o_m_id);
				
				//�̵� ./OrderList.or
				return "OrderList.or";
			}
		}