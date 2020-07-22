package goods.dao;
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
import order.dao.OrderDAO;



public class GoodsDAO {

	private static GoodsDAO instance = new GoodsDAO();
	public GoodsDAO() {} 
	public static GoodsDAO getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception {
		Connection con = null;
		// String url="jdbc:mysql://localhost:3306/jspdb";
		// String dbuser="jspid";
		// String dbpass="jsppass";
		// Class.forName("com.mysql.jdbc.Driver");
		// 2�떒怨� �뵒鍮꾩뿰寃�
		// con=DriverManager.getConnection(url,dbuser,dbpass);

		
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysqlDB");
		con = ds.getConnection();
		return con;
	}

	// getGoodsList()
	public List getGoodsList(String g_kind) {
		List goodsList = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// String sql="";
		StringBuffer sql = new StringBuffer();
		try {
			con = getConnection();
			sql.append("select * from goods ");
			if (g_kind.equals("all")) {
				
			} else if (g_kind.equals("best")) {
				sql.append(" where best=?");
			} else {
				sql.append(" where category=?");
			}
			
			pstmt = con.prepareStatement(sql.toString());
			if (g_kind.equals("all")) {
			} else if (g_kind.equals("best")) {
				pstmt.setInt(1, 1);
			} else {
				pstmt.setString(1, g_kind);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Goods gBean = new Goods();
				gBean.setG_amount(rs.getInt("g_amount"));
				gBean.setG_best(rs.getInt("g_best"));
				gBean.setG_kind(rs.getString("g_kind"));
				gBean.setG_color(rs.getString("g_color"));
				gBean.setG_content(rs.getString("g_content"));
				gBean.setG_reg_date(rs.getDate("g_date"));
				gBean.setG_image(rs.getString("g_image"));
				gBean.setG_name(rs.getString("g_name"));
				gBean.setG_num(rs.getInt("g_num"));
				gBean.setG_price(rs.getInt("g_price"));
				gBean.setG_size(rs.getString("g_size"));

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
	
	public Goods getRecentGoods(String g_kind,int count) {
        Connection con = null;  
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Goods gBean = null;       
        int i = 0;        
        try { con = getConnection();     
        // 가장 최근에 등록된 책 3권 조회
            pstmt = con.prepareStatement(
               "select * from (select * from Goods where g_kind" +
            	" = ? order by g_reg_date desc) where rownum <= ?");
            pstmt.setString(1, g_kind);
            pstmt.setInt(2, count);          
        	rs = pstmt.executeQuery();
        	if (rs.next()) {
        		gBean = new Goods();
				gBean.setG_amount(rs.getInt("g_amount"));
				gBean.setG_best(rs.getInt("g_best"));
				gBean.setG_kind(rs.getString("g_kind"));
				gBean.setG_color(rs.getString("g_color"));
				gBean.setG_content(rs.getString("g_content"));
				gBean.setG_reg_date(rs.getDate("g_date"));
				gBean.setG_image(rs.getString("g_image"));
				gBean.setG_name(rs.getString("g_name"));
				gBean.setG_num(rs.getInt("g_num"));
				gBean.setG_price(rs.getInt("g_price"));
				gBean.setG_size(rs.getString("g_size"));
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
		return gBean;
	}
        

	// getGoods(num)
	public Goods getGoods(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		Goods gBean = null;
		try {
			con = getConnection();
			sql = "select * from goods where g_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				gBean = new Goods();
				gBean.setG_amount(rs.getInt("g_amount"));
				gBean.setG_best(rs.getInt("g_best"));
				gBean.setG_kind(rs.getString("g_kind"));
				gBean.setG_color(rs.getString("g_color"));
				gBean.setG_content(rs.getString("g_content"));
				gBean.setG_reg_date(rs.getDate("g_date"));
				gBean.setG_image(rs.getString("g_image"));
				gBean.setG_name(rs.getString("g_name"));
				gBean.setG_num(rs.getInt("g_num"));
				gBean.setG_price(rs.getInt("g_price"));
				gBean.setG_size(rs.getString("g_size"));
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
		return gBean;
	}
	public int total() throws Exception {
		int total=0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from goods";
		try {
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			total=rs.getInt(1);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch (Exception e) {
			}
		}
		return total;
	}
	// updateAmount(basketList)
	// public void updateAmount(List basketList){
	// Connection con= null;
	// PreparedStatement pstmt=null;
	// ResultSet rs=null;
	// String sql="";
	// try {
	public List<Goods> getGoodsList(int startRow, int endRow) throws Exception {
		List<Goods> list = new ArrayList<Goods>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rowNum rn, a.* from (select * from goods order by g_reg_date) a) where rn between ? and ?";
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, endRow);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			Goods gBean = new Goods();
			gBean.setG_amount(rs.getInt("g_amount"));
			gBean.setG_best(rs.getInt("g_best"));
			gBean.setG_kind(rs.getString("g_kind"));
			gBean.setG_color(rs.getString("g_color"));
			gBean.setG_content(rs.getString("g_content"));
			gBean.setG_reg_date(rs.getDate("g_date"));
			gBean.setG_image(rs.getString("g_image"));
			gBean.setG_name(rs.getString("g_name"));
			gBean.setG_num(rs.getInt("g_num"));
			gBean.setG_price(rs.getInt("g_price"));
			gBean.setG_size(rs.getString("g_size"));
			list.add(gBean);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs != null) {
				rs.close();
				}
				if(pstmt != null) {
				pstmt.close();
				}
				if(conn != null) {
				conn.close();
				}
			}catch (Exception e) {}
		}
		return list;
	}
	

	// con=getConnection();
	// // basketList for
	// //3 sql update

	// //4 �떎�뻾
	// for(int i=0;i<basketList.size();i++){
	// BasketBean basketbean=(BasketBean)basketList.get(i);
	// //3 sql
	// sql="update goods set amount=amount-? where num=?";
	// pstmt=con.prepareStatement(sql);
	// pstmt.setInt(1, basketbean.getB_g_amount());
	// pstmt.setInt(2, basketbean.getB_g_num());

	// pstmt.executeUpdate();
	// }//for
	// } catch (Exception e) {
	// e.printStackTrace();
	// }finally{
	// if(rs!=null)try{rs.close();}catch(SQLException ex){}
	// if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
	// if(con!=null)try{con.close();}catch(SQLException ex){}
	// }
	// }
	/*public ArrayList<Book> getBooks(String book_kind) {
        Connection conn = null;     
        PreparedStatement pstmt = null;
        ResultSet rs = null;        
        ArrayList<Book> bookList=new ArrayList<Book>(); 
        String sql1="select * from book order by reg_date desc";
        String sql2 = "select * from book where " +
        		"book_kind = ? order by reg_date desc";
        try { conn = getConnection();           
            if(book_kind.equals("all")){
            	 pstmt = conn.prepareStatement(sql1);
            }else{
                pstmt = conn.prepareStatement(sql2);
                pstmt.setString(1, book_kind);
            }
        	rs = pstmt.executeQuery();
        	while(rs.next()){
                 Book book= new Book();
                 book.setBook_id(rs.getInt("book_id"));
                 book.setBook_kind(rs.getString("book_kind"));
                 book.setBook_title(rs.getString("book_title"));
                 book.setBook_price(rs.getInt("book_price"));
                 book.setBook_count(rs.getInt("book_count"));
                 book.setAuthor(rs.getString("author"));
                 book.setPublishing_com(rs.getString("publishing_com"));
                 book.setPublishing_date(rs.getString("publishing_date"));
                 book.setBook_image(rs.getString("book_image"));
                 book.setDiscount_rate(rs.getInt("discount_rate"));
                 book.setReg_date(rs.getDate("reg_date"));
                    
                 bookList.add(book);
        	}
        } catch(Exception e) {System.out.println(e.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return bookList;
    }
	
	// 쇼핑몰 메인에 표시하기 위해서 사용하는 분류별 신간책목록을 얻어내는 메소드
	public Goods getRecentGoods(String book_kind,int count) {
        Connection conn = null;  PreparedStatement pstmt = null;
        ResultSet rs = null;
        Book bookList[]=null;       int i = 0;        
        try { conn = getConnection();     
        // 가장 최근에 등록된 책 3권 조회
            pstmt = conn.prepareStatement(
               "select * from (select * from book where book_kind" +
            	" = ? order by reg_date desc) where rownum <= ?");
            pstmt.setString(1, book_kind);
            pstmt.setInt(2, count);          
        	rs = pstmt.executeQuery();
            if (rs.next()) {
                bookList = new Book[count];
                do{  Book book= new Book();
                     book.setBook_id(rs.getInt("book_id"));
                     book.setBook_kind(rs.getString("book_kind"));
                     book.setBook_title(rs.getString("book_title"));
                     book.setBook_price(rs.getInt("book_price"));
                     book.setBook_count(rs.getInt("book_count"));
                     book.setAuthor(rs.getString("author"));
                     book.setPublishing_com(rs.getString("publishing_com"));
                     book.setPublishing_date(rs.getString("publishing_date"));
                     book.setBook_image(rs.getString("book_image"));
                     book.setDiscount_rate(rs.getInt("discount_rate"));
                     book.setReg_date(rs.getDate("reg_date"));
                     bookList[i]=book;
                     i++;
			    }while(rs.next());
			}
        } catch(Exception e) { System.out.println(e.getMessage());  } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return bookList;
    }
    	public List<Book> list(int startRow, int endRow) {
		List<Book> list = new ArrayList<Book>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rowNum rn, a.* from (select * from board order by ref desc,re_step) a) where rn between ? and ?";
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, endRow);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			Book book = new Book();
			book.setBook_id(rs.getInt("book_id"));
			book.setBook_kind(rs.getString("book_kind"));
			book.setBook_title(rs.getString("book_title"));
			book.setBook_price(rs.getInt("book_price"));
			book.setBook_count(rs.getInt("book_count"));
			book.setAuthor(rs.getString("author"));
			book.setPublishing_com(rs.getString("publishing_com"));
			book.setPublishing_date(rs.getString("publishing_date"));
			book.setBook_image(rs.getString("book_image"));
			book.setBook_content(rs.getString("book_content"));
			book.setDiscount_rate(rs.getInt("discount_rate"));
			book.setSell_count(rs.getInt("sell_count"));
			book.setReg_date(rs.getDate("reg_date"));
			book.setDel(rs.getString("del"));
			list.add(book);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs != null) {
				rs.close();
				}
				if(pstmt != null) {
				pstmt.close();
				}
				if(conn != null) {
				conn.close();
				}
			}catch (Exception e) {}
		}
		return list;
	}
	*/
	// 모니터의 최신등록제품의 g_num을 찾아서 내용 호출하는 함수
	// 위의 주석처리 함수 처리하기.
}