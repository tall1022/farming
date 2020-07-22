package admin.a_goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.AdminGoodsDAO;
import goods.model.Goods;

public class AdminGoodsModifyForm implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
			System.out.println("AdminGoodsModifyForm");
			//int num��������
			int g_num=Integer.parseInt(request.getParameter("g_num"));
			//���ü ���� agdao
			AdminGoodsDAO agdao=new AdminGoodsDAO();
			//GoodsBean goodsbean = �޼���ȣ�� getGoods(num)
			Goods goodsbean=agdao.getGoods(g_num);
			//���� goodsbean
			request.setAttribute("goodsbean", goodsbean);
			//�̵� ./admingoods/admin_goods_modify.jsp

			return "admin_goods_modify";
		}

}
