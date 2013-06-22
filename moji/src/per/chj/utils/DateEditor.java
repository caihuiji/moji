package per.chj.utils;

import java.text.DateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.annotation.ControllerAdvice;

public class DateEditor extends CustomDateEditor {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if("null".endsWith(text)){
	    	this.setValue(null);
	    }
		super.setAsText(this.getAsText());
	}

	public DateEditor(DateFormat dateFormat, boolean allowEmpty) {
		super(dateFormat, allowEmpty);
	}

}
