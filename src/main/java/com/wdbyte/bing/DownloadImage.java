package com.wdbyte.bing;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DownloadImage {
    // FullHD
    private static String BING_API = "https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=10&nc=1612409408851&pid=hp&FORM=BEHPTB&width=1920&height=1080";
    private static final String BING_URL = "https://www.bing.com";
    private static Path imagePath;

    // save image
    public static void fhd() throws Exception {
        imagePath = Paths.get("BingDailyPhotoFHD.jpeg");
        String httpContent = HttpUtls.getHttpContent(BING_API);
        JSONObject jsonObject = JSON.parseObject(httpContent);
        JSONArray jsonArray = jsonObject.getJSONArray("images");

        jsonObject = (JSONObject) jsonArray.get(0);
        // image urls
        String url = BING_URL + (String) jsonObject.get("url");
        url = url.substring(0, url.indexOf("&"));

        URL imageURL = new URL(url); // change string to java.net.URL

        if (Files.exists(imagePath)) {
            Files.delete(imagePath);
        }
        try (InputStream in = imageURL.openStream()) {
            Files.copy(in, Paths.get("BingDailyPhotoFHD.jpeg"));
        }
    }

    public static void uhd() throws Exception {
        BING_API = "https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=10&nc=1612409408851&pid=hp&FORM=BEHPTB&uhd=1&uhdwidth=3840&uhdheight=2160";
        imagePath = Paths.get("BingDailyPhotoUHD.jpeg");
        String httpContent = HttpUtls.getHttpContent(BING_API);
        JSONObject jsonObject = JSON.parseObject(httpContent);
        JSONArray jsonArray = jsonObject.getJSONArray("images");

        jsonObject = (JSONObject) jsonArray.get(0);
        // image urls
        String url = BING_URL + (String) jsonObject.get("url");
        url = url.substring(0, url.indexOf("&"));

        URL imageURL = new URL(url); // change string to java.net.URL

        if (Files.exists(imagePath)) {
            Files.delete(imagePath);
        }
        try (InputStream in = imageURL.openStream()) {
            Files.copy(in, Paths.get("BingDailyPhotoUHD.jpeg"));
        }
    }
}
