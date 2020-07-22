package admin.a_goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.AdminGoodsDAO;
import goods.model.Goods;

public class AdminGoodsModifyForm implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
			System.out.println("AdminGoodsModifyForm");
			//int num가져오기
			int g_num=Integer.parseInt(request.getParameter("g_num"));
			//디비객체 생성 agdao
			AdminGoodsDAO agdao=new AdminGoodsDAO();
			//GoodsBean goodsbean = 메서드호출 getGoods(num)
			Goods goodsbean=agdao.getGoods(g_num);
			//저장 goodsbean
			request.setAttribute("goodsbean", goodsbean);
			//이동 ./admingoods/admin_goods_modify.jsp

			return "admin_goods_modify";
		}

}
