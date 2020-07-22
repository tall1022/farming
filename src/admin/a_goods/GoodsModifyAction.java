package admin.a_goods;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.AdminGoodsDAO;
import goods.model.Goods;

public class GoodsModifyAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("GoodsModifyAction");
		//�ѱ�ó��
		//�ڹٺ� ��ü ���� goodsbean
		Goods goodsbean=new Goods();
		//�� => �ڹٺ� ������� ����
		// num  category name price color amount size best content
		goodsbean.setG_num(Integer.parseInt(request.getParameter("g_num")));
		goodsbean.setG_kind(request.getParameter("g_kind"));
		goodsbean.setG_name(request.getParameter("g_name"));
		goodsbean.setG_price(Integer.parseInt(request.getParameter("g_price")));
		goodsbean.setG_color(request.getParameter("g_color"));
		goodsbean.setG_amount(Integer.parseInt(request.getParameter("g_amount")));
		goodsbean.setG_size(request.getParameter("g_size"));
		goodsbean.setG_best(Integer.parseInt(request.getParameter("g_best")));
		goodsbean.setG_content(request.getParameter("g_content"));
		//���ü ���� agdao
		AdminGoodsDAO agdao=new AdminGoodsDAO();
		// �޼���ȣ�� modifyGoods(goodsbean)
		agdao.modifyGoods(goodsbean);
		//�̵� ./GoodsList.ag

		return "GoodsList.ag";
	}

}
