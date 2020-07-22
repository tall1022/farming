package member.service2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.dao.MemberDao;

public class DeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MemberDao dao = MemberDao.getInstance();
		int result = dao.delete(id);
		request.setAttribute("result",result);
		request.setAttribute("id",id);
		request.setAttribute("password",password);
		System.out.println("result:"+result);
		return "delete";
	}

}
