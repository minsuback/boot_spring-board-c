package com.springboard.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboard.service.MemberService;
import com.springboard.vo.MemberVO;

@Controller // @Component + spring mvc 기능 추가
@RequestMapping(path = { "/account/" })
public class AccountController {
	
	@Autowired
	@Qualifier("memberService")
	private MemberService memberService;
	
	@PostMapping(path = { "/register" })
	public String register(MemberVO member) {
		
		memberService.registerMember(member);
		
		return "redirect:/resources/login.html";
	}
	
	@PostMapping(path = { "/login" })
	public String login(MemberVO member, HttpSession session) {
				
		MemberVO member2 = memberService.findMemberByEmailAndPasswd(member);
		if (member2 == null) {
			return "redirect:/resources/login.html";
		} else {
			// 로그인 처리
			session.setAttribute("loginuser", member2);
			return "redirect:/";
		}
	}
	
	@GetMapping(path = { "/logout" })
	public String logout(HttpSession session) {
		
		session.removeAttribute("loginuser");
		
		return "redirect:/";
	}
	
	
	
}
