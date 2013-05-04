/*
* $Id
*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package service;

import entity.TestCore;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 04.02.13</p>
 *
 * @version 1.0
 */
public class StatisticsServerJSONTest extends TestCore {
    private final Logger logger = Logger.getLogger(StatisticsServerJSONTest.class);

    @Override
    public void test() {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(getTestProperties().getProperty("test.url"));
        StringEntity entity;
        try {
            entity = new StringEntity("{\"age\":\"38\",\"sex\":\"Male\",\"country\":\"Russia\"}");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        entity.setContentType("application/json");
        post.setEntity(entity);
        try {
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
