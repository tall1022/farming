package order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import admin.dao.AdminGoodsDAO;
import basket.model.Basket;
import goods.model.Goods;
import order.model.Order;

public class OrderDAO {
	//��񿬰�
	private static OrderDAO instance = new OrderDAO();
	public OrderDAO() {} 
	public static OrderDAO getInstance() {
		return instance;
	}
		private Connection getConnection() throws Exception{
			Connection con=null;
			Context init=new InitialContext();
			DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
			con=ds.getConnection();
			return con;
		}
		
		//addOrder(orderbean,basketList,goodsList)
		public void addOrder(Order orderbean,List basketList,List goodsList){
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql="";
			ResultSet rs=null;
			int o_num=0;  //�Ϸù�ȣ
			int trade_num=0; //�ֹ���ȣ
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			try {
				//1,2 ��񿬰�
				con=getConnection();
				// o_num �Ϸù�ȣ���ϱ�
				sql="select max(o_num) from g_order";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()){
					o_num=rs.getInt(1)+1;
				}else{
					o_num=1;
				}
				trade_num=o_num;
				// for basketList goodsList
				//3 sql insert  
				//4 ����
				for(int i=0;i<basketList.size();i++){
			Basket basketbean=(Basket)basketList.get(i);
			Goods goodsbean=(Goods)goodsList.get(i);
			 sql="insert into g_order values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?)";
			 pstmt=con.prepareStatement(sql);
			 pstmt.setInt(1, o_num);
			 pstmt.setString(2, sdf.format(cal.getTime()).toString()+"-"+trade_num);//�ֹ���ȣ
			 pstmt.setInt(3, basketbean.getB_g_num());
			 pstmt.setString(4, goodsbean.getG_name());
			 pstmt.setInt(5, basketbean.getB_g_amount());
			 pstmt.setString(6, basketbean.getB_g_size());
			 pstmt.setString(7, basketbean.getB_g_color());
			 pstmt.setString(8, orderbean.getO_m_id());
			 pstmt.setString(9, orderbean.getO_receive_name());
			 pstmt.setString(10, orderbean.getO_receive_addr1());
			 pstmt.setString(11, orderbean.getO_receive_addr2());
			 pstmt.setString(12, orderbean.getO_receive_phone());
			 pstmt.setString(13, orderbean.getO_receive_mobile());
			 pstmt.setString(14, orderbean.getO_memo());
			 pstmt.setInt(15, basketbean.getB_g_amount()*goodsbean.getG_price());
			 pstmt.setString(16, orderbean.getO_trade_type());
			 pstmt.setString(17, orderbean.getO_trade_payer());
			 pstmt.setString(18, "");//������ȣ
			 pstmt.setInt(19, 0); //�ֹ�����
			 pstmt.executeUpdate();
			 o_num++; //�Ϸù�ȣ����
				}//for
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)try{con.close();}catch(SQLException ex){}
			}
		}//
		
		//getOrderList(id)
		public List getOrderList(String id){
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql="";
			ResultSet rs=null;
			List orderList=new ArrayList();
			try {
				//1,2 ��񿬰�
				con =getConnection();
				//3 sql o_trade_num ,o_g_name,o_g_amount
				// o_g_size, o_g_color,  
				// sum(o_sum_money) as o_sum_money
				// o_trans_num , o_date, o_status
				// o_trade_type
				// g_order���̺� ���� o_m_id �����ϸ� 
				//  �׷� o_trade_num ���� o_trade_num ��������
				sql="select o_trade_num,o_g_name,o_g_amount,o_g_size,o_g_color,sum(o_sum_money) as o_sum_money,o_trans_num , o_date, o_status,o_trade_type from g_order where o_m_id=? group by o_trade_num order by o_trade_num desc";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				//4 rs ���� ���� 
				rs=pstmt.executeQuery();
				//5 rs������ ������ OrderBean orderbean��ü ����
				// rs=>�ڹٺ� ������� =>orderList ��ĭ ����
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
					orderList.add(orderbean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)try{con.close();}catch(SQLException ex){}
			}
			return orderList;
		}
		
		//orderDetail(trade_num)
		public List orderDetail(String trade_num){
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql="";
			ResultSet rs=null;
			List orderDetailList=new ArrayList();
			try {
				//1,2 ��񿬰�
				con =getConnection();
				//3 sql ���̺� g_order ���� o_trade_num
				sql="select * from g_order where o_trade_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, trade_num);
				//4 rs ��������
				rs=pstmt.executeQuery();
				//5 rs�� ������������ OrderBean orderbean��ü ����
		// rs => orderbean���� ���� => orderDetailList��ĭ����
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
					orderDetailList.add(orderbean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)try{con.close();}catch(SQLException ex){}
			}
			return orderDetailList;
		}
		
	}