package goods.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goods.dao.GoodsDAO;

public class GoodsListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		//�ѱ�ó��
				try {
					request.setCharacterEncoding("utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// item �Ķ���� ��������
				String g_kind=request.getParameter("g_kind");
				if(g_kind==null){
					g_kind="all";
				}
				//����۾� ���� net.goods.db  GoodsDAO
				//���ü ���� gdao
				GoodsDAO gdao=new GoodsDAO();
				//List goodsList =�޼���ȣ��  getGoodsList();
				List goodsList=gdao.getGoodsList(g_kind);
				//���� goodsList
				request.setAttribute("goodsList", goodsList);
				//�̵� ./goods/goods_list.jsp
				return "goods_list";
	}

}
