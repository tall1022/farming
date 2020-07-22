package board.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import board.model.Board;

public class BoardDao{
	private static BoardDao instance = new BoardDao();
	private BoardDao() {}
	public static BoardDao getInstance() {
		return instance;
	}
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	public List<Board> list(int startRow, int endRow){
		List<Board> list = new ArrayList<Board>();
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
			Board board = new Board();
			board.setBd_num(rs.getInt("bd_num "));
			board.setBd_writer(rs.getString("bd_writer "));
			board.setBd_subject(rs.getString("bd_subject "));
			board.setBd_content(rs.getString("bd_content "));
		//	board.setEmail(rs.getString("email"));
			board.setBd_readcount(rs.getInt("bd_readcount "));
			board.setBd_password(rs.getString("bd_password "));
			board.setBd_ref(rs.getInt("bd_ref "));
			board.setBd_re_step(rs.getInt("bd_re_step "));
			board.setBd_re_level(rs.getInt("bd_re_level "));
			board.setBd_ip(rs.getString("bd_ip "));
			board.setBd_reg_date(rs.getDate("bd_reg_date "));
			board.setBd_del(rs.getString("bd_del "));
			list.add(board);
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
	
	public int insert(Board board) {
		int result =0 ;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlChk = "select min(re_step) from board where ref=? and re_step > ? and re_level <= ?";
		String sqlNum = "select nvl(max(num),0) + 1 from board";
		String sql = "insert into board values(?,?,?,?,?,0,?,?,?,?,?,sysdate,'n')";
		String sqlUp = "update board set re_step=re_step+1 where ref=? and re_step >=?";
		String sqlStep = "select max(re_step)+1 from board where ref=?";
		
		try {
			pstmt = conn.prepareStatement(sqlNum);
			rs = pstmt.executeQuery();
			rs.next();
			int number = rs.getInt(1);
			rs.close();
			pstmt.close();
			if(board.getBd_num() > 0) {
				pstmt = conn.prepareStatement(sqlChk);
				pstmt.setInt(1,board.getBd_ref());
				pstmt.setInt(2,board.getBd_re_step());
				pstmt.setInt(3,board.getBd_re_level());
				rs = pstmt.executeQuery();
				String chk = rs.getString(1);
				rs.close();
				pstmt.close();
				if(chk != null) {
					int tempStep = Integer.parseInt(chk);
					pstmt = conn.prepareStatement(sqlUp);
					pstmt.setInt(1,board.getBd_ref());
					pstmt.setInt(2,tempStep);
					pstmt.executeUpdate();
					board.setBd_re_step(tempStep);
				}else {
					pstmt = conn.prepareStatement(sqlStep);
					pstmt.setInt(1,board.getBd_ref());
					rs=pstmt.executeQuery();
					rs.next();
					board.setBd_re_step(rs.getInt(1));
				}
				board.setBd_re_level(board.getBd_re_level()+1);
				pstmt.close();
			}else {
				board.setBd_ref(number);
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, board.getBd_writer());
			pstmt.setString(3, board.getBd_subject());
			pstmt.setString(4, board.getBd_content());
		//	pstmt.setString(5, board.getBd_email());
			pstmt.setString(6, board.getBd_password());
			pstmt.setInt(7, board.getBd_ref());
			pstmt.setInt(8, board.getBd_re_step());
			pstmt.setInt(9, board.getBd_re_level());
			pstmt.setString(10, board.getBd_ip());
			result = pstmt.executeUpdate();
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
		return result;
	}
	public void updateReadCount(int num) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql ="update board set readcount=readcount+1 where num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch (Exception e) {
			}
		}
	}
	public Board select(int num) {
		Board board = new Board();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String sql = "select * from board where num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setBd_num(rs.getInt("bd_num "));
				board.setBd_writer(rs.getString("bd_writer "));
				board.setBd_subject(rs.getString("bd_subject "));
				board.setBd_content(rs.getString("bd_content "));
			//	board.setBd_email(rs.getString("email"));
				board.setBd_readcount(rs.getInt("bd_readcount "));
				board.setBd_password(rs.getString("bd_password "));
				board.setBd_ref(rs.getInt("bd_ref "));
				board.setBd_re_step(rs.getInt("bd_re_step "));
				board.setBd_re_level(rs.getInt("bd_re_level "));
				board.setBd_ip(rs.getString("bd_ip "));
				board.setBd_reg_date(rs.getDate("bd_reg_date "));
				board.setBd_del(rs.getString("bd_del "));
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
			}catch (Exception e) {
			}
		}
		return board;
	}
	public int total() {
		int total=0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from board";
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
	public int update(Board board) {
		int result =0;
		Connection conn = getConnection();
		PreparedStatement pstmt =null;
		String sql ="update board set writer=?,subject=?,content=?,email=? where num=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,board.getBd_writer());
			pstmt.setString(2, board.getBd_subject());
			pstmt.setString(3, board.getBd_content());
			pstmt.setInt(4, board.getBd_num());
			result =pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch (Exception e) {
			}
		}
		return result;
	}
	public int delete(int num) {
		int result =0;
		Connection conn =getConnection();
		PreparedStatement pstmt=null;
		String sql ="update board set del='y' where num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				if(pstmt!= null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				
			}
		}
		return result;
	}
}