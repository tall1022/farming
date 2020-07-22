package admin.a_goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.AdminGoodsDAO;

public class AdminGoodsDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("AdminGoodsDeleteAction");
		//int num ��������
		int g_num = Integer.parseInt(request.getParameter("g_num"));
		//���ü ���� agdao
		AdminGoodsDAO agdao=new AdminGoodsDAO();
		//�޼���ȣ�� deleteGoods(num)
		agdao.deleteGoods(g_num);
		//�̵� ./GoodsList.ag

		return "GoodsList.ag";
	}

}
