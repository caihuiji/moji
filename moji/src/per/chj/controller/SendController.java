package per.chj.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/send")
@Scope("prototype")
public class SendController {
	
	@RequestMapping
	public ModelAndView index () throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		ModelAndView mav = new ModelAndView("send");
		
		mav.addObject("currentDate", sdf.format(new Date()));
		
		
		return mav;
	}

}
