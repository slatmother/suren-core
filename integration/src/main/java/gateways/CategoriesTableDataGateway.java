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
package gateways;

import beans.AbstractEntity;
import beans.Category;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 30.04.13</p>
 *
 * @version 1.0
 */
//@Singleton
public class CategoriesTableDataGateway extends AbstractTableDataGateway {
    //    @PersistenceUnit
//    EntityManager em;

    public Object findId(AbstractEntity entity) throws NamingException {
        if (entity instanceof Category) {
            Category category = (Category) entity;

            InitialContext ctx = new InitialContext();
            EntityManager em = (EntityManager) ctx.lookup("java:/Manager1");

            Query query = em.createNamedQuery("Category.findId")
                                .setParameter("shop_category_id", category.getShop_category_id())
                                .setParameter("shop_id", category.getShop().getId());
            Object ob = 0;
            try {
                ob = query.getSingleResult();
            } catch (NoResultException ex) {
            }

            return ob;
        }

        return 0;
    }
}
