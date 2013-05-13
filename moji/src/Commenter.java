import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;

public class Commenter {

	private DefaultHttpClient client;
	private String comment;

	private String COMMENT_URL = "http://ugc.moji001.com/sns/MakePictureReply";

	public Commenter(DefaultHttpClient client ,String comment) {
		this.comment = comment;
		this.client = client;

	}

	public boolean comment(Map<String,String> param ) throws IllegalStateException, IOException {
		param.put("Reply", URIUtil.encodeQuery(comment));

		String newCommentUrl = Utils.urlGenerate(COMMENT_URL, param);

		
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
