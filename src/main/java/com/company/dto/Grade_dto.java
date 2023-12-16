package com.company.dto;

public class Grade_dto {

	private int u_grade;
	private String u_grade_name;

	public Grade_dto() {
		super();
	}

	public Grade_dto(int u_grade, String u_grade_name) {
		super();
		this.u_grade = u_grade;
		this.u_grade_name = u_grade_name;
	}

	@Override
	public String toString() {
		return "grade_dto [u_grade=" + u_grade + ", u_grade_name=" + u_grade_name + "]";
	}

	public int getU_grade() {
		return u_grade;
	}

	public void setU_grade(int u_grade) {
		this.u_grade = u_grade;
	}

	public String getU_grade_name() {
		return u_grade_name;
	}

	public void setU_grade_name(String u_grade_name) {
		this.u_grade_name = u_grade_name;
	}

}
