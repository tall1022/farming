package member.service2;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.dao.BoardDao;
public class BoardDeleteAction implements CommandProcess {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		BoardDao bd = BoardDao.getInstance();
		int result  = bd.delete(num);
		request.setAttribute("pageNum",pageNum);
		request.setAttribute("result",result);
		return "boardDelete";		
	}
}
