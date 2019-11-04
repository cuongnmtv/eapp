package eapp.poc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eapp.poc.dto.EnquiryCommand;
import eapp.poc.entity.Course;
import eapp.poc.entity.EnquirySource;
import eapp.poc.entity.Institute;
import eapp.poc.repo.CourseRepo;
import eapp.poc.repo.EnquiryRepo;
import eapp.poc.repo.EnquirySourceRepo;
import eapp.poc.repo.FollowupRepo;
import eapp.poc.repo.InstituteRepo;
import eapp.poc.service.CommandService;

@Controller
public class EnquiryController {

	@Autowired
	private InstituteRepo instRepo;

	@Autowired
	private EnquirySourceRepo enqSourceRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private CommandService cmdService;

	@Autowired
	private EnquiryRepo enqRepo;

	@Autowired
	private FollowupRepo foRepo;

	@GetMapping("/")
	public String index(Model m) {
		m.addAttribute("cmd", new EnquiryCommand());
		return "index";
	}

	@ModelAttribute("instituteList")
	public List<Institute> getInstList() {
		return instRepo.getInstCustomList();
	}

	@ModelAttribute("sourceList")
	public List<EnquirySource> getSourceList() {
		return enqSourceRepo.findAll();
	}

	@GetMapping("/get-courses")
	@ResponseBody
	public List<Course> getCourseByInstId(@RequestParam Long instId) {
		return courseRepo.findCourseListByInstitute_instituteId(instId);
	}

	@PostMapping("/save-enquiry")
	public String save(@ModelAttribute EnquiryCommand cmd) {
		cmdService.saveEnquiry(cmd);
		return "redirect:/enq-list";
	}

	@GetMapping("/enq-list")
	public String enquiryList(@RequestParam(required = false) Long instId, Model m) {
		m.addAttribute("enquiryList", enqRepo.getEnquiryDTOList(instId));
		return "enq-list";
	}

	@GetMapping("/test-get-inst-ids")
	@ResponseBody
	public String testGetInstIds() {
		return enqRepo.findAllIds();
	}

	@PostMapping("/save-followup")
	public String saveFollowup(@RequestParam Long enquiryId, @RequestParam String followup,
			@RequestParam(required = false) String go) {
		cmdService.saveFollowup(enquiryId, followup);
		return (go != null && go.equals("eview")) ? "redirect:/enquiry-detail/" + enquiryId : "redirect:/enq-list";
	}

	@GetMapping("/enquiry-detail/{id}")
	public String enquiryDetail(@PathVariable Long id, Model m) {
		m.addAttribute("dataMap", enqRepo.getEnquiryDetailMap(id));
		m.addAttribute("followupList", foRepo.getFollowupsByEnquiryId(id));
		return "enquiry-detail";
	}

	@GetMapping("/test-enquiry-detail/{id}")
	@ResponseBody
	public Map<String, Object> getEnqMap(@PathVariable Long id) {
		return enqRepo.getEnquiryDetailMap(id);
	}
}
