package admin.a_goods;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.AdminGoodsDAO;
import goods.dao.GoodsDAO;
import goods.model.Goods;

public class GoodsListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int rowPerPage = 10; // 한페이지에 10개 row를 보여주자
		int pagePerBlock = 10; // 한블록에 10개 페이지를 보여주다
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum.equals(""))
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		// 시작번호 : (페이지번호 - 1) * 페이지당 갯수 + 1
		int startRow = (currentPage - 1) * rowPerPage + 1;
		// 끝번호 : 시작번호 + 페이지당개수 – 1
		int endRow = startRow + rowPerPage - 1;
		GoodsDAO gs = GoodsDAO.getInstance();
		List<Goods> list=null;
		try {
			list = gs.getGoodsList(startRow, endRow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int total=0;;
		try {
			total = gs.total();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("거수자 발견?2");
		int tot = total - startRow + 1;
		// 총 페이지수 소숫점 이하에 숫자가 있으면 1페이지 증가
		int totalPage = (int) Math.ceil((double) total / rowPerPage);
		// 현재페이지 - (현재페이지 - 1)% 블록당 갯수
		int startPage = currentPage - (currentPage - 1) % pagePerBlock;
		// 시작페이지 + 블록당페이지 수 -1
		int endPage = startPage + pagePerBlock - 1;
		// endPage는 totalPage보다 크면 안된다
		if (endPage > totalPage)
			endPage = totalPage;
		
		request.setAttribute("list", list);
		request.setAttribute("tot", tot);
		request.setAttribute("startPage", startPage);
		request.setAttribute("pagePerBlock", pagePerBlock);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPage", totalPage);

		System.out.println("거수자 발견?3");
		System.out.println(list.size());
		// 디비객체 생성 AdminGoodsDAO agdao
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		// List goodsList = 메서드호출 getGoodsList()
		List<GoodsDAO> goodsList = agdao.getGoodsList();
		// goodsList 저장
		request.setAttribute("goodsList", goodsList);
		// 이동 ./admingoods/admin_goods_list.jsp

		return "admin_goods_list";
	}

}
