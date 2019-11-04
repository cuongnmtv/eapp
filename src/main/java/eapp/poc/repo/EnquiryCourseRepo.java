package eapp.poc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import eapp.poc.entity.EnquiryCourse;

public interface EnquiryCourseRepo extends JpaRepository<EnquiryCourse, Long> {
}
