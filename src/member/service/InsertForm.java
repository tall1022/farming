package member.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardDao;
import board.model.Board;
public class InsertForm implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int num=0, ref=0, re_level=0, re_step=0; 
		String no = request.getParameter("num");
		String pageNum = request.getParameter("pageNum");
		if (no != null) {
			num = Integer.parseInt(no);
			BoardDao bd = BoardDao.getInstance();
			Board board = bd.select(num);
			ref = board.getBd_ref();
			re_step = board.getBd_re_step();
			re_level = board.getBd_re_level();
		}
		request.setAttribute("num", num);
		request.setAttribute("ref", ref);
		request.setAttribute("re_level", re_level);
		request.setAttribute("re_step", re_step);
		request.setAttribute("pageNum", pageNum);
		return "insertForm";

	}
}
