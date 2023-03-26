package com.shubham.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shubham.model.Contact;
import com.shubham.service.ContactService;

@Controller
public class ContactController {
	
	private static Logger log=LoggerFactory.getLogger(ContactController.class);
	
	@Autowired
	private final ContactService contactService;
	
	ContactController(ContactService contactService)
	{
		this.contactService=contactService;
	}
	
	@RequestMapping("/contact")
	public String displayContactPage(Model model)
	{
		model.addAttribute("contact", new Contact());
		return "contact.html";
	}
	
	@RequestMapping(value="/saveMsg",method = RequestMethod.POST)
	public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors)
	{
		if(errors.hasErrors())
		{
			log.error("Contact form validation failed due to : "+errors.toString());
			return "contact.html";
		}
		contactService.saveMessageDetails(contact);
		return "redirect:/contact";
	}
}
