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
package web;

import entity.StatisticDataBean;
import org.apache.log4j.Logger;
import utils.Utils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 20.05.13</p>
 *
 * @version 1.0
 */
@Path("/stat")
public class StatisticHandlerService {
    private final Logger logger = Utils.getRootLogger();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getString(final StatisticDataBean bean) {
        return (bean.shop + " " + bean.age + " " + bean.country + " " + bean.sex);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String checkStatus() {
        return "Statistic service is up\n" + new Date().toString();
    }
}
