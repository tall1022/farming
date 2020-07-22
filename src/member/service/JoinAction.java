package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.dao.MemberDao;
import member.model.Member;

public class JoinAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		Member member=new Member();
		member.setM_id(request.getParameter("id"));
		member.setM_password(request.getParameter("password"));
		member.setM_name(request.getParameter("name"));
		member.setM_tel(request.getParameter("tel"));
		member.setM_address1(request.getParameter("address1"));
		member.setM_address2(request.getParameter("address2"));
		member.setM_zipcode(request.getParameter("zipcode"));
		member.setM_rrn(request.getParameter("rrn"));
		member.setM_del(request.getParameter("del"));
		MemberDao md=MemberDao.getInstance();
		int result=md.insert(member);
		request.setAttribute("result",result);
		return "join";
	}

}
