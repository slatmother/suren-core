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
package service.json;

import configuration.Configuration;
import constants.IConstants;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.junit.Test;
import utils.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 04.02.13</p>
 *
 * @version 1.0
 */
public class StatisticHandlerSeviceTest {
    private final Logger logger = Utils.getRootLogger();

    @Test
    public void test() {
        HttpClient client = new DefaultHttpClient();

        try {
            Configuration config = new Configuration(IConstants.TEST_PROPERTIES);

            HttpPost post = new HttpPost(config.getConfigProperties().getProperty("test.statistic.url"));

            File file = new File(".\\config\\test.json");
            assertTrue(file.exists() && file.isFile());

            FileEntity entity = new FileEntity(file, "application/json");
            post.setEntity(entity);

            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            assertNotNull(rd);

            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
