package per.chj.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping
	public String index () throws Exception {
		return "index";
	}
	
	@RequestMapping("/{id}")
	public String show (){
		return "show";
	}

}
