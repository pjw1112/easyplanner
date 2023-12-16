package com.company.dto;

public class Answer_dto {

	private int a_index;
	private String a_date;
	private String a_content;
	private int q_index;

	public Answer_dto() {
		super();
	}

	public Answer_dto(int a_index, String a_date, String a_content, int q_index) {
		super();
		this.a_index = a_index;
		this.a_date = a_date;
		this.a_content = a_content;
		this.q_index = q_index;
	}

	@Override
	public String toString() {
		return "answer_dto [a_index=" + a_index + ", a_date=" + a_date + ", a_content=" + a_content + ", q_index="
				+ q_index + "]";
	}

	public int getA_index() {
		return a_index;
	}

	public void setA_index(int a_index) {
		this.a_index = a_index;
	}

	public String getA_date() {
		return a_date;
	}

	public void setA_date(String a_date) {
		this.a_date = a_date;
	}

	public String getA_content() {
		return a_content;
	}

	public void setA_content(String a_content) {
		this.a_content = a_content;
	}

	public int getQ_index() {
		return q_index;
	}

	public void setQ_index(int q_index) {
		this.q_index = q_index;
	}

}
