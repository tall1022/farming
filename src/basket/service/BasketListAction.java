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
		//세션 가져오기
				HttpSession session=request.getSession();
				String b_m_id=(String)session.getAttribute("m_id");
				//세션값 없으면  ./MemberLogin.me
				
				if(b_m_id==null){
					
					return "MemberLogin.me";
				}
				//BasketDAO 객체 생성 basketdao
				BasketDAO basketdao=new BasketDAO();
				//Vector vector= 메서드호출  getBasketList(String id)
				//  => Vector vector=new Vector();
				Vector vector=basketdao.getBasketList(b_m_id);
				//List basketList = vector 첫번째데이터
				List basketList=(List)vector.get(0);
				//List goodsList = vector 두번째데이터
				List goodsList=(List)vector.get(1);
				// 저장 basketList goodsList
				request.setAttribute("basketList", basketList);
				request.setAttribute("goodsList", goodsList);
				//이동   ./goods_order/goods_basket.jsp
				
				return "goods_basket";
			}
		}
