import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws IllegalStateException, IOException, ParserConfigurationException,
			SAXException {
		CommentPublisher comm = new CommentPublisher("很好", "352");
		comm.run();

	}

}
