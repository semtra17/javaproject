package com.trabajo.Grupo16OO22021.controllers;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.trabajo.Grupo16OO22021.helpers.ViewRouteHelper;


@Controller
public class UserController {

	@GetMapping("/home")
	public String home(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewRouteHelper.HOME;
	}

	
	@GetMapping("/logout")
	public String logout(Model model) {
		return ViewRouteHelper.LOGOUT;
	}
	
	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/index";
	}
	
	  
}

