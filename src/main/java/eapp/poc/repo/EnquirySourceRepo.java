package eapp.poc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import eapp.poc.entity.EnquirySource;

public interface EnquirySourceRepo extends JpaRepository<EnquirySource, Long> {

}
