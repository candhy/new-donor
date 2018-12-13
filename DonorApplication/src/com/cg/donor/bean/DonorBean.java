package com.cg.donor.bean;

import java.sql.Date;

public class DonorBean {
	private String donorid;
	private String donorname;
	private String phonenumber;
	private String address;
	private double donorAmount;
	private Date donationdate;
	
	
	public String getDonorid() {
		return donorid;
	}
	public void setDonorid(String donorid) {
		this.donorid = donorid;
	}
	public String getDonorname() {
		return donorname;
	}
	public void setDonorname(String donorname) {
		this.donorname = donorname;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getDonorAmount() {
		return donorAmount;
	}
	public void setDonorAmount(double donorAmount) {
		this.donorAmount = donorAmount;
	}
	public Date getDonationdate() {
		return donationdate;
	}
	public void setDonationdate(Date donationdate) {
		this.donationdate = donationdate;
	}
	@Override
	public String toString() {
		return "DonorBean [donorid=" + donorid + ", donorname=" + donorname + ", phonenumber=" + phonenumber
				+ ", address=" + address + ", donorAmount=" + donorAmount + ", donationdate=" + donationdate + "]";
	}
	
	
	

}
