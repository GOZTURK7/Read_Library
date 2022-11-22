package com.RL.service;

import com.RL.repository.ContactRepository;
import com.RL.domain.Contact;
import com.RL.exception.ResourceNotFoundException;
import com.RL.exception.message.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public void createContact(Contact contact){
        contactRepository.save(contact);
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_MESSAGE,id)));
    }

    public Page<Contact> findAllContactsWithPage(Pageable pageable){
        return contactRepository.findAllContactsWithPage(pageable);
    }

    public Contact updateContact(Contact contact, Long id) {
        Contact foundContact = getContactById(id);
        foundContact.setName(contact.getName());
        foundContact.setEmail(contact.getEmail());
        foundContact.setSubject(contact.getSubject());
        foundContact.setBody(contact.getBody());
        foundContact.setIsread(contact.getIsread());
        contactRepository.save(foundContact);
        return foundContact;
    }

    public Contact deleteContact(Long id) {
        Contact foundContact = getContactById(id);
        contactRepository.deleteById(id);
        return foundContact;
    }


}
