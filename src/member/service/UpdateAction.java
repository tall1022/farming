package member.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.dao.BoardDao;
import board.model.Board;

public class UpdateAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		Board board= new Board();
		board.setBd_num(Integer.parseInt(request.getParameter("num")));
		board.setBd_subject(request.getParameter("subject"));
		board.setBd_writer(request.getParameter("writer"));
		board.setBd_content(request.getParameter("content"));
		String pageNum =request.getParameter("pageNum");
		BoardDao bd= BoardDao.getInstance();
		int result = bd.update(board);
		
		request.setAttribute("pageNum",pageNum);
		request.setAttribute("result",result);
		request.setAttribute("board",board);
		return "update";
	}

}
