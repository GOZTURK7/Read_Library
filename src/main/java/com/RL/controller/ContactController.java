package com.RL.controller;

import com.RL.domain.Author;
import com.RL.domain.Contact;
import com.RL.service.AuthorService;
import com.RL.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class ContactController {

    @Autowired
    ContactService contactService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/contact")
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact){
        contactService.createContact(contact);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }



    @GetMapping("/contact/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id){
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact,HttpStatus.OK);
    }


    @GetMapping("/contact/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Contact>> getAllContacts(@RequestParam(required = false,value = "page", defaultValue = "0") int page,
                                                      @RequestParam(required = false,value = "size", defaultValue = "20") int size,
                                                      @RequestParam(required = false,value = "sort", defaultValue =  "1") String id,
                                                      @RequestParam(required = false,value = "direction", defaultValue = "ASC") Sort.Direction direction){

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, id));
        Page<Contact> contactPage = contactService.findAllContactsWithPage(pageable);
        return new ResponseEntity<>(contactPage, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/contact/{id}")
    public ResponseEntity<Contact> updateAuthorById(@PathVariable Long id, @Valid @RequestBody Contact contact){
        Contact updatedContact = contactService.updateContact(contact, id);
        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }

    /* Delete Current Author */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/contact/{id}")
    public ResponseEntity<Contact> deleteContactById(@PathVariable Long id){
        Contact contact = contactService.deleteContact(id);
        return ResponseEntity.ok(contact);
    }




}
