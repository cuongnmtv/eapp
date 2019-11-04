package eapp.poc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import eapp.poc.entity.Course;
import eapp.poc.entity.Institute;
import eapp.poc.repo.CourseRepo;
import eapp.poc.repo.InstituteRepo;

@Controller
public class CourseController {

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private InstituteRepo instRepo;

	@ModelAttribute("instituteList")
	public List<Institute> getInstituteList() {
		// donot use findAll() method to fetch join table data
		return instRepo.getInstCustomList();
	}

	@ModelAttribute("courseList")
	public List<Map<String, Object>> getCourseList() {
		// donot use findAll() method to fetch join table data
		return courseRepo.getCourseList();
	}

	@GetMapping("/courses")
	public String form(Model m) {
		m.addAttribute("cmd", new Course());
		return "courses";
	}

	@PostMapping("/save-course")
	public String save(@ModelAttribute Course c) {
		courseRepo.save(c);
		return "redirect:/courses";
	}
}
