package basket.service;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basket.dao.BasketDAO;

public class BasketListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		//���� ��������
				HttpSession session=request.getSession();
				String b_m_id=(String)session.getAttribute("m_id");
				//���ǰ� ������  ./MemberLogin.me
				
				if(b_m_id==null){
					
					return "MemberLogin.me";
				}
				//BasketDAO ��ü ���� basketdao
				BasketDAO basketdao=new BasketDAO();
				//Vector vector= �޼���ȣ��  getBasketList(String id)
				//  => Vector vector=new Vector();
				Vector vector=basketdao.getBasketList(b_m_id);
				//List basketList = vector ù��°������
				List basketList=(List)vector.get(0);
				//List goodsList = vector �ι�°������
				List goodsList=(List)vector.get(1);
				// ���� basketList goodsList
				request.setAttribute("basketList", basketList);
				request.setAttribute("goodsList", goodsList);
				//�̵�   ./goods_order/goods_basket.jsp
				
				return "goods_basket";
			}
		}
