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
package gateway;

import entity.*;

import java.util.List;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 25.03.13</p>
 *
 * @version 1.0
 */
public class SYmlGateway {
    /**
     *
     * @param catalog
     * @throws Exception
     */
    public static void persistCatalog(YmlCatalog catalog) throws Exception {
        Shop shop = catalog.getShop();
        persistEntity(shop);


        List<Category> categories = shop.getCategories();

        for (Category category : categories) {
            category.setShop(shop);

            persistEntity(category);
        }

        List<Offer> offers = shop.getOffers();
        for (Offer offer : offers) {
            offer.setShop(shop);

            persistEntity(offer);

            List<OfferParam> offerParams = offer.getParam();
            if (offerParams != null && !offerParams.isEmpty()) {
                for (OfferParam offerParam : offerParams) {
                    offerParam.setOffer(offer);

                    persistEntity(offerParam);
                }
            }
        }

        List<Currency> currencies = shop.getCurrencies();
        for (Currency currency : currencies) {
            currency.setShop(shop);

            persistEntity(currency);
        }
    }

    /**
     *
     * @param entity
     * @return
     * @throws Exception
     */
    private static AbstractEntity persistEntity(AbstractEntity entity) throws Exception {
        Integer entityId = (Integer) entity.findId();
        entity.setId(entityId);
        return entity.save();
    }
}
