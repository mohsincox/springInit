package com.mohsin.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mohsin.main.model.Programmer;
import com.mohsin.main.repository.ProgrammerRepo;

@Controller
public class MainController {

	@Autowired
	ProgrammerRepo pr;

	@RequestMapping("/home")
	public String homePage() {
		return "HomePage.html";
	}

	@PostMapping("/addProgrammer")
	public String addProgrammer(@ModelAttribute Programmer programmer) {
		pr.save(programmer);

		return "redirect:/home";
	}

	@PostMapping("/findById")
	public String findById(@RequestParam int pId, Model m) {

		Programmer p = pr.getOne(pId);

		m.addAttribute("programmer", p);

		return "ProgrammerInfo.html";
	}
	
	@PostMapping("/findByLang")
	public String findByLang(@RequestParam String pLang, Model m) {

		List<Programmer> p = pr.findBypLang(pLang);

		m.addAttribute("programmers", p);

		return "AllProgrammer.html";
	}
	
	@PostMapping("/findByName")
	public String findByName(@RequestParam String pName, Model m) {

		List<Programmer> p = pr.findP(pName);

		m.addAttribute("programmers", p);

		return "AllProgrammer.html";
	}

	@PostMapping("/updateProgrammer")
	public String updateProgrammer(@ModelAttribute Programmer programmer) {

		Programmer p = pr.getOne(programmer.getpId());
		p.setpName(programmer.getpName());
		p.setpLang(programmer.getpLang());
		pr.save(programmer);

		return "ProgrammerInfo.html";
	}

	@GetMapping("/deleteProgrammer")
	public String deleteProgrammer(@RequestParam int pId) {

		pr.deleteById(pId);

		return "redirect:/home";
	}

	@GetMapping("/allProgrammer")
	public String allProgrammer(Model m) {

		List<Programmer> p = pr.findAll();

		m.addAttribute("programmers", p);

		return "AllProgrammer.html";
	}

}
