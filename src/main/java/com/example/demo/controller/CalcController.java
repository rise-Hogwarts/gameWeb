package com.example.demo.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalcController {
	
	@GetMapping("/calc/setting")
	public String calcsetting(Model model) {
		return "html/calcSetting";
	}
	@PostMapping("/calcStart")
	public String start(@RequestParam("set") String set, Model model) {
		System.out.println(set);
		Random rand = new Random();
		int num1 = rand.nextInt(10);
		int num2 = rand.nextInt(10);
		
		model.addAttribute("num1",num1);
		model.addAttribute("num2",num2);
		
		if("plus".equals(set)) {
			model.addAttribute("answer", num1+num2);
			model.addAttribute("enzansi","+");
		}else if("minus".equals(set)) {
			model.addAttribute("answer", num1-num2);
			model.addAttribute("enzansi","-");
		}else if("kake".equals(set)) {
			model.addAttribute("answer", num1*num2);
			model.addAttribute("enzansi","x");
		}else {
			model.addAttribute("answer", num1/num2);
			model.addAttribute("enzansi","÷");
		}
		return "html/calcStart";
	}
	
	@PostMapping("/calc/answer")
	public String calcAnswer(@RequestParam("answer") String answer, @RequestParam("response") String response) {

		if(answer.equals(response)){
			return "redirect:/calc/setting";
		}
		return null;
	}

}
