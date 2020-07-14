package com.furquan.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.furquan.rest.model.ContactDetailEntity;

/**
 * @author furquan
 *
 */
@Repository
public interface ContactRepository extends JpaRepository<ContactDetailEntity, Long> {

	@Query("SELECT t FROM ContactDetailEntity t WHERE t.contactNumber = ?1 AND t.status = ?2")
	ContactDetailEntity findByContact(int contactNumber, int status);

}
