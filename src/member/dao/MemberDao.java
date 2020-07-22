package member.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

import member.model.Member;

	public class MemberDao {
		private static MemberDao instance = new MemberDao();
		public MemberDao() {} 
		public static MemberDao getInstance() {
			return instance;
		}
	
		private Connection getConnection() {
			Connection conn = null;
			try {
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)
						ctx.lookup("java:comp/env/jdbc/OracleDB");
				conn = ds.getConnection();
			}catch (Exception e) {
				System.out.println("연결에러 : "+e.getMessage());
			}
			return conn;
		}

	public Member select(String id) {
		Connection conn = getConnection(); 	
			Member member = null;
			PreparedStatement pstmt = null;		
			ResultSet rs = null;
			String sql = "select * from member where M_id=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					member = new Member();
					member.setM_id (rs.getString("m_id "));
					member.setM_password(rs.getString("m_password "));
					member.setM_name(rs.getString("m_name "));
//					member.setEmail(rs.getString("email"));
					member.setM_tel(rs.getString("m_tel "));
					member.setM_address1(rs.getString("m_address1 "));
					member.setM_address2(rs.getString("m_address2 "));
					member.setM_address2(rs.getString("m_zipcode "));
					member.setM_rrn(rs.getString("m_rrn "));
					/* member.setSex(rs.getString("sex")); */
					/* member.setRec_email(rs.getString("rec_email")); */
					/* member.setRec_sms(rs.getString("rec_sms")); */
					/* member.setReg_date(rs.getDate("reg_date")); */
					member.setM_del(rs.getString("m_del "));
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally {
				try{
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
					if (conn  != null) conn.close();
				}catch (Exception e) {		}
			}
			return member;
		}
		
		public int insert(Member member) {
			Connection conn = getConnection(); 	
			int result = 0;
			PreparedStatement pstmt = null;		
			String sql="insert into member values(?,?,?,?,?,?,?,?,sysdate,'n')";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getM_id());
				pstmt.setString(2, member.getM_password());
				pstmt.setString(3, member.getM_name());
				pstmt.setString(4, member.getM_rrn());
				pstmt.setString(5, member.getM_address1());
				pstmt.setString(6, member.getM_address2());
				pstmt.setString(7, member.getM_zipcode());
				pstmt.setString(8, member.getM_tel());
				/* pstmt.setString(10, member.getSex()); */
				/*
				 * pstmt.setString(10, member.getRec_email()); pstmt.setString(11,
				 * member.getRec_sms());
				 */
				result = pstmt.executeUpdate();
			}catch(Exception e){System.out.println(e.getMessage());
			}finally {
				try{if (pstmt != null) pstmt.close();
					if (conn  != null) conn.close();
				}catch (Exception e) {		}
			}
			return result;
		}
		// 이부분은 추가했으면한다. 로그인창에 체크박스 체크한다음에 로그,인하면 메니처 체크 실행
		public int managerCheck(String id, String passwd) {
			Connection conn = null;   PreparedStatement pstmt = null;
			ResultSet rs= null;       String dbpasswd="";		int x=-1;        
			try { conn = getConnection();            
	            pstmt = conn.prepareStatement(
	            	"select a_m_pass from manager where a_m_id=?");
	            pstmt.setString(1, id);            
	            rs= pstmt.executeQuery();
				if(rs.next()){
					dbpasswd= rs.getString("a_m_pass"); 
					if(dbpasswd.equals(passwd))		x= 1; //인증 성공
					else			x= 0; //비밀번호 틀림
				}else				x= -1;//해당 아이디 없음			
	        } catch(Exception e) {System.out.println(e.getMessage());
	        } finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
			return x;
		}
		public int loginChk(String id, String password) {
			Connection conn = getConnection(); 	
			int result = 0;
			PreparedStatement pstmt = null;		
			ResultSet rs = null;
			String sql="select M_password from member where M_id=? and M_del='n'";
			try{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					String dbPass=rs.getString("m_password"); 
					if (dbPass.equals(password)) {
						result = 1;
					}else {
						result = 0;
					}
				} else {
					result = -1; 
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally {
				try{if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
					if (conn  != null) conn.close();
				}catch (Exception e) {		}
			}
			return result;
		}
		public int delete(String id) {
			Connection conn = getConnection(); 	
			int result = 0;
			PreparedStatement pstmt = null;		
			String sql="update member set M_del='y' where M_id=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				result = pstmt.executeUpdate();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally {
				try{if (pstmt != null) pstmt.close();
					if (conn  != null) conn.close();
				}catch (Exception e) {		}
			}
			return result;
		}
		
		public int update(Member member) {
			Connection conn = getConnection(); 	
			int result = 0;
			PreparedStatement pstmt = null;	
			String sql="update member set M_password=?, M_name=?,"
					+ "M_address1=?,M_addres2=?,M_tel=? where M_id=?";
			try {
				pstmt = conn.prepareStatement(sql);			
				pstmt.setString(1, member.getM_password());
				pstmt.setString(2, member.getM_name());
				pstmt.setString(3, member.getM_address1());
				pstmt.setString(4, member.getM_address2());
				pstmt.setString(5, member.getM_tel());
				pstmt.setString(6, member.getM_del());
				result = pstmt.executeUpdate();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally {
				try{
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn  != null) {
						conn.close();
					}
				}catch (Exception e) {		}
			}
			return result;
		}
		public List<Member> list(int startRow, int endRow) {
			Connection conn = getConnection(); 	
			List<Member> list = new ArrayList<Member>();
			PreparedStatement pstmt = null;		
			ResultSet rs = null;
			String sql="select * from (select rowNum rn, a.* from ("
				+ "select * from member order by m_reg_date desc) a)"
				+ " where rn between ? and ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Member member = new Member();
					member.setM_id(rs.getString("m_id"));
					member.setM_password(rs.getString("m_password"));
					member.setM_name(rs.getString("m_name "));
					member.setM_address1(rs.getString("m_address1"));
					member.setM_address2(rs.getString("m_address2"));
					member.setM_tel(rs.getString("m_tel"));
					member.setM_reg_date(rs.getDate("m_reg_date"));
					member.setM_del(rs.getString("m_del"));
					list.add(member);
				}
			}catch(Exception e){System.out.println(e.getMessage());
			}finally {
				try{
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn  != null) {
						conn.close();
					}
				}catch (Exception e) {		}
			}
			return list;
		}
		
		public int total() {
			int total = 0;
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;       
			String sql = "select count(*) from member";
			try{pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				rs.next();
				total = rs.getInt(1);			
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try{
					if (rs    != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn  != null) {
						conn.close();
					}
				}catch (Exception e) {	}
			}
			return total;
		}
		public Member getMember(String a) {
			Member mb = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				// 1단계 드라이버 로드
				// 2단계 db연결 => Connection con 객체 저장
				con = getConnection();

				// 3단계 sql id에 해당하는 pass 가져오기
				String sql = "select * from member where m_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, a);

				// 4단계 rs=실행결과 저장
				rs = pstmt.executeQuery();

				if (rs.next()) {
					mb = new Member();
					mb.setM_id(rs.getString("m_id"));
					mb.setM_password(rs.getString("m_password"));
					mb.setM_name(rs.getString("m_name"));
					mb.setM_reg_date(rs.getDate("reg_date"));
					mb.setM_rrn(rs.getString("m_rrn"));
					mb.setM_email(rs.getString("m_email"));

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 객체닫기
				// 객체생성 닫기(기억장소를 회수하는 작업)
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException ex) {
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException ex) {
					}
				}
				if (con != null) {
					try {
						pstmt.close();
					} catch (SQLException ex) {
					}
				}
			}
			return mb;
		}// getmember
	}





