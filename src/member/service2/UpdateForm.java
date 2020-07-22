package member.service2;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.dao.MemberDao;
import member.model.Member;

public class UpdateForm implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name= request.getParameter("name");
		String tel= request.getParameter("tel");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String zipcode =request.getParameter("zipcode");
		String rrn = request.getParameter("rrn");
		MemberDao dao = MemberDao.getInstance();
		Member member = dao.select(id);
		request.setAttribute("member",member);
		request.setAttribute("id",id);
		request.setAttribute("password",password);
		request.setAttribute("name",name);
		request.setAttribute("tel",tel);
		request.setAttribute("address1",address1);
		request.setAttribute("address2",address2);
		request.setAttribute("zipcode",zipcode);
		request.setAttribute("rrn",rrn);
		return "updateForm";
	}

}
