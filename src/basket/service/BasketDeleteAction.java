package basket.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basket.dao.BasketDAO;

public class BasketDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		//세션값 가져오기
				HttpSession session=request.getSession();
				String b_m_id=(String)session.getAttribute("m_id");
				//세션값 없으면 ./MemberLogin.me 이동

				if(b_m_id==null){
				
					return "MemberLogin.me";
				}
				// b_num 가져오기
				int b_num=Integer.parseInt(request.getParameter("b_num"));
				// 디비객체 생성 basketdao
				BasketDAO basketdao=new BasketDAO();
				//메서드호출 basketDelete(b_num)
				basketdao.basketDelete(b_num);
				//이동 ./BasketList.ba
				return "BasketList.ba";
			}
		}
