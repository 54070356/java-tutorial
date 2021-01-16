package org.laofuzi.minio;


import io.minio.MinioClient;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 */
public class GetObjectDemo
{
    @Test
    public void testGetPartialObject() throws Exception{
        MinioClient minio = MinioClientFactory.createClient();
        try(InputStream is = minio.getObject("test", "log.txt", 1000, 1000000l);
            final BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));){
            final StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                if (!Character.isLetterOrDigit(line.codePointAt(0))) {
                    String str = "" + line.codePointAt(0);
                    if (!str.matches("[\u4e00-\u9fa5]+")) {
                        System.out.println(line.substring(1));
                    } else {
                        System.out.println(line);
                    }
                } else {
                    System.out.println(line);
                }

            }
        }

    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
