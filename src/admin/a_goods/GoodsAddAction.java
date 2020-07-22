package admin.a_goods;



import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import admin.dao.AdminGoodsDAO;
import goods.model.Goods;

public class GoodsAddAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		// upload 폴더 만들기  5*1024*1024
		// MultipartRequest 객체 생성
		ServletContext context=request.getServletContext();
		String realPath=context.getRealPath("/upload");
		int maxSize=5*1024*1024;
		MultipartRequest multi = null;

		try {
			multi = new MultipartRequest(request, realPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 자바빈 파일 만들기 net.admin.goods.db 파일 GoodsBean
		//자바빈 파일 생성 GoodsBean gBean
		Goods gBean=new Goods();
		// 폼 => 자바빈 저장
		gBean.setG_amount(Integer.parseInt(multi.getParameter("g_amount")));
		gBean.setG_best(0);
		gBean.setG_kind(multi.getParameter("g_kind"));
		gBean.setG_color(multi.getParameter("g_color"));
		gBean.setG_content(multi.getParameter("g_content"));
		String image=multi.getFilesystemName("file1")+","+multi.getFilesystemName("file2")+","+multi.getFilesystemName("file3")+","+multi.getFilesystemName("file4");
		gBean.setG_image(image);
		gBean.setG_name(multi.getParameter("g_name"));
		gBean.setG_price(Integer.parseInt(multi.getParameter("g_price")));
		gBean.setG_size(multi.getParameter("g_size"));
		System.out.println(realPath);
		System.out.println(image);
		// 디비 파일 만들기 net.admin.goods.db 파일 AdminGoodsDAO
		// 객체 생성 AdminGoodsDAO agdao
		AdminGoodsDAO agdao=new AdminGoodsDAO();
		//객체 생성 메서드호출 insertGoods(gBean)
		agdao.insertGoods(gBean);
		// 이동 ./GoodsList.ag
		return "admin_goods_list";
	}

}
