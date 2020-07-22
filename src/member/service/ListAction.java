package member.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.dao.BoardDao;
import board.model.Board;
public class ListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int rowPerPage = 10;   // �븳�럹�씠吏��뿉 10媛� row瑜� 蹂댁뿬二쇱옄
		int pagePerBlock = 10; // �븳釉붾줉�뿉 10媛� �럹�씠吏�瑜� 蹂댁뿬二쇰떎
		String pageNum = request.getParameter("pageNum");
		if (pageNum==null || pageNum.equals(""))
			pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		// �떆�옉踰덊샇 : (�럹�씠吏�踰덊샇 - 1) * �럹�씠吏��떦 媛��닔 + 1
		int startRow = (currentPage - 1) * rowPerPage + 1;
		// �걹踰덊샇   : �떆�옉踰덊샇 + �럹�씠吏��떦媛쒖닔 �� 1
		int endRow = startRow + rowPerPage - 1;
		BoardDao bd = BoardDao.getInstance();
		List<Board> list = bd.list(startRow, endRow); 
		int total = bd.total();
		int tot = total - startRow + 1;
		// 珥� �럹�씠吏��닔 �냼�닽�젏 �씠�븯�뿉 �닽�옄媛� �엳�쑝硫� 1�럹�씠吏� 利앷�
		int totalPage = (int)Math.ceil((double)total/rowPerPage);
		// �쁽�옱�럹�씠吏� - (�쁽�옱�럹�씠吏� - 1)% 釉붾줉�떦 媛��닔
		int startPage = currentPage - (currentPage - 1) % pagePerBlock;
		// �떆�옉�럹�씠吏� + 釉붾줉�떦�럹�씠吏� �닔 -1
		int endPage = startPage + pagePerBlock - 1;
		// endPage�뒗 totalPage蹂대떎 �겕硫� �븞�맂�떎
		if (endPage > totalPage) endPage = totalPage;
		
		request.setAttribute("list",list);
		request.setAttribute("tot",tot);
		request.setAttribute("startPage",startPage);
		request.setAttribute("pagePerBlock",pagePerBlock);
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("endPage",endPage);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPage", totalPage);
		return "list";
	}

}
