package per.chj;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class PictureForCity implements PicutreFetch {

	private DefaultHttpClient client;

	private String FETCH_URL = "http://ugc.moji001.com/sns/GetPictureFlowID";

	private String PICTURE_URL = "http://ugc.moji001.com/sns/GetPictureFlow";

	public PictureForCity(DefaultHttpClient client) {
		this.client = client;
	}

	public String fetchId(Map<String, String> param) throws IllegalStateException, IOException {
		param.put("DV", "200");
		param.put("Step", "20");
		param.put("QT", "1");
		String fetchUrl = Utils.urlGenerate(FETCH_URL, param);

		HttpGet get = new HttpGet(fetchUrl);
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

	public Element getXml(Map<String, String> param) throws ParserConfigurationException, IllegalStateException,
			SAXException, IOException {

		param.put("DV", "200");
		String newPictureUrl = Utils.urlGenerate(PICTURE_URL, param);

		HttpGet get = new HttpGet(newPictureUrl);
		initHttpGet(get);
		HttpResponse httpResponse = null;

		try {
			httpResponse = client.execute(get);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		try {
			if (httpResponse.getEntity().getContentEncoding() != null
					&& httpResponse.getEntity().getContentEncoding().getValue().indexOf("gzip") != -1) {
				httpResponse.setEntity(new GzipDecompressingEntity(httpResponse.getEntity()));
			}
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
