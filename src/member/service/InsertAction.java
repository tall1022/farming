package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardDao;
import board.model.Board;

public class InsertAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		Board board = new Board();
		board.setBd_num(Integer.parseInt(request.getParameter("num")));
		board.setBd_writer(request.getParameter("writer"));
		board.setBd_subject(request.getParameter("subject"));
		board.setBd_content(request.getParameter("content"));
		board.setBd_password(request.getParameter("password"));
		board.setBd_ref(Integer.parseInt(request.getParameter("ref")));
		board.setBd_re_level(Integer.parseInt(request.getParameter("re_level")));
		board.setBd_re_step(Integer.parseInt(request.getParameter("re_step")));
		String pageNum = request.getParameter("pageNum");
		board.setBd_ip(request.getRemoteAddr());
		BoardDao bd = BoardDao.getInstance();
		int result= bd.insert(board);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		
		return "insert";
	}

}
