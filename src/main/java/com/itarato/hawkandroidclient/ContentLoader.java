package com.itarato.hawkandroidclient;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class ContentLoader {

    private final String feedURL;

    public ContentLoader(String feedURL) {
        this.feedURL = feedURL;
    }

    public String load() {
        try {
            URL contentURL = new URL(this.feedURL);
            InputStream is = contentURL.openStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int current;
            byte[] data = new byte[50];

            while ((current = bis.read(data, 0, data.length)) != -1) {
                byteArrayOutputStream.write(data, 0, current);
            }

            System.out.println("feedURL operation done");
            return byteArrayOutputStream.toString();
        } catch (Exception e) {
            System.out.println("feedURL related exception.");
            e.printStackTrace();
        }

        return "";
    }

}
