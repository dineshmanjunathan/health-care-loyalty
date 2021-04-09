package com.hc.app.vo;


public class AppointmentVo {

	private Long id;
	private Long category;
	private Long doctor;
	private String doctorName;
	private String appointmentDate;
	private String appointmentTime;
	private Long patientID;
	private String description;
	private String doctorId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCategory() {
		return category;
	}
	public void setCategory(Long categoryId) {
		this.category = categoryId;
	}
	public Long getDoctor() {
		return doctor;
	}
	public void setDoctor(Long doctor) {
		this.doctor = doctor;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPatientID() {
		return patientID;
	}
	public void setPatientID(Long patientID) {
		this.patientID = patientID;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
	
}