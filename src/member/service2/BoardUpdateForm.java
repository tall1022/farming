package member.service2;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.dao.BoardDao;
import board.model.Board;

public class BoardUpdateForm implements CommandProcess{
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		BoardDao bd = BoardDao.getInstance();
		Board board = bd.select(num);
		request.setAttribute("num",num);
		request.setAttribute("pageNum",pageNum);
		request.setAttribute("board",board);
		return "boardUpdateForm";
	}

}
