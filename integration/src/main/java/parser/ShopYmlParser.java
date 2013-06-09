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
package parser;

import entity.YmlCatalog;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 04.03.13</p>
 *
 * @version 1.0
 */
public class ShopYmlParser implements IYMLParser {
    public static Logger log = Logger.getLogger(ShopYmlParser.class);

    public YmlCatalog parse(String xmlSource) throws Exception {
        InputStream xmlStream = new ByteArrayInputStream(new String(xmlSource.getBytes("Cp1251"), "Cp1251").getBytes("utf-8"));
        return parse(xmlStream);
    }

    public YmlCatalog parse(InputStream xmlStream) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(YmlCatalog.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        YmlCatalog catalog = (YmlCatalog) unmarshaller.unmarshal(xmlStream);
        return catalog;
    }

    public YmlCatalog parse(File xmlFile) throws Exception {
        InputStream xmlStream = new FileInputStream(xmlFile);
        return parse(xmlStream);
    }
}
