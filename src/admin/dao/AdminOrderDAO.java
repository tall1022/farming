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

import order.model.Order;


public class AdminOrderDAO {
	private Connection getConnection() throws Exception{
		Connection con=null;
//		String url="jdbc:mysql://localhost:3306/jspdb";
//		String dbuser="jspid";
//		String dbpass="jsppass";
		//1�ܰ� ����̹��δ�
//		Class.forName("com.mysql.jdbc.Driver");
		//2�ܰ� ��񿬰�
//		con=DriverManager.getConnection(url,dbuser,dbpass);
		
		//Ŀ�ؼ�Ǯ : �����ͺ��̽��� ����� Connection ��ü�� �̸� �����Ͽ�
		//Ǯ�ӿ� ������ �ΰ� �ʿ��Ҷ����� �� Ǯ�� �����Ͽ� Connection��ü ���
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		con=ds.getConnection();
		return con;
	}
	
	//getAdminOrderList()
	public List getAdminOrderList(){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		List adminOrderList=new ArrayList();
		try {
			//1,2 ��񿬰�
			con=getConnection();
			//3 sql ���̺� g_order 
			// sum(o_sum_money) as o_sum_money
			// �׷� o_trade_num ���� o_trade_num ��������
			sql="select o_trade_num,o_g_name,o_g_amount,o_g_size,o_g_color,sum(o_sum_money) as o_sum_money,o_trans_num , o_date, o_status,o_trade_type,o_m_id from g_order group by o_trade_num order by o_trade_num desc";
			pstmt=con.prepareStatement(sql);
			//4 rs ���� ��� ����
			rs=pstmt.executeQuery();
			//5 rs ������ ������ �ڹٺ� OrderBean orderbean��ü����
		// rs => orderbean������� ���� => adminOrderList��ĭ����
			while(rs.next()){
				Order orderbean=new Order();
				orderbean.setO_date(rs.getDate("o_date"));
				orderbean.setO_g_amount(rs.getInt("o_g_amount"));
				orderbean.setO_g_color(rs.getString("o_g_color"));
				orderbean.setO_g_name(rs.getString("o_g_name"));
				orderbean.setO_g_size(rs.getString("o_g_size"));
				orderbean.setO_trade_num(rs.getString("o_trade_num"));
				orderbean.setO_trans_num(rs.getInt("o_trans_num"));
				orderbean.setO_sum_money(rs.getInt("o_sum_money"));
				orderbean.setO_status(rs.getInt("o_status"));
				orderbean.setO_trade_type(rs.getString("o_trade_type"));
				orderbean.setO_m_id(rs.getString("o_m_id"));
				adminOrderList.add(orderbean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return adminOrderList;
	}
	//getAdminOrderDetail(trade_num)
	public List getAdminOrderDetail(String trade_num){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		List adminOrderDetail=new ArrayList();
		try {
			//1,2 ��񿬰�
			con=getConnection();
			//3 sql ���̺� g_order ���� o_trade_num
			sql="select * from g_order where o_trade_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, trade_num);
			//4 rs���� ����
			rs=pstmt.executeQuery();
			//5 rs������������ OrderBean orderbean��ü����
			//rs => orderbean����������� 
			//=> adminOrderDetail ��ĭ ����
			while(rs.next()){
				Order orderbean=new Order();
				orderbean.setO_date(rs.getDate("o_date"));
				orderbean.setO_g_amount(rs.getInt("o_g_amount"));
				orderbean.setO_g_color(rs.getString("o_g_color"));
				orderbean.setO_g_name(rs.getString("o_g_name"));
				orderbean.setO_g_size(rs.getString("o_g_size"));
				orderbean.setO_trade_num(rs.getString("o_trade_num"));
				orderbean.setO_trans_num(rs.getInt("o_trans_num"));
				orderbean.setO_sum_money(rs.getInt("o_sum_money"));
				orderbean.setO_status(rs.getInt("o_status"));
				orderbean.setO_trade_type(rs.getString("o_trade_type"));
				adminOrderDetail.add(orderbean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return adminOrderDetail;
	}
public void updateOrder(Order orderbean){
	Connection con=null;
	PreparedStatement pstmt=null;
	String sql="";
	ResultSet rs=null;
	try{
		con=getConnection();
		sql="update g_order set o_status=? , o_trans_num=? where o_trade_num=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, orderbean.getO_status());
		pstmt.setInt(2,orderbean.getO_trans_num() );
		pstmt.setString(3,orderbean.getO_trade_num() );
		pstmt.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(rs!=null)try{rs.close();}catch(SQLException ex){}
		if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
		if(con!=null)try{con.close();}catch(SQLException ex){}
	}
	
	
}	
}//Ŭ����