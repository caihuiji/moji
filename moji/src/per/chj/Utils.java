package per.chj;
import java.util.HashMap;
import java.util.Map;

public class Utils {

	private static Map<String, String> standparam = new HashMap<String, String>(10);

	static {
		standparam.put("UserID", "16396308");
		standparam.put("Platform", "Android");
		standparam.put("Version", "10023702");
		standparam.put("BaseOSVer", "10");
		standparam.put("PartnerKey", "5068");
		standparam.put("Model", "MB525");
		standparam.put("Device", "phone");
		standparam.put("VersionType", "1");
	}

	public static final String urlGenerate(String url, Map<String, String> param) {

		for (Map.Entry<String, String> entry : standparam.entrySet()) {
			if (param.containsKey(entry.getKey()) && param.get(entry.getKey()) != null) {
				continue;
			}
			param.put(entry.getKey(), entry.getValue());
		}

		StringBuffer sb = new StringBuffer(50);
		sb.append(url).append("?");
		for (Map.Entry<String, String> entry : param.entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		return sb.substring(0, sb.length() - 1).toString();
	}

}
