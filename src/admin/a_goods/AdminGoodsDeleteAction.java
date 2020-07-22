package admin.a_goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.AdminGoodsDAO;

public class AdminGoodsDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("AdminGoodsDeleteAction");
		//int num 가져오기
		int g_num = Integer.parseInt(request.getParameter("g_num"));
		//디비객체 생성 agdao
		AdminGoodsDAO agdao=new AdminGoodsDAO();
		//메서드호출 deleteGoods(num)
		agdao.deleteGoods(g_num);
		//이동 ./GoodsList.ag

		return "GoodsList.ag";
	}

}
