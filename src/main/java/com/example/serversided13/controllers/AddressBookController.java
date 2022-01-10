package com.example.serversided13.controllers;

import com.example.serversided13.models.ContactModel;
import com.example.serversided13.util.Contacts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddressBookController {

  private static final Logger logger = LoggerFactory.getLogger(AddressBookController.class);

  @Autowired
  private ApplicationArguments applicationArguments;

  @GetMapping("/")
  public String contactForm(Model model) {
    model.addAttribute("contact", new ContactModel());
    // returns name of html page
    return "contactform";
  }

  @PostMapping("/contactSubmit")
  public String contactSubmit(@ModelAttribute ContactModel contactModel, Model model) {
    long startTime = System.currentTimeMillis();

    logger.info("Id: " + contactModel.getId());
    logger.info("Name: " + contactModel.getName());
    logger.info("Email: " + contactModel.getEmail());
    logger.info("Phone number: " + contactModel.getPhoneNumber());

    Contacts contacts = new Contacts();
    contacts.saveContact(contactModel, model, applicationArguments);
    long endTime = System.currentTimeMillis();
    logger.info("Elapsed timing -> contactSubmit() " + (endTime - startTime));

    // returns name of html page

    return "displaycontact";
  }

  @GetMapping("/getContact/{contactId}")
  public String getContact(Model model, @PathVariable(value = "contactId") String contactId) {
    logger.info("contactId : " + contactId);

    Contacts contacts = new Contacts();
    contacts.getContactById(model, contactId, applicationArguments);
    // returns name of html page
    return "displaycontact";
  }

}
