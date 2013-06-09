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
package service.xml;

import configuration.Configuration;
import constants.IConstants;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 31.03.13</p>
 *
 * @version 1.0
 */
public class YmlLoaderServiceTest {
    private final Logger logger = Logger.getLogger(YmlLoaderServiceTest.class);

    @Test
    public void test() {
        HttpClient client = new DefaultHttpClient();
        try {
            Configuration config = new Configuration(IConstants.TEST_PROPERTIES);

            HttpPost post = new HttpPost(config.getConfigProperties().getProperty("test.url"));
            String testXmlPath = config.getConfigProperties().getProperty("test.xml.path");
            assertNotNull(testXmlPath);

            logger.info("Working with xml: " + testXmlPath);

            InputStreamEntity entity = new InputStreamEntity(new FileInputStream(testXmlPath), -1);
            entity.setChunked(true);

            entity.setContentType("application/xml");
            post.setEntity(entity);

            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                logger.info(line);
                assertEquals("Export complete", line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
