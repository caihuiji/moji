import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CommentPublisher implements Runnable {

	private DefaultHttpClient client;
	private String comment;
	private String city;
	private PictureForCity pictureForCity;

	private String commentUrl;

	public CommentPublisher(String comment, String city) {
		this.comment = comment;
		this.city = city;

		client = new DefaultHttpClient();
		pictureForCity = new PictureForCity(client);

		commentUrl = "http://ugc.moji001.com/sns/MakePictureReply?" + PictureForCity.STAND_URL_PARAM + "&CityID="
				+ city;
	}

	@Override
	public void run() {
		try {
			Element element = pictureForCity.getXml(city, pictureForCity.fetchId(city));

			NodeList nl = element.getElementsByTagName("picture");

			for (int i = 0; i < nl.getLength(); i++) {
				Element node = (Element) nl.item(i);
				System.out.print("location:" + node.getAttribute("location") + " is ");
				System.out.print(comment(node.getAttribute("id"), node.getAttribute("snsid"),
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

	private boolean comment(String picID, String picSnsid, String picUserId) throws IllegalStateException, IOException {
		StringBuffer sb = new StringBuffer(10);

		String newCommentUrl = sb.append(commentUrl).append("&PicID=").append(picID).append("&PicSnsId=")
				.append(picSnsid).append("&PicUserId=").append(picUserId).append("&Reply=").append(comment).toString();

		HttpGet get = new HttpGet(newCommentUrl);

		get.setHeader("User-Agent", "Apache-HttpClient/UNAVAILABLE (java 1.4)");
		get.setHeader("Accept-Encoding", "gzip,deflate,sdch");

		HttpResponse httpResponse = null;

		try {
			httpResponse = client.execute(get);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		try {
			httpResponse.setEntity(new GzipDecompressingEntity(httpResponse.getEntity()));
			Scanner sc = new Scanner(httpResponse.getEntity().getContent());
			return sc.next().equals("0");
		} finally {
			get.releaseConnection();
		}

	}

}
