package basket.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basket.dao.BasketDAO;
import basket.model.Basket;

public class BasketAddAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
	
			//���ǰ� ��������
			//���ǰ� ������  ./MemberLogin.me
			HttpSession session=request.getSession();
			String b_m_id=(String)session.getAttribute("m_id");

			if(b_m_id==null){

				return "MemberLogin.me";
			}
			//�ѱ�ó��
			try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//�ڹٺ� ���� ����� net.basket.db  BasketBean
			//��ü���� BasketBean basketbean 
			Basket basketbean =new Basket();
			
			//�� => �ڹٺ� ����  num  amount size color  id
			basketbean.setB_g_num(Integer.parseInt(request.getParameter("b_g_num")));
			basketbean.setB_g_amount(Integer.parseInt(request.getParameter("b_g_amount")));
			basketbean.setB_g_color(request.getParameter("b_g_color"));
			basketbean.setB_g_size(request.getParameter("b_g_size"));
			basketbean.setB_m_id(b_m_id);
			
			//��� ���� ����� net.basket.db BasketDAO
			//��ü ���� basketdao
			BasketDAO basketdao=new BasketDAO();
			
			//int check=��ǰ �ߺ�üũ �ߺ��̸� ���� update <= 1
			//      checkGoods(BasketBean basketbean)
			int check=basketdao.checkGoods(basketbean);
			if(check!=1){
			
				//�޼���ȣ�� basketAdd(basketbean)
			basketdao.basketAdd(basketbean);
			}
			
			//�̵� ./BasketList.ba
			return "BasketList.ba";
		}
	}
	