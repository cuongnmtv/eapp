package eapp.poc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eapp.poc.dto.EnquiryCommand;
import eapp.poc.entity.Address;
import eapp.poc.entity.Contact;
import eapp.poc.entity.Enquiry;
import eapp.poc.entity.EnquiryCourse;
import eapp.poc.entity.Followup;
import eapp.poc.entity.Institute;
import eapp.poc.repo.AddressRepo;
import eapp.poc.repo.ContactRepo;
import eapp.poc.repo.CourseRepo;
import eapp.poc.repo.EnquiryCourseRepo;
import eapp.poc.repo.EnquiryRepo;
import eapp.poc.repo.EnquirySourceRepo;
import eapp.poc.repo.FollowupRepo;
import eapp.poc.repo.InstituteRepo;

@Service
public class CommandService {

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private ContactRepo contactRepo;

	@Autowired
	private InstituteRepo instRepo;

	@Autowired
	private EnquirySourceRepo enqSourceRepo;

	@Autowired
	private EnquiryRepo enqRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private EnquiryCourseRepo enqCourseRepo;

	@Autowired
	private FollowupRepo foRepo;

	@Transactional
	public void saveInstitute(Institute inst) {
		// address
		addressRepo.save(inst.getContact().getPermanentAddress());
		// contact
		contactRepo.save(inst.getContact());
		// institute
		instRepo.save(inst);
	}

	@Transactional
	public void saveEnquiry(EnquiryCommand cmd) {
		// waring: do not use command at service layer
		// address
		Address a = cmd.getEnquiry().getContact().getPermanentAddress();
		addressRepo.save(a);
		// contact
		Contact c = cmd.getEnquiry().getContact();
		contactRepo.save(c);
		// enquiry
		Enquiry e = cmd.getEnquiry();
		// waring: do not use findById
		e.setInstitute(instRepo.getOne(e.getInstitute().getInstituteId()));
		e.setEnquirySource(enqSourceRepo.getOne(e.getEnquirySource().getEnquirySourceId()));
		Date currentServerTime = new Date();
		e.setDoe(currentServerTime);
		e.setLastUpdate(currentServerTime);
		enqRepo.save(e);

		Long[] courseIds = cmd.getCourses();
		for (Long courseId : courseIds) {
			EnquiryCourse ec = new EnquiryCourse();
			ec.setEnquiry(e);
			ec.setCourse(courseRepo.getOne(courseId));
			enqCourseRepo.save(ec);
		}
	}

	@Transactional
	public void saveFollowup(Long enquiryId, String followup) {
		Followup fo = new Followup();
		fo.setDetail(followup);
		fo.setEnquiry(enqRepo.getOne(enquiryId));
		fo.setDoe(new Date());
		foRepo.save(fo);
	}
}
