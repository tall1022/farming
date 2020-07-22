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
		// ���ü ���� gdao
		GoodsDAO gdao=new GoodsDAO();
		//�ڹٺ� gBean = getGoods(num) ȣ��
		Goods gBean=gdao.getRecentGoods(g_kind,count);
		//���� "gBean"
		request.setAttribute("gBean", gBean);
		//�̵�  ./goods/goods_detail.jsp
		return "goods_main_list.jsp";
	}

}
