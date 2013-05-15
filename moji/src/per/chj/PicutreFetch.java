package per.chj;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public interface PicutreFetch {

	String fetchId(Map<String, String> param) throws IllegalStateException, IOException;

	Element getXml(Map<String, String> param) throws ParserConfigurationException, IllegalStateException, SAXException,
			IOException;

}