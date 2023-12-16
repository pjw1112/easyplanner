package com.company.dto;

public class Schedule_dto {
	private int s_index;
	private String start_date;
	private String end_date;
	private String content;
	private String create_date;
	private String ip;
	private int u_index;

	public Schedule_dto() {
		super();
	}

	public Schedule_dto(int s_index, String start_date, String end_date, String content, String create_date, String ip,
			int u_index) {
		super();
		this.s_index = s_index;
		this.start_date = start_date;
		this.end_date = end_date;
		this.content = content;
		this.create_date = create_date;
		this.ip = ip;
		this.u_index = u_index;
	}

	@Override
	public String toString() {
		return "schedule_dto [s_index=" + s_index + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", content=" + content + ", create_date=" + create_date + ", ip=" + ip + ", u_index=" + u_index + "]";
	}

	public int getS_index() {
		return s_index;
	}

	public void setS_index(int s_index) {
		this.s_index = s_index;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getU_index() {
		return u_index;
	}

	public void setU_index(int u_index) {
		this.u_index = u_index;
	}

}
