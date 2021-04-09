package com.hc.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

@Entity
@Table(name = "t_appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String appointmentDate;
	private String appointmentTime;
	private String description;
	
	@ManyToOne
	@Lazy
	@JoinColumn(name = "patient_id")
	private UserMaster userMaster;
	
	@ManyToOne
	@Lazy
	@JoinColumn(name = "doctor_id", referencedColumnName = "id")
	private DoctorDetails doctorDetails;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public UserMaster getUserMaster() {
		return userMaster;
	}
	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}
	public String getDescription() {
		return description;
	}
	public void setDesciption(String description) {
		this.description = description;
	}
	public DoctorDetails getDoctorDetails() {
		return doctorDetails;
	}
	public void setDoctorDetails(DoctorDetails doctorDetails) {
		this.doctorDetails = doctorDetails;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

