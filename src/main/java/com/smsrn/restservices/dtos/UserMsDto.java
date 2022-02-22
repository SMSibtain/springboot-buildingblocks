package com.smsrn.restservices.dtos;

public class UserMsDto {

	private Long id;
	private String userName;
	private String emailAddres;

	public UserMsDto() {
	}

	public UserMsDto(Long id, String userName, String emailAddres) {
		super();
		this.id = id;
		this.userName = userName;
		this.emailAddres = emailAddres;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailAddres() {
		return emailAddres;
	}

	public void setEmailAddres(String emailAddres) {
		this.emailAddres = emailAddres;
	}
}
