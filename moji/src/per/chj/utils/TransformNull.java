package per.chj.utils;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;

/**
 * "null" to null
 * @author ji
 *
 */
public class TransformNull extends StringTrimmerEditor{
	

	public TransformNull(boolean emptyAsNull) {
		super(emptyAsNull);
		// TODO Auto-generated constructor stub
	}

	public void setAsText(String text) {
			super.setAsText(text);
		    if("null".endsWith(this.getAsText())){
		    	this.setValue(null);
		    }
	 }

}
