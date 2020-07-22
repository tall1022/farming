package basket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import basket.model.Basket;
import goods.dao.GoodsDAO;
import goods.model.Goods;

public class BasketDAO {
	private static BasketDAO instance = new BasketDAO();

	public BasketDAO() {}

	public static BasketDAO getInstance() {
		return instance;
	}

	// ��񿬰�
	private Connection getConnection() throws Exception {
		Connection con = null;
//				String url="jdbc:mysql://localhost:3306/jspdb";
//				String dbuser="jspid";
//				String dbpass="jsppass";
		// 1�ܰ� ����̹��δ�
//				Class.forName("com.mysql.jdbc.Driver");
		// 2�ܰ� ��񿬰�
//				con=DriverManager.getConnection(url,dbuser,dbpass);

		// Ŀ�ؼ�Ǯ : �����ͺ��̽��� ����� Connection ��ü�� �̸� �����Ͽ�
		// Ǯ�ӿ� ������ �ΰ� �ʿ��Ҷ����� �� Ǯ�� �����Ͽ� Connection��ü ���
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		con = ds.getConnection();
		return con;
	}

	// basketAdd(basketbean)
	public void basketAdd(Basket basketbean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int b_num = 0;
		try {
			// 1,2 ��񿬰�
			con = getConnection();
			// num ���ϱ� max(num)+1
			sql = "select max(b_num) from basket";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				b_num = rs.getInt(1) + 1;
			} else {
				b_num = 1;
			}
			// 3 sql insert now()
			sql = "insert into basket values(?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			pstmt.setString(2, basketbean.getB_m_id());
			pstmt.setInt(3, basketbean.getB_g_num());
			pstmt.setInt(4, basketbean.getB_g_amount());
			pstmt.setString(5, basketbean.getB_g_size());
			pstmt.setString(6, basketbean.getB_g_color());
			// 4 ����
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

	// checkGoods(basketbean)
	public int checkGoods(Basket basketbean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int check = 0;
		try {
			// 1,2 ��񿬰�
			con = getConnection();
			// 3 select
			// ���� b_m_id b_g_num b_g_size b_g_color
			sql = "select * from basket where b_m_id=? and b_g_num=? and b_g_size=? and b_g_color=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, basketbean.getB_m_id());
			pstmt.setInt(2, basketbean.getB_g_num());
			pstmt.setString(3, basketbean.getB_g_size());
			pstmt.setString(4, basketbean.getB_g_color());
			// 4 rs ��������
			rs = pstmt.executeQuery();
			// 5 rs ������ ������ check=1
			// 3 sql update
			// ���� b_m_id b_g_num b_g_size b_g_color
			// ���� b_g_amount
			// 4 ����
			if (rs.next()) {
				check = 1;
				sql = "update basket set b_g_amount=b_g_amount+? where b_m_id=? and b_g_num=? and b_g_size=? and b_g_color=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, basketbean.getB_g_amount());
				pstmt.setString(2, basketbean.getB_m_id());
				pstmt.setInt(3, basketbean.getB_g_num());
				pstmt.setString(4, basketbean.getB_g_size());
				pstmt.setString(5, basketbean.getB_g_color());
				pstmt.executeUpdate();
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

		return check;
	}

	// getBasketList(id)
	public Vector getBasketList(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		String sql = "";
		Vector vector = new Vector();
		List basketList = new ArrayList();
		List goodsList = new ArrayList();
		try {
			// 1,2 ��񿬰�
			con = getConnection();
			// 3 sql id�� �ش��ϴ� ��ٱ��� ���� ��������
			sql = "select * from basket where b_m_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 4 rs ���� ����
			rs = pstmt.executeQuery();
			// 5 rs ������ ������ ��ٱ��� �ڹٺ� ��ü ����
			// rs => �ڹٺ� ���� =>basketList �迭��ĭ ����
			// rs => b_g_num
			// 3 sql b_g_num �ش��ϴ� ��ǰ������������
			// 4 rs2 pstmt2 ��������
			// 5 rs2������ ������ ��ǰ �ڹٺ� ��ü ����
			// rs2=>�ڹٺ� ���� => goodsList��ĭ ����
			while (rs.next()) {
				Basket bb = new Basket();
				bb.setB_date(rs.getDate("b_date"));
				bb.setB_g_amount(rs.getInt("b_g_amount"));
				bb.setB_g_color(rs.getString("b_g_color"));
				bb.setB_g_num(rs.getInt("b_g_num"));
				bb.setB_g_size(rs.getString("b_g_size"));
				bb.setB_m_id(rs.getString("b_m_id"));
				bb.setB_num(rs.getInt("b_num"));
				basketList.add(bb);
				sql = "select * from goods where num=?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, bb.getB_g_num());
				rs2 = pstmt2.executeQuery();
				if (rs2.next()) {
					Goods gb = new Goods();
					gb.setG_price(rs2.getInt("g_price"));
					gb.setG_name(rs2.getString("g_name"));
					gb.setG_image(rs2.getString("g_image"));
					goodsList.add(gb);
				}
			}
			// vector ù��° ĭ basketList ����
			// vector �ι�° ĭ goodsList ����
			vector.add(basketList);
			vector.add(goodsList);
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
		return vector;
	}

	// basketDelete(b_num);
	public void basketDelete(int b_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			// 1,2 ��񿬰�
			con = getConnection();
			// 3 sql b_num �� �ش��ϴ� ��ٱ��� ����
			sql = "delete from basket where b_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			// 4 ����
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

	// deleteBasket(String id)
	public void deleteBasket(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			// 1,2 ��񿬰�
			con = getConnection();
			// 3 sql delete b_m_id�ش��ϴ� ��ٱ��� ����
			sql = "delete from basket where b_m_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 4 ����
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

}