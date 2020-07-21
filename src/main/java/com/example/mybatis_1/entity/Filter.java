package com.example.mybatis_1.entity;

public class Filter {
	private String searchTerm;

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public Filter() {
	}

	public Filter(String searchTerm) {
		super();
		this.searchTerm = searchTerm;
	}

}
