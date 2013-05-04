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
package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 11.02.13</p>
 *
 * @version 1.0
 */
public class Utils {
    public static Properties readConfig(String name) throws IOException {
        Properties properties = new Properties();
        InputStream in = Utils.class.getClassLoader().getResourceAsStream(name);
        properties.load(in);
        return properties;
    }
}
