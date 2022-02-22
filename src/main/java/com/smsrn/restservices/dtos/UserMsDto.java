package com.smsrn.restservices.dtos;

public class UserMsDto {

	private Long id;
	private String userName;
	private String emailaddress;
	private String rolename;

	public UserMsDto() {
	}

	public UserMsDto(Long id, String userName, String emailaddress, String rolename) {
		super();
		this.id = id;
		this.userName = userName;
		this.emailaddress = emailaddress;
		this.rolename = rolename;
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

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
