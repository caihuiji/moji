import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws IllegalStateException, IOException, ParserConfigurationException,
			SAXException {
		
		new Worker(new PictureForCity(new DefaultHttpClient(), "333"),new Commenter("很好", "333")).run();

	}

}
