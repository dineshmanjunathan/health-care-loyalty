package com.hc.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hc.app.entity.Appointment;
import com.hc.app.entity.Category;
import com.hc.app.entity.DoctorDetails;
import com.hc.app.entity.UserMaster;
import com.hc.app.model.AppointmentRepository;
import com.hc.app.model.CategoryCodeRepository;
import com.hc.app.model.UserRepository;
import com.hc.app.vo.AppointmentVo;
import com.hc.app.vo.GuestAppointmentVo;
import com.hc.app.vo.TrackAppointmentVo;

@Controller
public class AppointmentController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private CategoryCodeRepository categoryCodeRepository;

	@RequestMapping("/appoinment")
	public String appoinment(HttpServletRequest request,ModelMap model) { 
		return "appointment";
	}


	@RequestMapping("/manage/time")
	public String manageTime(HttpServletRequest request,ModelMap model) { 
		return "manageTime";
	}

	@RequestMapping("/track/appoinment")
	public String trackAppoinment(HttpServletRequest request,ModelMap model) { 
		try {
			Iterable<Category> categoryList = categoryCodeRepository.findAll();
			model.addAttribute("categoryList", categoryList); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return "trackAppoinment";
	}

	@RequestMapping(value="/track/appoinment",method=RequestMethod.POST)
	public String trackAppoinmentSearch(TrackAppointmentVo trackVo,HttpServletRequest request,ModelMap model) { 
		try {
			model.addAttribute("appointmentTrackList", "true"); 

		} catch (Exception e) {
			e.printStackTrace();
		}  
		return "trackAppoinment";
	}

	@RequestMapping(value="/searchUser",method=RequestMethod.POST)
	public String searchUser(HttpServletRequest request,ModelMap model) { 
		try {
			UserMaster user=null;
			String searchOption=(String)request.getParameter("searchOption");
			String searchBy=(String)request.getParameter("searchBy");
			if("id".equals(searchOption)){
				user=userRepository.findById(Long.parseLong(searchBy.trim())).get();
			}else if("mobile".equals(searchOption)){
				user=userRepository.findByMobile(searchBy);
			}else {
				return "redirect:user";
			}
			model.addAttribute("user", user);
			Iterable<Category> categoryList = categoryCodeRepository.findAll();
			model.addAttribute("categoryList", categoryList); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return "appointment";
	}

	@RequestMapping("/appointmentListing")
	public String appointmentListing(HttpServletRequest request,ModelMap model) { 
		try {
			Iterable<Appointment> appointmentList = appointmentRepository.findAll();
			model.addAttribute("appointmentList", appointmentList); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return "appointmentListing";
	}

	@RequestMapping(value="/appointment/delete",method=RequestMethod.GET)
	public String appointmentDelete(@RequestParam("id")String id,HttpServletRequest request,ModelMap model) { 
		try {
			appointmentRepository.deleteById(Long.parseLong(id));
			model.addAttribute("deletesuccessmessage","Deleted Successfully"); 
			Iterable<Appointment>  appointmentList= appointmentRepository.findAll();
			model.addAttribute("appointmentList", appointmentList); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return "appointmentListing";
	}

	@RequestMapping(value="/appointment/edit",method=RequestMethod.GET)
	public String appointmentEdit(@RequestParam("id")String id,HttpServletRequest request,ModelMap model) { 
		try {
			Appointment appointment = appointmentRepository.findById(Long.parseLong(id)).get();
			AppointmentVo appointmentVo=new AppointmentVo();
			BeanUtils.copyProperties(appointmentVo, appointment);
			model.addAttribute("appointment", appointmentVo); 

			Iterable<UserMaster> userList = userRepository.findAll();
			model.addAttribute("userList", userList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "appointment";  
	}


	@RequestMapping(value="/appointment/edit",method=RequestMethod.POST)
	public String appointmentEditSubmit(HttpServletRequest request,AppointmentVo appointmentVo,ModelMap model) {
		Appointment appointment=new Appointment();
		try {
			BeanUtils.copyProperties(appointment, appointmentVo);
			appointmentRepository.save(appointment);
			Iterable<Appointment>  appointmentList= appointmentRepository.findAll();
			model.addAttribute("appointmentList", appointmentList); 
			model.addAttribute("successMessage","Successfully Edited Admin Record"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "appointmentListing";
	}	

	@RequestMapping(value="/appointment/save",method=RequestMethod.POST)
	public String appointmentSubmit(HttpServletRequest request,AppointmentVo appointmentVo,ModelMap model) {
		try {
			Appointment appointment=new Appointment();
			BeanUtils.copyProperties(appointment, appointmentVo);
			appointment.setUserMaster(userRepository.findById(Long.valueOf(appointmentVo.getPatientID())).get());
			DoctorDetails doctorDetails = new DoctorDetails();
			doctorDetails.setId(Long.parseLong(appointmentVo.getDoctorId()));
			appointment.setDoctorDetails(doctorDetails);
			appointmentRepository.save(appointment);
			Iterable<Appointment>  appointmentList= appointmentRepository.findAll();
			model.addAttribute("appointmentList", appointmentList); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "appointmentListing";
	}

	@RequestMapping(value = "/doctor/guestAppointment")
	public String guestAppointment(HttpServletRequest request,ModelMap model) {
		Iterable<Category> categoryList = categoryCodeRepository.findAll();
		model.addAttribute("categoryList", categoryList); 
		return "guestAppointment";
	}

	@RequestMapping(value="/appointment/guest/save",method=RequestMethod.POST)
	public String guestAppointmentSubmit(HttpServletRequest request,GuestAppointmentVo guestAppointmentVo,ModelMap model) {
		try {
			UserMaster userMaster = prepareUserMaster(guestAppointmentVo);
			UserMaster insertedUserMaster = userRepository.save(userMaster);
			Appointment appointment = prepareAppointment(guestAppointmentVo, insertedUserMaster);
			appointmentRepository.save(appointment);
			model.addAttribute("successMessage","Appointment Submitted Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}


	private Appointment prepareAppointment(final GuestAppointmentVo guestAppointmentVo, final UserMaster insertedUserMaster) {
		Appointment appointment=new Appointment();
		appointment.setUserMaster(insertedUserMaster);
		DoctorDetails doctorDetails = new DoctorDetails();
		doctorDetails.setId(Long.parseLong(guestAppointmentVo.getDoctorId()));
		appointment.setDoctorDetails(doctorDetails);
		appointment.setAppointmentDate(guestAppointmentVo.getAppointmentDate());
		appointment.setAppointmentTime(guestAppointmentVo.getAppointmentTime());
		appointment.setDesciption(guestAppointmentVo.getDescription());
		return appointment;
	}
	private UserMaster prepareUserMaster(final GuestAppointmentVo guestAppointmentVo) {
		UserMaster userMaster = new UserMaster();
		userMaster.setName(guestAppointmentVo.getName());
		userMaster.setAddress(guestAppointmentVo.getAddress());
		userMaster.setAge(guestAppointmentVo.getAge());
		userMaster.setMobile(guestAppointmentVo.getMobile());
		userMaster.setEmail(guestAppointmentVo.getEmail());
		return userMaster;
	}
}
