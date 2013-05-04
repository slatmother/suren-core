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

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

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
@Table(name = "categories")
@NamedQuery(
        name = "Category.findId",
        query = "select category.id from Category category " +
                "where category.shop_category_id = :shop_category_id " +
                "and category.shop.id = :shop_id"
)
public class Category extends AbstractEntity implements Serializable {
    private Integer id;

    private int shop_category_id;

    private Integer shop_parent_id;

    private String category_name;

    private Shop shop;

    @javax.persistence.Column(name = "id")
    @Id
    @SequenceGenerator(
            name = "idGenerator",
            sequenceName = "shop_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "idGenerator")
    @XmlTransient
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @XmlAttribute(name = "id")
    @Basic
    @javax.persistence.Column(name = "shop_category_id")
    public int getShop_category_id() {
        return shop_category_id;
    }

    public void setShop_category_id(int shop_category_id) {
        this.shop_category_id = shop_category_id;
    }

    @XmlAttribute(name = "parentId")
    @Basic
    @javax.persistence.Column(name = "shop_parent_id")
    public Integer getShop_parent_id() {
        return shop_parent_id;
    }

    public void setShop_parent_id(Integer shop_parent_id) {
        this.shop_parent_id = shop_parent_id;
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

    @XmlValue
    @Basic
    @javax.persistence.Column(name = "category_name")
    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
