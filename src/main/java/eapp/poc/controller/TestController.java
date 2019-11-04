package eapp.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import eapp.poc.entity.EnquirySource;
import eapp.poc.repo.EnquirySourceRepo;

@RestController
public class TestController {

	@Autowired
	private EnquirySourceRepo enquirySourceRepo;

	@GetMapping("/test-save-source")
	public String testSaveSource() {
		EnquirySource es = new EnquirySource("Website Partner 69");
		enquirySourceRepo.save(es);
		return "Success";
	}

	@GetMapping("/test-source-list")
	public List<EnquirySource> testGetSourceList() {
		return enquirySourceRepo.findAll();
	}
}
