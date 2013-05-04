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

import beans.YmlCatalog;

import java.io.File;
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
public interface IYMLParser {
    public YmlCatalog parse(String xmlSource) throws Exception;
    public YmlCatalog parse(InputStream xmlStream) throws Exception;
    public YmlCatalog parse(File xmlFile) throws Exception;
}
