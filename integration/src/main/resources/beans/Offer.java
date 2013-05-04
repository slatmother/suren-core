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
package beans;

import javax.naming.NamingException;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 06.03.13</p>
 *
 * @version 1.0
 */
@Entity
@Table(name = "offers")
@NamedQuery(
        name = "Offer.findId",
        query = "select offer.id from Offer offer " +
                "where offer.shop_offer_id = :shop_offer_id " +
                "and offer.shop.id = :shop_id"
)
public class Offer extends AbstractEntity implements Serializable {
    private Integer id;
    private int shop_offer_id;
    private Shop shop;
    //    private String offer_type;
    private Boolean available;
    private String offer_url;
    private int offer_price;
    private String shop_currency_id;
    private Integer shop_category_id;
    private String offer_picture_url;
    private Boolean delivery;
    private String offer_name;
    private String offer_vendor;
    private String offer_vendor_code;
    private String offer_description;
    private String offer_barcode;

    private List<String> offer_ordering;

    @javax.persistence.Column(name = "id")
    @Id
    @SequenceGenerator(
            name = "idGenerator",
            sequenceName = "offer_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "idGenerator")
    public Integer getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    @javax.persistence.Column(name = "shop_offer_id")
    public int getShop_offer_id() {
        return shop_offer_id;
    }

    public void setShop_offer_id(int shop_offer_id) {
        this.shop_offer_id = shop_offer_id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    @XmlTransient
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

//    @XmlElement(name = "id")
//    @javax.persistence.Column
//    public String getOffer_type() {
//        return offer_type;
//    }

    @XmlAttribute
    @javax.persistence.Column
    public Boolean getAvailable() {
        return available;
    }

    @XmlElement(name = "url")
    @javax.persistence.Column
    public String getOffer_url() {
        return offer_url;
    }

    @XmlElement(name = "price")
    @javax.persistence.Column
    public int getOffer_price() {
        return offer_price;
    }

    @XmlElement(name = "picture")
    @javax.persistence.Column
    public String getOffer_picture_url() {
        return offer_picture_url;
    }

    @XmlElement(name = "delivery")
    @javax.persistence.Column
    public Boolean getDelivery() {
        return delivery;
    }

    @XmlElement(name = "ordering")
    @XmlElementWrapper(name = "orderingTime")
    @javax.persistence.Column
    @Transient
    public List<String> getOffer_ordering() {
        return offer_ordering;
    }

    @XmlElement(name = "name")
    @javax.persistence.Column(name = "offer_name")
    public String getOffer_name() {
        return offer_name;
    }

    @XmlElement(name = "vendor")
    @javax.persistence.Column
    public String getOffer_vendor() {
        return offer_vendor;
    }

    @XmlElement(name = "vendorCode")
    @javax.persistence.Column
    public String getOffer_vendor_code() {
        return offer_vendor_code;
    }

    @XmlElement(name = "description")
    @javax.persistence.Column(name = "offer_description")
    public String getOffer_description() {
        return offer_description;
    }

    @XmlElement(name = "barcode")
    @javax.persistence.Column
    public String getOffer_barcode() {
        return offer_barcode;
    }

    private List<OfferParam> param;

    @XmlElement(name = "param")
    @OneToMany(mappedBy = "offer")
    public List<OfferParam> getParam() {
        return param;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setOffer_url(String offer_url) {
        this.offer_url = offer_url;
    }

    public void setOffer_price(int offer_price) {
        this.offer_price = offer_price;
    }

    public void setOffer_picture_url(String offer_picture_url) {
        this.offer_picture_url = offer_picture_url;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public void setOffer_ordering(List<String> offer_ordering) {
        this.offer_ordering = offer_ordering;
    }

    public void setOffer_name(String offer_name) {
        this.offer_name = offer_name;
    }

    public void setOffer_vendor(String offer_vendor) {
        this.offer_vendor = offer_vendor;
    }

    public void setOffer_vendor_code(String offer_vendor_code) {
        this.offer_vendor_code = offer_vendor_code;
    }

    public void setOffer_description(String offer_description) {
        this.offer_description = offer_description;
    }

    public void setOffer_barcode(String offer_barcode) {
        this.offer_barcode = offer_barcode;
    }

    public void setParam(List<OfferParam> param) {
        this.param = param;
    }

    @XmlElement(name = "currencyId")
    @Basic
    @javax.persistence.Column
    public String getShop_currency_id() {
        return shop_currency_id;
    }

    public void setShop_currency_id(String shop_currency_id) {
        this.shop_currency_id = shop_currency_id;
    }

    @XmlElement(name = "categoryId")
    @Basic
    @javax.persistence.Column
    public Integer getShop_category_id() {
        return shop_category_id;
    }

    public void setShop_category_id(Integer shop_category_id) {
        this.shop_category_id = shop_category_id;
    }

    public Object findId() throws NamingException {
        EntityManager em = getEntityManager();

        Query query = em.createNamedQuery("Offer.findId")
                .setParameter("shop_offer_id", getShop_offer_id())
                .setParameter("shop_id", getShop().getId());

        Object ob = 0;
        try {
            ob = query.getSingleResult();
        } catch (NoResultException ex) {
            log.warn("Cannot find object with such parameters");
        }

        return ob;
    }
}
