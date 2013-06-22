package per.chj.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import per.chj.utils.MD5;

@Controller
@RequestMapping("/file")
@Scope("prototype")
public class FileController {

	private static final String UPLOAD_DIR = "/uploads/";

	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public ResponseEntity<String> upload(MultipartFile fileToUpload, HttpServletRequest request) {
		CommonsMultipartFile multipartFile = (CommonsMultipartFile) fileToUpload;

		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("status", "success");

		// 生成文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String folderName = sdf.format(new Date());
		String path = request.getServletContext().getRealPath(UPLOAD_DIR + folderName);

		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}

		// 生成文件
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		String upoadFileName = sdf.format(new Date());
		String originalFilename = multipartFile.getOriginalFilename();
		String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
		File uploadFile = new File(dir + File.separator + MD5.encode(upoadFileName) + fileNameSuffix);

		obj.put("path", request.getContextPath() + UPLOAD_DIR +folderName +  "/" + uploadFile.getName());
		try {
			copyInputStreamToFile(multipartFile.getInputStream(), uploadFile);
		} catch (IOException e) {
			e.printStackTrace();
			obj.put("status", "error");

		}
		
		
		ObjectMapper om = new ObjectMapper();
		String responseBody = "{}";
		try {
			responseBody = om.writeValueAsString(obj);
		} catch (JsonGenerationException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return new ResponseEntity<String>(responseBody, HttpStatus.OK);
	}

	private void copyInputStreamToFile(InputStream is, File file) throws IOException {
		BufferedInputStream inputeStream = null;
		BufferedOutputStream outStream = null;
		try {

			inputeStream = new BufferedInputStream(is);
			outStream = new BufferedOutputStream(new FileOutputStream(file));

			byte[] bytes = new byte[1024];
			while (inputeStream.read(bytes) != -1) {
				outStream.write(bytes);
			}
			outStream.flush();
		} finally {
			if (inputeStream != null) {
				inputeStream.close();
			}
			if (outStream != null) {
				outStream.close();
			}
		}

	}

}
