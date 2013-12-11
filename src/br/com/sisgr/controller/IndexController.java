package br.com.sisgr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/inicio")
	public String getIndex(){
		return "index";
	}
}
