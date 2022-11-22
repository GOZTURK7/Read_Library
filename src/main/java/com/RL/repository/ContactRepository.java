package com.RL.repository;
import com.RL.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

    @Query(value = "SELECT p  FROM Contact p")
    Page<Contact> findAllContactsWithPage(Pageable pageable);
}
