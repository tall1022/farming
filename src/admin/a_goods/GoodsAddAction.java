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
		// upload ���� �����  5*1024*1024
		// MultipartRequest ��ü ����
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
		
		// �ڹٺ� ���� ����� net.admin.goods.db ���� GoodsBean
		//�ڹٺ� ���� ���� GoodsBean gBean
		Goods gBean=new Goods();
		// �� => �ڹٺ� ����
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
		// ��� ���� ����� net.admin.goods.db ���� AdminGoodsDAO
		// ��ü ���� AdminGoodsDAO agdao
		AdminGoodsDAO agdao=new AdminGoodsDAO();
		//��ü ���� �޼���ȣ�� insertGoods(gBean)
		agdao.insertGoods(gBean);
		// �̵� ./GoodsList.ag
		return "admin_goods_list";
	}

}
