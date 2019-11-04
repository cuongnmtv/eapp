package eapp.poc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import eapp.poc.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
