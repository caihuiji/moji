package per.chj;
import java.io.IOException;
import java.util.Map;

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

public class PictureForHot implements PicutreFetch {

	private DefaultHttpClient client;

	private String HOT_URL = "http://ugc.moji001.com/sns/GetBoutiquePictureFlow";

	public PictureForHot(DefaultHttpClient client) {
		this.client = client;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PicutreFetch#fetchId(java.util.Map)
	 */
	@Override
	public String fetchId(Map<String, String> param) throws IllegalStateException, IOException {

		return "";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PicutreFetch#getXml(java.util.Map)
	 */
	@Override
	public Element getXml(Map<String, String> param) throws ParserConfigurationException, IllegalStateException,
			SAXException, IOException {

		param.put("DV", "200");
		param.put("Position", "");
		param.put("Step", "50");

		String newPictureUrl = Utils.urlGenerate(HOT_URL, param);

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
