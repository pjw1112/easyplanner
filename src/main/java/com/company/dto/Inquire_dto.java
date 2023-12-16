package com.company.dto;

public class Inquire_dto {

	private int q_index;
	private String q_date;
	private String q_content;
	private int u_index;

	public Inquire_dto() {
		super();
	}

	public Inquire_dto(int q_index, String q_date, String q_content, int u_index) {
		super();
		this.q_index = q_index;
		this.q_date = q_date;
		this.q_content = q_content;
		this.u_index = u_index;
	}

	@Override
	public String toString() {
		return "inquire_dto [q_index=" + q_index + ", q_date=" + q_date + ", q_content=" + q_content + ", u_index="
				+ u_index + "]";
	}

	public int getQ_index() {
		return q_index;
	}

	public void setQ_index(int q_index) {
		this.q_index = q_index;
	}

	public String getQ_date() {
		return q_date;
	}

	public void setQ_date(String q_date) {
		this.q_date = q_date;
	}

	public String getQ_content() {
		return q_content;
	}

	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}

	public int getU_index() {
		return u_index;
	}

	public void setU_index(int u_index) {
		this.u_index = u_index;
	}

}
