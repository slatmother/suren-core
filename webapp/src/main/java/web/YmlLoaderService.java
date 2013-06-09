package web;

import entity.YmlCatalog;
import gateway.SYmlGateway;
import org.apache.log4j.Logger;
import parser.ShopYmlParser;
import utils.Utils;

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
@Path("/yml")
public class YmlLoaderService {
    private final Logger logger = Utils.getRootLogger();

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String exportYML(String xmlSource) {
        ShopYmlParser parser = new ShopYmlParser();
        try {
            YmlCatalog catalog = parser.parse(xmlSource);

            SYmlGateway.persistCatalog(catalog);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }

        return "Export complete";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String checkStatus() {
        return "YML loader service is up\n" + new Date().toString();
    }
}
