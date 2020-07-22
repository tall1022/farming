package member.service2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.dao.MemberDao;
import member.model.Member;

public class DeleteForm implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MemberDao dao = MemberDao.getInstance();
		Member member= dao.select(id);
		request.setAttribute("member",member);
		request.setAttribute("id",id);
		request.setAttribute("password",password);
		return "deleteForm";
	}

}
