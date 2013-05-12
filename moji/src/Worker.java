import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Worker implements Runnable {

	private PictureForCity pictureForCity;
	private Commenter commenter;

	public Worker(PictureForCity pictureForCity, Commenter commenter) {
		this.pictureForCity = pictureForCity;
		this.commenter = commenter;

	}

	@Override
	public void run() {
		while (true) {
			try {
				Element element = pictureForCity.getXml(pictureForCity.fetchId());

				NodeList nl = element.getElementsByTagName("picture");

				for (int i = 0; i < nl.getLength(); i++) {
					Element node = (Element) nl.item(i);
					System.out.print("location:" + node.getAttribute("location") + " is ");
					System.out.print(commenter.comment(node.getAttribute("id"), node.getAttribute("snsid"),
							node.getAttribute("userid")));
					System.out.print(" image " + node.getAttribute("purl"));
					System.out.println();
					// 5分钟处理一个
					Thread.sleep(1000 * 60 * 5);
				}

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
