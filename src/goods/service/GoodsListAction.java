package goods.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goods.dao.GoodsDAO;

public class GoodsListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		//한글처리
				try {
					request.setCharacterEncoding("utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// item 파라미터 가저오기
				String g_kind=request.getParameter("g_kind");
				if(g_kind==null){
					g_kind="all";
				}
				//디비작업 파일 net.goods.db  GoodsDAO
				//디비객체 생성 gdao
				GoodsDAO gdao=new GoodsDAO();
				//List goodsList =메서드호출  getGoodsList();
				List goodsList=gdao.getGoodsList(g_kind);
				//저장 goodsList
				request.setAttribute("goodsList", goodsList);
				//이동 ./goods/goods_list.jsp
				return "goods_list";
	}

}
