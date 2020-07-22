package member.service2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardDao;
import board.model.Board;

public class BoardDeleteForm implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int num=Integer.parseInt(request.getParameter("num"));
		BoardDao bd = BoardDao.getInstance();
		Board board = bd.select(num);
		request.setAttribute("board",board);
		request.setAttribute("num",num);
		request.setAttribute("pageNum", pageNum);
		return "boardDeleteForm";
	}

}
