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

import beans.*;
import junit.framework.TestCase;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 05.03.13</p>
 *
 * @version 1.0
 */
public class OzonXMLParserTest extends TestCase {
    public static Logger log = Logger.getLogger(OzonXMLParserTest.class);
    private static Properties testProperties;

    static {
        try {
            testProperties = new Properties();
            InputStream in = OzonXMLParserTest.class.getClassLoader().getResourceAsStream("test.properties");
            testProperties.load(in);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    protected static void initTestConfig() {

    }

    public void testParser() {
        ShopYmlParser parser = new ShopYmlParser();
        File xmlFile = new File(testProperties.getProperty("test.xml.path"));
//        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Shop.xml");
//        File xmlFile = new File("D:\\SVNProjects\\StatisticsServer\\test_config\\Shop.xml");
        log.debug("-----------------------------START DEBUGGING-----------------------------");
        try {
            InputStream stream = new FileInputStream(xmlFile);
            YmlCatalog catalog = parser.parse(stream);
            log.debug("Catalog " + catalog);
            log.debug("Catalog date " + catalog.getDate());
//            log.debug("Catalog shop " + catalog.shop);
            log.debug("Catalog shop " + catalog.getShop());

            if (catalog.getShop() != null) {
                Shop shop = catalog.getShop();

                log.debug("Shop " + shop);
                log.debug("Shop name " + shop.getShop_name());
                log.debug("Shop url " + shop.getShop_url());
                log.debug("Shop company " + shop.getShop_company());

                showCurrencies(shop);
                showCategories(shop);
                showOffers(shop);

            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            log.debug("-----------------------------END DEBUGGING-----------------------------\n");
        }
    }

    protected void showCurrencies(Shop shop) {
        List<Currency> currencies = shop.getCurrencies();
        if (currencies != null) {
            log.debug("#########-LIST OF CURRENCIES-#########");
            for (Currency currency : currencies) {
                log.debug("Currency " + currency);
                log.debug("Currency id " + currency.getId());
                log.debug("Currency rate " + currency.getCurrency_rate());
            }
            log.debug("#########- END OF LIST -#########");
        }
    }

    protected void showCategories(Shop shop) {
//        Shop.Categories categories = shop.getCategories();
//        Shop categories = shop.getCategory();

        List<Category> categoryList = shop.getCategories();
        log.debug("#########- LIST OF CATEGORIES -#########");
        for (Category category : categoryList) {
            log.debug("Category " + category);
            log.debug("Category id " + category.getId());
            log.debug("Category name " + category.getCategory_name());
//            log.debug("Category id " + category.category_id);
//            log.debug("Category name " + category.category_name);
        }
        log.debug("#########- END OF LIST -#########");
    }

    protected void showOffers(Shop shop) {
        List<Offer> offerList = shop.getOffers();
        log.debug("#########- LIST OF OFFERS -#########");
        for (Offer offer : offerList) {
            log.debug("Offer " + offer);
            log.debug("Offer id " + offer.getId());
            log.debug("Offer description " + offer.getOffer_description());
            log.debug("Offer price " + offer.getOffer_price());
            log.debug("Offer delivery " + offer.getDelivery());


            List<OfferParam> params = offer.getParam();
            if (params != null) {
                log.debug("#########- LIST OF OFFER'S PARAMS -#########");
                for (OfferParam param : params) {
                    log.debug("Offer params name " + param.getParam_name());
                    log.debug("Offer params value " + param.getParam_value());
                }
                log.debug("#########- END OF PARAM'S LIST -#########");
            }
        }
        log.debug("#########- END OF LIST -#########");
    }
}
