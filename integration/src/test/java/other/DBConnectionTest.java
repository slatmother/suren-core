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
package other;

import junit.framework.TestCase;

import java.sql.*;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 17.02.13</p>
 *
 * @version 1.0
 */
public class DBConnectionTest extends TestCase {
    private static final String URL = "jdbc:postgresql://194.28.132.219/testdb";
    private static final String USER = "pguser";
    private static final String PASSWORD = "123";


    public void test_1() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
