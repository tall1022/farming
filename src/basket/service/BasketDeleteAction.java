package basket.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basket.dao.BasketDAO;

public class BasketDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		//���ǰ� ��������
				HttpSession session=request.getSession();
				String b_m_id=(String)session.getAttribute("m_id");
				//���ǰ� ������ ./MemberLogin.me �̵�

				if(b_m_id==null){
				
					return "MemberLogin.me";
				}
				// b_num ��������
				int b_num=Integer.parseInt(request.getParameter("b_num"));
				// ���ü ���� basketdao
				BasketDAO basketdao=new BasketDAO();
				//�޼���ȣ�� basketDelete(b_num)
				basketdao.basketDelete(b_num);
				//�̵� ./BasketList.ba
				return "BasketList.ba";
			}
		}
