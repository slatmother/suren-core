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
import javax.xml.bind.annotation.XmlValue;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 15.04.13</p>
 *
 * @version 1.0
 */
@Entity
@Table(name = "offer_params")
@NamedQuery(
        name = "OfferParam.findId",
        query = "select offerparam.id from OfferParam offerparam " +
                "where offerparam.param_name = :param_name " +
                "and offerparam.offer.id = :offer_id"
)
public class OfferParam extends AbstractEntity {
    private Integer id;
    private String param_name;
    private Offer offer;
    private String param_value;

    @javax.persistence.Column(name = "param_id")
    @Id
    @SequenceGenerator(
            name = "idGenerator",
            sequenceName = "offer_param_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "idGenerator")
    @XmlTransient
    public Integer getId() {
        return id;
    }

    @XmlAttribute(name = "name")
    @Basic
    @javax.persistence.Column
    public String getParam_name() {
        return param_name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id")
    @XmlTransient
    public Offer getOffer() {
        return offer;
    }

    @javax.persistence.Column
    @Basic
    @XmlValue
    public String getParam_value() {
        return param_value;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setParam_name(String param_name) {
        this.param_name = param_name;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public void setParam_value(String param_value) {
        this.param_value = param_value;
    }

    public Object findId() throws NamingException {
        EntityManager em = getEntityManager();

        Query query = em.createNamedQuery("OfferParam.findId")
                .setParameter("param_name", getParam_name())
                .setParameter("offer_id", getOffer().getId());
        Object ob = 0;

        try {
            ob = query.getSingleResult();
        } catch (NoResultException ex) {
            log.warn("Cannot find object with such parameters");
        }

        return ob;
    }
}
