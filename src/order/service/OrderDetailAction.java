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
		//세션값 가져오기
				HttpSession session=request.getSession();
				String o_m_id=(String)session.getAttribute("m_id");
				//세션값 없으면 ./MemberLogin.me
				if(o_m_id==null){
					return "MemberLogin.me";
				}
				//자바빈 OrderBean 객체 생성 orderbean
				Order orderbean=new Order();
				//한글처리
				try {
					request.setCharacterEncoding("utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 폼 => 자바빈 저장
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
				orderbean.setO_trade_type("온라인입금");
				//장바구니 디비작업 객체 생성 basketdao
				BasketDAO basketdao=new BasketDAO();
				//Vector vector = 메서드호출 getBasketList(id)
				Vector vector=basketdao.getBasketList(o_m_id);
				//List basketList=vector.get(0)
				List basketList=(List)vector.get(0);
				//List goodsList=vector.get(1)
				List goodsList=(List)vector.get(1);
				
				//결제 연결  U+전자결제 유플러스결제 http://ecredit.uplus.co.kr/
				
				// net.order.db OrderDAO 객체생성 orderdao
				OrderDAO orderdao=new OrderDAO();
				//메서드 호출  => 주문정보저장
				// 메서드호출 addOrder(orderbean,basketList,goodsList)
				orderdao.addOrder(orderbean,basketList,goodsList);
				
				//메일,문자 전송
				
				//상품전체개수 수정goodsdao    updateAmount(basketList)
				GoodsDAO goodsdao=new GoodsDAO();
				//goodsdao.updateAmount(basketList);
				
				//장바구니 정보 삭제 basketdao//  deleteBasket(id)
				basketdao.deleteBasket(o_m_id);
				
				//이동 ./OrderList.or
				return "OrderList.or";
			}
		}