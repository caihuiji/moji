import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws IllegalStateException, IOException, ParserConfigurationException,
			SAXException, ParseException {
		

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		
		
		
		//String comment = "全国轰炸式评论广告，刷热图，请加扣扣 1598175699.";
		String comment = "给力啊！大家也来看看我的热图啊！！[嘴唇][嘴唇][嘴唇][嘴唇]";
		DefaultHttpClient dhc = new DefaultHttpClient();
		new Worker(new PictureForHot(dhc), new Commenter(dhc, comment), new WorkerCondition("333", "5652340",
				sdf.parse("2013-05-13 22:45:00"), sdf.parse("2013-05-13 23:15:00"), 1000*60*5)).run();
		
//		String[] cityIds = {"352","126" ,"340","1288" ,"143","412" ,"53" ,"188", "288"} ;
//		for(String city : cityIds){
//			DefaultHttpClient dhc = new DefaultHttpClient();
//			new Thread (new Worker(new PictureForCity(dhc), new Commenter(dhc, comment), new WorkerCondition(city, "5452225",
//					sdf.parse("2013-05-13 22:00:00"), sdf.parse("2013-05-13 24:00:00"), 1000*60*25))).start();
//		}
	}

}
