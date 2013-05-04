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
package entity;

import junit.framework.TestCase;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 25.03.13</p>
 *
 * @version 1.0
 */
public class TestCore extends TestCase {
    public static Logger log = Logger.getLogger(TestCore.class);
    private static Properties testProperties;

    static {
        try {
            testProperties = new Properties();
            InputStream in = TestCore.class.getClassLoader().getResourceAsStream("test.properties");
            testProperties.load(in);
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void test() throws IOException {

    }

    public static Properties getTestProperties() {
        return testProperties;
    }
}
