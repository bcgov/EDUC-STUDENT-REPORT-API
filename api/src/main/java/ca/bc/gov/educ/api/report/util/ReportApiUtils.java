package ca.bc.gov.educ.api.report.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportApiUtils {

	public static HttpHeaders getHeaders (String accessToken)
    {
		HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.setBearerAuth(accessToken);
        return httpHeaders;
    }
	
	public static HttpHeaders getHeaders (String username,String password)
    {
		HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth(username, password);
        return httpHeaders;
    }
	
	public static String formatDate (Date date, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

    public static byte[] appendData(byte[] firstObject,byte[] secondObject){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        try {
            if (firstObject!=null && firstObject.length != 0)
                outputStream.write(firstObject);
            if (secondObject!=null && secondObject.length != 0)
                outputStream.write(secondObject);
        } catch (IOException e) {
            //ignore
        }
        return outputStream.toByteArray();
    }
}
