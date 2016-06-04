package com.itarato.hawk;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class ContentLoader {

    public String load() {
        try {
            URL contentURL = new URL("http://192.168.0.101/hawk_d8/hawk/content");
            InputStream is = contentURL.openStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int current;
            byte[] data = new byte[50];

            while ((current = bis.read(data, 0, data.length)) != -1) {
                byteArrayOutputStream.write(data, 0, current);
            }

            System.out.println("URL operation done");
            return byteArrayOutputStream.toString();
        } catch (Exception e) {
            System.out.println("URL related exception.");
            e.printStackTrace();
        }

        return "";
    }

}
