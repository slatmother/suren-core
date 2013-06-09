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
package configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 20.05.13</p>
 *
 * @version 1.0
 */
public class Configuration {
    private final Properties configProperties = new Properties();

    public Configuration(String propertyFileName) throws IOException {
        InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream(propertyFileName);

        if (inputStream == null) {
            throw new FileNotFoundException("property file " + propertyFileName + " not found in the classpath");
        }

        configProperties.load(inputStream);
    }

    public Properties getConfigProperties() {
        return configProperties;
    }
}
