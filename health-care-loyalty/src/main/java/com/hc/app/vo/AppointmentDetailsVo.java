package com.hc.app.vo;

public class AppointmentDetailsVo {
	
	private String patientName;
	private String mobileNumber;
	private Long patientId;
	private String categoryDesc;
	private String doctorName;
	private String appointedDatetime;
	
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getAppointedDatetime() {
		return appointedDatetime;
	}
	public void setAppointedDatetime(String appointedDatetime) {
		this.appointedDatetime = appointedDatetime;
	}
}
