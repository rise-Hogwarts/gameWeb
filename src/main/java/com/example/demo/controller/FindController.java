package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@EnableScheduling
@Controller
public class FindController {
	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	@GetMapping("/")
	public String test() {
		return "html/root";
	}
	
	@RequestMapping(value = "/numbers", method = {RequestMethod.GET, RequestMethod.POST})
	public String numbers(Model model) {
		session = request.getSession();
	    session.setMaxInactiveInterval(1); 
		
		Random rand = new Random();
		int number = rand.nextInt(100);
		
		// 表示される数字を格納するリスト
		List<Integer> numbers = new ArrayList<Integer>();
		// 1〜100の数字を格納
		for(int i = 0; i <= 99; i++) {
			numbers.add(i);
		}
		// 格納された数字をシャッフル
		Collections.shuffle(numbers);
		
		model.addAttribute("themeNumber", number);
		model.addAttribute("numbers", numbers);
		
		return "html/numbers";
	}
	
	@GetMapping("/answer")
	public String answer(@RequestParam("clickNo") String clickNo, @RequestParam("themeNumber") String themeNo) {
		System.out.println(clickNo);
		if(clickNo.equals(themeNo)) {
			
		}
		session = request.getSession(false);
		if(session == null) {
			return "html/root";
		}
		
		return "redirect:/numbers";
	}
	
//	@GetMapping("/calc/setting")
//	public String calcsetting(Model model) {
//		return "html/calcSetting";
//	}
//	@PostMapping("/calcStart")
//	public String start(@RequestParam("set") String set, Model model) {
//		System.out.println(set);
//		Random rand = new Random();
//		int num1 = rand.nextInt(10);
//		int num2 = rand.nextInt(10);
//		
//		model.addAttribute("num1",num1);
//		model.addAttribute("num2",num2);
//		
//		if("plus".equals(set)) {
//			model.addAttribute("answer", num1+num2);
//			model.addAttribute("enzansi","+");
//		}else if("minus".equals(set)) {
//			model.addAttribute("answer", num1-num2);
//			model.addAttribute("enzansi","-");
//		}else if("kake".equals(set)) {
//			model.addAttribute("answer", num1*num2);
//			model.addAttribute("enzansi","x");
//		}else {
//			model.addAttribute("answer", num1/num2);
//			model.addAttribute("enzansi","÷");
//		}
//		return "html/calcStart";
//	}
//	
//	@PostMapping("/calc/answer")
//	public String calcAnswer(@RequestParam("answer") String answer, @RequestParam("response") String response) {
//
//		if(answer.equals(response)){
//			return "redirect:/calc/setting";
//		}
//		return null;
//	}
	
}
