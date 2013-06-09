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

import com.sun.xml.internal.ws.developer.Stateful;
import org.apache.log4j.Logger;
import utils.Utils;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.transaction.UserTransaction;
import javax.xml.bind.annotation.XmlTransient;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 15.04.13</p>
 *
 * @version 1.0
 */
@XmlTransient
@Stateful
public abstract class AbstractEntity {
    protected Logger log = Utils.getRootLogger();

    @PersistenceContext
    EntityManager em;

    public abstract void setId(Integer id);
    public abstract Integer getId();
    public abstract Object findId() throws NamingException;

    public AbstractEntity save() throws Exception {
        EntityManager em = getEntityManager();
        UserTransaction transaction = null;

        try {
            transaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            transaction.begin();

            AbstractEntity mergedObject = em.merge(this);
            setId(mergedObject.getId());

            transaction.commit();
        } catch (RollbackException e) {
            if (transaction != null && transaction.getStatus() == 0) {
                transaction.rollback();
            }

            throw e;
        }

        return this;
    }

    protected EntityManager getEntityManager() throws NamingException {
        InitialContext ctx = new InitialContext();
        EntityManager em = (EntityManager) ctx.lookup("java:/Manager1");
        return em;
    }
}
