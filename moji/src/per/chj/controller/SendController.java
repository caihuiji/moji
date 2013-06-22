package per.chj.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import per.chj.model.Moji;
import per.chj.utils.DateEditor;
import per.chj.utils.TransformNull;

@Controller
@RequestMapping("/send")
@Scope("prototype")
public class SendController {
	
 

	@RequestMapping(value = "moji", method = RequestMethod.GET)
	public ModelAndView index() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		ModelAndView mav = new ModelAndView("send");

		mav.addObject("currentDate", sdf.format(new Date()));

		return mav;
	}

	@RequestMapping(value = "moji", method = RequestMethod.POST)
	public ModelAndView create(Moji moji) {

		System.out.println();

		return null;
	}

}
