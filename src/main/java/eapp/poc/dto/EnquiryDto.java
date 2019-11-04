package eapp.poc.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface EnquiryDto {

	Long getEnquiryId();

	String getPhone();

	String getName();

	String getCourses(); // comma separated

	Integer getFollowUpCount();

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	Date getDoe();

	Float getFees();
}
