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
	
			//세션값 가져오기
			//세션값 없으면  ./MemberLogin.me
			HttpSession session=request.getSession();
			String b_m_id=(String)session.getAttribute("m_id");

			if(b_m_id==null){

				return "MemberLogin.me";
			}
			//한글처리
			try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//자바빈 파일 만들기 net.basket.db  BasketBean
			//객체생성 BasketBean basketbean 
			Basket basketbean =new Basket();
			
			//폼 => 자바빈 저장  num  amount size color  id
			basketbean.setB_g_num(Integer.parseInt(request.getParameter("b_g_num")));
			basketbean.setB_g_amount(Integer.parseInt(request.getParameter("b_g_amount")));
			basketbean.setB_g_color(request.getParameter("b_g_color"));
			basketbean.setB_g_size(request.getParameter("b_g_size"));
			basketbean.setB_m_id(b_m_id);
			
			//디비 파일 만들기 net.basket.db BasketDAO
			//객체 생성 basketdao
			BasketDAO basketdao=new BasketDAO();
			
			//int check=상품 중복체크 중복이면 수량 update <= 1
			//      checkGoods(BasketBean basketbean)
			int check=basketdao.checkGoods(basketbean);
			if(check!=1){
			
				//메서드호출 basketAdd(basketbean)
			basketdao.basketAdd(basketbean);
			}
			
			//이동 ./BasketList.ba
			return "BasketList.ba";
		}
	}
	