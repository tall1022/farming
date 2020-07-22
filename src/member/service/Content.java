package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.CommandProcess;
import board.dao.BoardDao;
import board.model.Board;

public class Content implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		BoardDao bd = BoardDao.getInstance();
		bd.updateReadCount(num);
		Board board = bd.select(num);
		request.setAttribute("board",board);
		request.setAttribute("pageNum",pageNum);
		return "content";
	}
}