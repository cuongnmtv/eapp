package eapp.poc.dto;

import eapp.poc.entity.Enquiry;

public class EnquiryCommand {

	private Enquiry enquiry;
	Long[] courses;

	public Enquiry getEnquiry() {
		return enquiry;
	}

	public void setEnquiry(Enquiry enquiry) {
		this.enquiry = enquiry;
	}

	public Long[] getCourses() {
		return courses;
	}

	public void setCourses(Long[] courses) {
		this.courses = courses;
	}
}
