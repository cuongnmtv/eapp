package eapp.poc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import eapp.poc.entity.Contact;

public interface ContactRepo extends JpaRepository<Contact, Long> {
}
