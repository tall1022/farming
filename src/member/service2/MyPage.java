package member.service2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.dao.MemberDao;
import member.model.Member;
import member.service2.CommandProcess;

public class MyPage implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id= (String)session.getAttribute("id");
		MemberDao md= MemberDao.getInstance();
		Member member= md.select(id);
		request.setAttribute("member",member);
		return "myPage";
	}

}
