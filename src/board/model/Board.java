package board.model;

import java.sql.Date;

public class Board {
	private int bd_num;
	private String bd_writer;
	private String bd_subject;
	private String bd_content;
	private String bd_email;
	private int bd_readcount;
	private String bd_password;
	private int bd_ref;
	private int bd_re_step;
	private int bd_re_level;
	private String bd_ip;
	private Date bd_reg_date;
	private String bd_del;
	
	public int getBd_num() {
		return bd_num;
	}
	public void setBd_num(int bd_num) {
		this.bd_num = bd_num;
	}
	public String getBd_writer() {
		return bd_writer;
	}
	public void setBd_writer(String bd_writer) {
		this.bd_writer = bd_writer;
	}
	public String getBd_subject() {
		return bd_subject;
	}
	public void setBd_subject(String bd_subject) {
		this.bd_subject = bd_subject;
	}
	public String getBd_content() {
		return bd_content;
	}
	public void setBd_content(String bd_content) {
		this.bd_content = bd_content;
	}
	public String getBd_email() {
		return bd_email;
	}
	public void setBd_email(String bd_email) {
		this.bd_email = bd_email;
	}
	public int getBd_readcount() {
		return bd_readcount;
	}
	public void setBd_readcount(int bd_readcount) {
		this.bd_readcount = bd_readcount;
	}
	public String getBd_password() {
		return bd_password;
	}
	public void setBd_password(String bd_password) {
		this.bd_password = bd_password;
	}
	public int getBd_ref() {
		return bd_ref;
	}
	public void setBd_ref(int bd_ref) {
		this.bd_ref = bd_ref;
	}
	public int getBd_re_step() {
		return bd_re_step;
	}
	public void setBd_re_step(int bd_re_step) {
		this.bd_re_step = bd_re_step;
	}
	public int getBd_re_level() {
		return bd_re_level;
	}
	public void setBd_re_level(int bd_re_level) {
		this.bd_re_level = bd_re_level;
	}
	public String getBd_ip() {
		return bd_ip;
	}
	public void setBd_ip(String bd_ip) {
		this.bd_ip = bd_ip;
	}
	public Date getBd_reg_date() {
		return bd_reg_date;
	}
	public void setBd_reg_date(Date bd_reg_date) {
		this.bd_reg_date = bd_reg_date;
	}
	public String getBd_del() {
		return bd_del;
	}
	public void setBd_del(String bd_del) {
		this.bd_del = bd_del;
	}


	
}
