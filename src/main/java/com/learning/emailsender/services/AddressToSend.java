package com.learning.emailsender.services;

import jakarta.mail.Address;

public class AddressToSend extends Address {
	
	private String address;
	
	public AddressToSend(String address) {
		this.address = address;
	}

	@Override
	public String getType() {
		return "Email";
	}

	@Override
	public String toString() {
		return this.address;
	}

	@Override
	public boolean equals(Object address) {
		try {
			AddressToSend downCasting = (AddressToSend) address;
			return downCasting == this;
			
		} catch (Exception e) {
			return false;
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
