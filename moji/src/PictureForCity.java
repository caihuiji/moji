import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class PictureForCity {

	public static final String STAND_URL_PARAM = "UserID=16396308&Platform=Android&Version=10023802&BaseOSVer=10&PartnerKey=5068&Model=MB525&Device=phone&VersionType=1&SnsID=5365870";

	private DefaultHttpClient client;
	private String city;

	public PictureForCity(DefaultHttpClient client, String city) {
		this.client = client;
		this.city = city;
	}

	private String fetchUrl = "http://ugc.moji001.com/sns/GetPictureFlowID?" + STAND_URL_PARAM
			+ "&DV=200&Step=1&QT=1&CityID=";

	private String pictureUrl = "http://ugc.moji001.com/sns/GetPictureFlow?" + STAND_URL_PARAM + "&DV=200&CityID=";

	public String fetchId() throws IllegalStateException, IOException {
		String newFetchUrl = fetchUrl + city;
		HttpGet get = new HttpGet(newFetchUrl);
		initHttpGet(get);

		HttpResponse httpResponse = null;

		try {
			httpResponse = client.execute(get);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Scanner sc = new Scanner(httpResponse.getEntity().getContent());

		int i = 0;
		try {
			while (sc.hasNext()) {
				if (++i >= 3) {
					return sc.next();
				}
				sc.next();
			}
		} finally {
			get.releaseConnection();
		}
		return "";

	}

	public Element getXml(String picIDs) throws ParserConfigurationException, IllegalStateException, SAXException,
			IOException {
		String newPictureUrl = pictureUrl + city + "&PicID=" + picIDs;

		HttpGet get = new HttpGet(newPictureUrl);
		initHttpGet(get);
		HttpResponse httpResponse = null;

		try {
			httpResponse = client.execute(get);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(httpResponse.getEntity().getContent());
			Element element = document.getDocumentElement();
			return element;
		} finally {
			get.releaseConnection();
		}

	}

	private void initHttpGet(HttpGet get) {
		get.setHeader("User-Agent", "Apache-HttpClient/UNAVAILABLE (java 1.4)");
		get.setHeader("Connection", " Keep-Alive");
		get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		get.setHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
		get.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		get.setHeader("Connection", "keep-alive");
	}

}
