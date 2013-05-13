import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Worker implements Runnable {

	private PicutreFetch pictureForCity;
	private Commenter commenter;
	private WorkerCondition workerCon;

	Map<String, String> param = new HashMap<String, String>();

	Map<String, String> fetchParam = new HashMap<String, String>();
	Map<String, String> xmlParam = new HashMap<String, String>();
	Map<String, String> commentParam = new HashMap<String, String>();

	public Worker(PicutreFetch pictureForCity, Commenter commenter, WorkerCondition workerCon) {
		this.pictureForCity = pictureForCity;
		this.commenter = commenter;

		this.workerCon = workerCon;

		param.put("CityID", workerCon.getCity());
		param.put("SnsID", workerCon.getSnsId());

		fetchParam.putAll(param);
		xmlParam.putAll(param);
		commentParam.putAll(param);
	}

	@Override
	public void run() {

		// 等待开始
		long currentTime = new Date().getTime();
		long sleepTime =  workerCon.getStartDate().getTime() - currentTime ;
		if (sleepTime > 0) {
			try {
				System.out.println("等待 " + (sleepTime / 1000) + "s 后开始");
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			while (true) {

				xmlParam.put("PicID", pictureForCity.fetchId(fetchParam));

				Element element = pictureForCity.getXml(xmlParam);

				NodeList nl = element.getElementsByTagName("picture");

				for (int i = 0; i < nl.getLength(); i++) {
					Element node = (Element) nl.item(i);
					commentParam.put("PicID", node.getAttribute("id"));
					commentParam.put("PicSnsId", node.getAttribute("snsid"));
					commentParam.put("PicUserId", node.getAttribute("userid"));
					boolean is = commenter.comment(commentParam);
					System.out.println((i+1) +".location:" + node.getAttribute("location") + " is " + is + " image " + node.getAttribute("purl"));
					// 15秒处理一个
					Thread.sleep(1000 * 3);
				}
				System.out.println("等待 " + (workerCon.getInterval() / 1000) + "s 后重新启动");
				Thread.sleep(workerCon.getInterval());

				
				System.out.println("等待 " + (new Date().getTime() - workerCon.getEndDate().getTime()) + "s 后结束");
				// 结束
				if (new Date().getTime() - workerCon.getEndDate().getTime() >= 0) {
					break;
				}
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

		System.out.println("结束");
	}

}
