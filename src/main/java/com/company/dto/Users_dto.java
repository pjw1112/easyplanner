package com.company.dto;

public class Users_dto {

	private int u_index;
	private String u_id;
	private String u_pass;
	private String u_email;
	private String u_birth;
	private String u_join_date;
	private int u_grade;
	private String u_kakaoid;

	public Users_dto() {
		super();
	}

	public Users_dto(int u_index, String u_id, String u_pass, String u_email, String u_birth, String u_join_date,
			int u_grade) {
		super();
		this.u_index = u_index;
		this.u_id = u_id;
		this.u_pass = u_pass;
		this.u_email = u_email;
		this.u_birth = u_birth;
		this.u_join_date = u_join_date;
		this.u_grade = u_grade;
	}

	@Override
	public String toString() {
		return "users_dto [u_index=" + u_index + ", u_id=" + u_id + ", u_pass=" + u_pass + ", u_email=" + u_email
				+ ", u_birth=" + u_birth + ", u_join_date=" + u_join_date + ", u_grade=" + u_grade + "]";
	}

	public int getU_index() {
		return u_index;
	}

	public void setU_index(int u_index) {
		this.u_index = u_index;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getU_pass() {
		return u_pass;
	}

	public void setU_pass(String u_pass) {
		this.u_pass = u_pass;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_birth() {
		return u_birth;
	}

	public void setU_birth(String u_birth) {
		this.u_birth = u_birth;
	}

	public String getU_join_date() {
		return u_join_date;
	}

	public void setU_join_date(String u_join_date) {
		this.u_join_date = u_join_date;
	}

	public int getU_grade() {
		return u_grade;
	}

	public void setU_grade(int u_grade) {
		this.u_grade = u_grade;
	}

	public String getU_kakaoid() {
		return u_kakaoid;
	}

	public void setU_kakaoid(String u_kakaoid) {
		this.u_kakaoid = u_kakaoid;
	}

}
