package eapp.poc.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import eapp.poc.dto.InstituteDto;
import eapp.poc.entity.Institute;

public interface InstituteRepo extends JpaRepository<Institute, Long> {
	
	@Query("SELECT i.instituteId AS instituteId, "
			+ "i.name AS name, "
			+ "i.contact.phone AS phone, "
			+ "i.contact.email AS email, "
			+ "i.contact.permanentAddress.city AS thecity "
			+ "FROM Institute AS i")
	public List<InstituteDto> getInstituteList();
	
	@Query("SELECT new eapp.poc.entity.Institute(i.instituteId, i.name)"
			+ "FROM Institute AS i")
	public List<Institute> getInstCustomList();
}
