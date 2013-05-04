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
package dao;

import beans.Shop;
import beans.YmlCatalog;
import gateways.*;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 25.03.13</p>
 *
 * @version 1.0
 */
//@Stateful
public class ShopDataProcessor {
    private final ShopsTableDataGateway shop_gateway = new ShopsTableDataGateway();
    private final OffersTableDataGateway offer_gateway = new OffersTableDataGateway();
    private final CategoriesTableDataGateway category_gateway = new CategoriesTableDataGateway();
    private final CurrenciesTableDataGateway currency_gateway = new CurrenciesTableDataGateway();
    private final OfferParamsTableDataGateway offer_param_gateway = new OfferParamsTableDataGateway();


    public void persistShopData(YmlCatalog catalog) throws Exception {
        Shop shop = catalog.getShop();
//        Integer shop_id = (Integer) shop_gateway.findId(shop);
//        shop.setId(shop_id);
//        shop.save();

        Integer shop_id = (Integer) shop.findId();
        shop.setId(shop_id);
        shop.save();

//        List<Category> categories = shop.getCategories();
//        for (Category category : categories) {
//            category.setShop(shop);
//
//            Integer category_id = (Integer) category_gateway.findId(category);
//            category.setId(category_id);
//            category.save();
//        }
//
//        List<Offer> offers = shop.getOffers();
//        for (Offer offer : offers) {
//            offer.setShop(shop);
//
//            Integer offer_id = (Integer) offer_gateway.findId(offer);
//            offer.setId(offer_id);
//
//            offer.save();
//
//            List<OfferParam> offerParams = offer.getParam();
//            if (offerParams != null && !offerParams.isEmpty()) {
//                for (OfferParam offerParam : offerParams) {
//                    offerParam.setOffer(offer);
//
//                    Integer offerParam_id = (Integer) offer_param_gateway.findId(offerParam);
//                    offerParam.setId(offerParam_id);
//
//                    offerParam.save();
//                }
//            }
//        }
//
//        List<Currency> currencies = shop.getCurrencies();
//        for (Currency currency : currencies) {
//            currency.setShop(shop);
//
//            Integer currency_id = (Integer) currency_gateway.findId(currency);
//            currency.setId(currency_id);
//
//            currency.save();
//        }
    }
}
