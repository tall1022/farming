package order.service;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basket.dao.BasketDAO;
import member.dao.MemberDao;
import member.model.Member;

public class OrderStarAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		//���ǰ� ��������
				HttpSession session=request.getSession();
				String o_m_id=(String)session.getAttribute("m_id");
				//���ǰ� ������ ./MemberLogin.me
				if(o_m_id==null){
					return "MemberLogin.me";
				}
				//��ٱ��� ����۾� ��ü ���� basketdao
				BasketDAO basketdao=new BasketDAO();
				//Vector vector = �޼���ȣ�� getBasketList(id)
				Vector vector=basketdao.getBasketList(o_m_id);
				//List basketList=vector.get(0)
				List basketList=(List)vector.get(0);
				//List goodsList=vector.get(1)
				List goodsList=(List)vector.get(1);
				//��� ����۾� ��ü ���� memberdao
				MemberDao memberdao=new MemberDao();
				//MemberBean memberbean =�޼��� ȣ�� getMember(id)
				Member memberbean=memberdao.getMember(o_m_id);  // ����߰�
				//���� basketList  goodsList memberbean
				request.setAttribute("basketList", basketList);
				request.setAttribute("goodsList", goodsList);
				request.setAttribute("memberbean", memberbean); 
				//�̵� ./goods_order/goods_buy.jsp

				return "goods_buy";
			}
		}
