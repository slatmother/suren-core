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
package entity;

import javax.naming.NamingException;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;
import java.util.List;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 05.03.13</p>
 *
 * @version 1.0
 */
@Table(name = "shops")
@Entity
@NamedQuery(
        name = "Shop.findId",
        query = "select shop.id from Shop shop " +
                "where shop.shop_name = :shop_name " +
                "and shop.shop_company = :shop_company"
)
public class Shop extends AbstractEntity implements Serializable {

    private int id;
    private String shop_name;
    private String shop_company;
    private String shop_url;
    private List<Category> categories;
    private List<Offer> offers;

    private List<Currency> currencies;


    @javax.persistence.Column(name = "shop_id")
    @Id
    @SequenceGenerator(
            name = "idGenerator",
            sequenceName = "shop_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "idGenerator")
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    @javax.persistence.Column(name = "shop_name")
    @Basic
    @XmlElement(name = "name")
    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    @javax.persistence.Column(name = "shop_company")
    @Basic
    @XmlElement(name = "company")
    public String getShop_company() {
        return shop_company;
    }

    public void setShop_company(String shop_company) {
        this.shop_company = shop_company;
    }

    @javax.persistence.Column(name = "shop_url")
    @Basic
    @XmlElement(name = "url")
    public String getShop_url() {
        return shop_url;
    }

    public void setShop_url(String shop_url) {
        this.shop_url = shop_url;
    }

    @XmlElement(name = "offer")
    @XmlElementWrapper(name = "offers")
    @OneToMany(mappedBy = "shop")
    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @XmlElement(name = "category")
    @XmlElementWrapper(name = "categories")
    @OneToMany(mappedBy = "shop")
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @XmlElement(name = "currency")
    @XmlElementWrapper(name = "currencies")
    @OneToMany(mappedBy = "shop")
    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (id != shop.id) return false;
        if (shop_company != null ? !shop_company.equals(shop.shop_company) : shop.shop_company != null) return false;
        if (shop_name != null ? !shop_name.equals(shop.shop_name) : shop.shop_name != null) return false;
        if (shop_url != null ? !shop_url.equals(shop.shop_url) : shop.shop_url != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (shop_company != null ? shop_company.hashCode() : 0);
        result = 31 * result + (shop_name != null ? shop_name.hashCode() : 0);
        result = 31 * result + (shop_url != null ? shop_url.hashCode() : 0);
        return result;
    }

    public Object findId() throws NamingException {
        EntityManager em = getEntityManager();
//            InitialContext ctx = new InitialContext();
//            EntityManager em = (EntityManager) ctx.lookup("java:/Manager1");

        Query query = em.createNamedQuery("Shop.findId")
                .setParameter("shop_name", getShop_name())
                .setParameter("shop_company", getShop_company());
        Object ob = 0;

        try {
            ob = query.getSingleResult();
        } catch (NoResultException ex) {
            log.warn("Cannot find object with such parameters");
        }

        return ob;
    }
}
