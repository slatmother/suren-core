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
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 05.03.13</p>
 *
 * @version 1.0
 */

@Entity
@Table(name = "currencies")
@NamedQuery(
        name = "Currency.findId",
        query = "select currency.id from Currency currency " +
                "where currency.shop_currency_id = :shop_currency_id " +
                "and currency.shop.id = :shop_id"
)
public class Currency extends AbstractEntity implements Serializable {
    private Integer id;
    private String shop_currency_id;
    private Shop shop;
    private int currency_rate;

    @javax.persistence.Column(name = "id")
    @Id
    @SequenceGenerator(
            name = "idGenerator",
            sequenceName = "currency_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "idGenerator")
//    @XmlTransient
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlAttribute(name = "id")
    @javax.persistence.Column(name = "shop_currency_id")
    public String getShop_currency_id() {
        return shop_currency_id;
    }

    public void setShop_currency_id(String shop_currency_id) {
        this.shop_currency_id = shop_currency_id;
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

    @XmlAttribute(name = "rate")
    @Basic
    @javax.persistence.Column
    public int getCurrency_rate() {
        return currency_rate;
    }

    public void setCurrency_rate(int currency_rate) {
        this.currency_rate = currency_rate;
    }

    public Object findId() throws NamingException {
        EntityManager em = getEntityManager();

        Query query = em.createNamedQuery("Currency.findId")
                .setParameter("shop_currency_id", getShop_currency_id())
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
