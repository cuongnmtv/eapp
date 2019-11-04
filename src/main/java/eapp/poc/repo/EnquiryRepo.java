package eapp.poc.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eapp.poc.dto.EnquiryDto;
import eapp.poc.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Long> {
	
	@Query("SELECT group_concat(e.enquiryId) FROM Enquiry AS e")
	public String findAllIds();
	
	@Query("SELECT e.enquiryId AS enquiryId, e.doe AS doe, e.committedFees AS fees, c.name AS name, c.phone AS phone,"
            + " (SELECT count(followupId) FROM Followup f WHERE e=f.enquiry) AS followUpCount,"
            + " (SELECT group_concat(co.name) FROM Course co, EnquiryCourse ec WHERE e=ec.enquiry AND ec.course=co) AS courses "
            + " FROM Enquiry e, Contact c WHERE e.contact=c and e.institute.instituteId=?1")
    public List<EnquiryDto> getEnquiryDTOList(Long instituteId);
	
	@Query("SELECT e.enquiryId AS enquiryId, e.doe AS doe,e.nextCallDate AS nextCallDate , e.committedFees AS fees, e.remark AS remark, "
            + " e.enquirySource.name AS sourceOfEnquiry, "
            + " c.name AS name, c.phone AS phone, c.email AS email,"
            + " c.permanentAddress.city AS city, c.permanentAddress.country AS country, c.permanentAddress.zip AS zip, c.permanentAddress.detail AS detail,"
            + " (SELECT group_concat(co.name) FROM Course co, EnquiryCourse ec WHERE e=ec.enquiry AND ec.course=co) AS courses "
            + " FROM Enquiry e, Contact c WHERE e.contact=c and e.enquiryId=?1")
    public Map<String, Object> getEnquiryDetailMap(Long enquiryId);
}
