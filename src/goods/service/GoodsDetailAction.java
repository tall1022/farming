package goods.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import goods.dao.GoodsDAO;
import goods.model.Goods;

public class GoodsDetailAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// int num 파라미터 가져오기
				int g_num=Integer.parseInt(request.getParameter("g_num"));
				// 디비객체 생성 gdao
				GoodsDAO gdao=new GoodsDAO();
				//자바빈 gBean = getGoods(num) 호출
				Goods gBean=gdao.getGoods(g_num);
				//저장 "gBean"
				request.setAttribute("gBean", gBean);
				//이동  ./goods/goods_detail.jsp
				return "goods_detail";
	}

}
