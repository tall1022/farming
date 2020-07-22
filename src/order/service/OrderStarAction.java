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
		//세션값 가져오기
				HttpSession session=request.getSession();
				String o_m_id=(String)session.getAttribute("m_id");
				//세션값 없으면 ./MemberLogin.me
				if(o_m_id==null){
					return "MemberLogin.me";
				}
				//장바구니 디비작업 객체 생성 basketdao
				BasketDAO basketdao=new BasketDAO();
				//Vector vector = 메서드호출 getBasketList(id)
				Vector vector=basketdao.getBasketList(o_m_id);
				//List basketList=vector.get(0)
				List basketList=(List)vector.get(0);
				//List goodsList=vector.get(1)
				List goodsList=(List)vector.get(1);
				//멤버 디비작업 객체 생성 memberdao
				MemberDao memberdao=new MemberDao();
				//MemberBean memberbean =메서드 호출 getMember(id)
				Member memberbean=memberdao.getMember(o_m_id);  // 기능추가
				//저장 basketList  goodsList memberbean
				request.setAttribute("basketList", basketList);
				request.setAttribute("goodsList", goodsList);
				request.setAttribute("memberbean", memberbean); 
				//이동 ./goods_order/goods_buy.jsp

				return "goods_buy";
			}
		}
