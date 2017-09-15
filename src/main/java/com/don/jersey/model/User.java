package com.don.jersey.model;


public class User {


    private String username;

	private long id;

    public User() {

    }

    public User(long id, String username) {
        this.username = username;
		this.id = id;
    }




	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}