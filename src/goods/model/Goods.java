package goods.model;

import java.sql.Date;

public class Goods {
	private int g_num;
	private String g_kind; /*category */
	private String g_name;
	private String g_content;
	private String g_size;
	private String g_color;
	private int g_amount ;
	private int g_price ;
	private String g_image;
	private int g_best ;
	private Date g_reg_date;
	
	public int getG_num() {
		return g_num;
	}
	public void setG_num(int g_num) {
		this.g_num = g_num;
	}
	public String getG_kind() {
		return g_kind;
	}
	public void setG_kind(String g_kind) {
		this.g_kind = g_kind;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_content() {
		return g_content;
	}
	public void setG_content(String g_content) {
		this.g_content = g_content;
	}
	public String getG_size() {
		return g_size;
	}
	public void setG_size(String g_size) {
		this.g_size = g_size;
	}
	public String getG_color() {
		return g_color;
	}
	public void setG_color(String g_color) {
		this.g_color = g_color;
	}
	public int getG_amount() {
		return g_amount;
	}
	public void setG_amount(int g_amount) {
		this.g_amount = g_amount;
	}
	public int getG_price() {
		return g_price;
	}
	public void setG_price(int g_price) {
		this.g_price = g_price;
	}
	public String getG_image() {
		return g_image;
	}
	public void setG_image(String g_image) {
		this.g_image = g_image;
	}
	public int getG_best() {
		return g_best;
	}
	public void setG_best(int g_best) {
		this.g_best = g_best;
	}
	public Date getG_reg_date() {
		return g_reg_date;
	}
	public void setG_reg_date(Date g_reg_date) {
		this.g_reg_date = g_reg_date;
	}
}
