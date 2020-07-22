package member.model;

import java.sql.Date;

public class Member {
	private String m_id;
	private String m_password;
	private String m_name;
	private String m_email;
	private String m_tel;
	private String m_address1;
	private String m_address2;
	private String m_zipcode;
	private String m_rrn;
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_password() {
		return m_password;
	}
	public void setM_password(String m_password) {
		this.m_password = m_password;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_tel() {
		return m_tel;
	}
	public void setM_tel(String m_tel) {
		this.m_tel = m_tel;
	}
	public String getM_address1() {
		return m_address1;
	}
	public void setM_address1(String m_address1) {
		this.m_address1 = m_address1;
	}
	public String getM_address2() {
		return m_address2;
	}
	public void setM_address2(String m_address2) {
		this.m_address2 = m_address2;
	}
	public String getM_zipcode() {
		return m_zipcode;
	}
	public void setM_zipcode(String m_zipcode) {
		this.m_zipcode = m_zipcode;
	}
	public String getM_rrn() {
		return m_rrn;
	}
	public void setM_rrn(String m_rrn) {
		this.m_rrn = m_rrn;
	}
	public Date getM_reg_date() {
		return m_reg_date;
	}
	public void setM_reg_date(Date m_reg_date) {
		this.m_reg_date = m_reg_date;
	}
	public String getM_del() {
		return m_del;
	}
	public void setM_del(String m_del) {
		this.m_del = m_del;
	}
	private Date m_reg_date;
	private String m_del;
	
}
