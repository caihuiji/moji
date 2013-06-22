package per.chj.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public final static String encode(String str){  //MD5加密算法
		String s = str==null ? "" : str;              //如果为空则返回""
		char hexDigits[] = {'0','1','2','3','4','5','6','7','8',
				'9','a','b','c','d','e','f'};         //字典
		byte[] strTemp = s.getBytes();                //获得二进制
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5"); //加密器
			mdTemp.update(strTemp);                   //执行加密
			byte[] md = mdTemp.digest();                          //加密结果
			int j = md.length;
			char[] value = new char[j*2];             //字符数组
			int k = 0;
			for(int i=0;i<j;i++) {
				byte byte0 = md[i];
				value[k++] = hexDigits[byte0 >>> 4 & 0xf];
				value[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(value);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public static void main(String[] args){
		System.out.println(encode("你好"));
	}
}