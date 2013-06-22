package per.chj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalWebBindingInitializer {  
	
  
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {  
      	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false); //非精确匹配
        
        binder.registerCustomEditor(String.class, new TransformNull(true));
        binder.registerCustomEditor(Date.class, new DateEditor(dateFormat, true));
    }  
    
  
}