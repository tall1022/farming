package goods.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import goods.dao.GoodsDAO;
import goods.model.Goods;

public class GoodsDetailAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// int num �Ķ���� ��������
				int g_num=Integer.parseInt(request.getParameter("g_num"));
				// ���ü ���� gdao
				GoodsDAO gdao=new GoodsDAO();
				//�ڹٺ� gBean = getGoods(num) ȣ��
				Goods gBean=gdao.getGoods(g_num);
				//���� "gBean"
				request.setAttribute("gBean", gBean);
				//�̵�  ./goods/goods_detail.jsp
				return "goods_detail";
	}

}
