package goods.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goods.dao.GoodsDAO;
import goods.model.Goods;

public class GoodsMainListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String g_kind=request.getParameter("g_kind");
		int count=Integer.parseInt(request.getParameter("count"));
		// 디비객체 생성 gdao
		GoodsDAO gdao=new GoodsDAO();
		//자바빈 gBean = getGoods(num) 호출
		Goods gBean=gdao.getRecentGoods(g_kind,count);
		//저장 "gBean"
		request.setAttribute("gBean", gBean);
		//이동  ./goods/goods_detail.jsp
		return "goods_main_list.jsp";
	}

}
