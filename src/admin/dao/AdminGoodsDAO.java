package admin.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import goods.model.Goods;
import member.dao.MemberDao;

public class AdminGoodsDAO {
	// 占쎈탵�뜮袁⑸염野껓옙
	private static AdminGoodsDAO instance = new AdminGoodsDAO();
	public AdminGoodsDAO() {} 
	public static AdminGoodsDAO getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception {
		Connection con = null;
		// String url="jdbc:mysql://localhost:3306/jspdb";
		// String dbuser="jspid";
		// String dbpass="jsppass";
		// 1占쎈뼊�⑨옙 占쎈굡占쎌뵬占쎌뵠甕곌쑬以덌옙�쐭
		// Class.forName("com.mysql.jdbc.Driver");
		// 2占쎈뼊�⑨옙 占쎈탵�뜮袁⑸염野껓옙
		// con=DriverManager.getConnection(url,dbuser,dbpass);

		// �뚣끇苑뽳옙�∽옙占� : 占쎈쑓占쎌뵠占쎄숲甕곗쥙�뵠占쎈뮞占쏙옙 占쎈염野껉퀡留� Connection 揶쏆빘猿쒐몴占� 沃섎챶�봺 占쎄문占쎄쉐占쎈릭占쎈연
		// 占쏙옙占쎈꺗占쎈퓠 占쏙옙占쎌삢占쎈퉸 占쎈あ�⑨옙 占쎈툡占쎌뒄占쎈막占쎈르筌띾뜄�뼄 占쎌뵠 占쏙옙占쎈퓠 占쎌젔域뱀눛釉�占쎈연 Connection揶쏆빘猿� 占쎄텢占쎌뒠
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		con = ds.getConnection();
		return con;
	}

	// insertGoods(gBean)筌롫뗄苑뚳옙諭�
	public void insertGoods(Goods gBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		int num = 0;
		try {
		
			con = getConnection();
			sql = "select max(g_num) from goods";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			} else {
				num = 1;
			}
			//(g_num,g_kind,g_name,g_content,g_size,g_color,g_amount,g_price,g_image,g_best,g_date)
			// 3 sql insert
			sql = "insert into goods values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, gBean.getG_kind());
			pstmt.setString(3, gBean.getG_name());
			pstmt.setString(4, gBean.getG_content());
			pstmt.setString(5, gBean.getG_size());
			pstmt.setString(6, gBean.getG_color());
			pstmt.setInt(7, gBean.getG_amount());
			pstmt.setInt(8, gBean.getG_price());
			pstmt.setString(9, gBean.getG_image());
			pstmt.setInt(10, gBean.getG_best());
			// 4 占쎈뼄占쎈뻬
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
	}// 筌롫뗄苑뚳옙諭�

	// getGoodsList()筌롫뗄苑뚳옙諭�
	public List getGoodsList() {
		List goodsList = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		try {
			// 1,2 占쎈탵�뜮袁⑸염野껓옙
			con = getConnection();
			// 3 sql
			sql = "select * from goods";
			pstmt = con.prepareStatement(sql);
			// 4 rs 占쎈뼄占쎈뻬 占쏙옙占쎌삢
			rs = pstmt.executeQuery();
			// 5 rs占쎈쑓占쎌뵠占쎄숲 占쎌뿳占쎌몵筌롳옙 占쎌쁽獄쏅뗀�돭 揶쏆빘猿� 占쎄문占쎄쉐 gBean
			// rs => 占쎌쁽獄쏅뗀�돭 筌롢끇苡�癰귨옙占쎈땾 占쏙옙占쎌삢 => goodsList 占쎈립燁삼옙 占쏙옙占쎌삢
			while (rs.next()) {
				Goods gBean = new Goods();
				gBean.setG_amount(rs.getInt("g_amount"));
				gBean.setG_best(rs.getInt("g_best"));
				gBean.setG_kind(rs.getString("g_kind"));
				gBean.setG_color(rs.getString("g_color"));
				gBean.setG_content(rs.getString("g_content"));
				gBean.setG_reg_date(rs.getDate("g_reg_date"));
				gBean.setG_image(rs.getString("g_image"));
				gBean.setG_name(rs.getString("g_name"));
				gBean.setG_num(rs.getInt("g_num"));
				gBean.setG_price(rs.getInt("g_price"));
				gBean.setG_size(rs.getString("g_size"));
				// 占쎌쁽獄쏅뗀�돭 => 獄쏄퀣肉� 占쎈립燁삼옙 占쏙옙占쎌삢
				goodsList.add(gBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return goodsList;
	}

	// modifyGoods(GoodsBean goodsbean)
	public void modifyGoods(Goods goodsbean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			// 1,2 占쎈탵�뜮袁⑸염野껓옙
			con = getConnection();
			// 3 sql num 占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉
			// category name price color amount size best content占쎈땾占쎌젟
			sql = "update goods set g_kind=?,g_name=?,g_price=?,g_color=?,g_amount=?,g_size=?,g_best=?,g_content=? where g_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsbean.getG_kind());
			pstmt.setString(2, goodsbean.getG_name());
			pstmt.setInt(3, goodsbean.getG_price());
			pstmt.setString(4, goodsbean.getG_color());
			pstmt.setInt(5, goodsbean.getG_amount());
			pstmt.setString(6, goodsbean.getG_size());
			pstmt.setInt(7, goodsbean.getG_best());
			pstmt.setString(8, goodsbean.getG_content());
			pstmt.setInt(9, goodsbean.getG_num());
			// 4占쎈뼄占쎈뻬
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
	}

	// deleteGoods(int num)
	public void deleteGoods(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			// 1,2 占쎈탵�뜮袁⑸염野껓옙
			con = getConnection();
			// 3 sql num占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎄맒占쎈�� 占쎄텣占쎌젫
			sql = "delete from goods where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			// 4 占쎈뼄占쎈뻬
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
	}

	// getGoods(int num)
	public Goods getGoods(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		Goods goodsbean = null;
		try {
			// 1,2 占쎈탵�뜮袁⑸염野껓옙
			con = getConnection();
			// 3 sql num占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎄맒占쎈�뱄옙�젟癰귨옙 揶쏉옙占쎌죬占쎌궎疫뀐옙
			sql = "select * from goods where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			// 4 rs 占쎈뼄占쎈뻬 占쏙옙占쎌삢
			rs = pstmt.executeQuery();
			// 5 占쎈쑓占쎌뵠占쎄숲 占쎌뿳占쎌몵筌롳옙 占쎌쁽獄쏅뗀�돭揶쏆빘猿� 占쎄문占쎄쉐
			// rs => 占쎌쁽獄쏅뗀�돭 占쏙옙占쎌삢
			if (rs.next()) {
				goodsbean = new Goods();
				goodsbean.setG_amount(rs.getInt("amount"));
				goodsbean.setG_best(rs.getInt("best"));
				goodsbean.setG_kind(rs.getString("category"));
				goodsbean.setG_color(rs.getString("color"));
				goodsbean.setG_content(rs.getString("content"));
				goodsbean.setG_reg_date(rs.getDate("date"));
				goodsbean.setG_image(rs.getString("image"));
				goodsbean.setG_name(rs.getString("name"));
				goodsbean.setG_num(rs.getInt("num"));
				goodsbean.setG_price(rs.getInt("price"));
				goodsbean.setG_size(rs.getString("size"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return goodsbean;
	}
}