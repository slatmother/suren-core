package server;

import beans.YmlCatalog;
import entity.StatisticDataBean;
import org.apache.log4j.Logger;
import dao.ShopDataProcessor;
import parser.ShopYmlParser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 04.02.13</p>
 *
 * @version 1.0
 */
@Path("/")
public class StatisticsServer {
    Logger logger = Logger.getLogger(StatisticsServer.class);
//    @EJB ShopDataProcessor processor;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getString(final StatisticDataBean bean) {
        return (bean.age + " " + bean.country + " " + bean.sex);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String export(String xmlSource) {
        ShopYmlParser parser = new ShopYmlParser();
        try {
            YmlCatalog catalog = parser.parse(xmlSource);
            ShopDataProcessor processor = new ShopDataProcessor();
            processor.persistShopData(catalog);

        } catch (Exception e) {
//            Throwable ex = e.getCause().getCause().getCause();
//            if (ex instanceof BatchUpdateException) {
//                BatchUpdateException exception = (BatchUpdateException) ex;
//                exception.getNextException().printStackTrace();
//            }  else {
//                e.printStackTrace();
//            }
//            int i = 0;
//            for (Throwable ex = e; ex != null && i < 10; ex = e.getCause()) {
//                if (ex instanceof BatchUpdateException) {
//                    BatchUpdateException exp = (BatchUpdateException) ex;
//                    exp.getNextException().printStackTrace();
//                    break;
//                    for(SQLException sqlException = exp.getNextException(); sqlException != null; sqlException = exp.getNextException()) {
//                        sqlException.printStackTrace();
//                }
//                i++;
//        }
//            return "Failed";
            logger.error(e.getMessage());
            return e.getMessage();
        }
        return "Export complete";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String checkStatus() {
        return "success " + new Date().toString();
    }
}
