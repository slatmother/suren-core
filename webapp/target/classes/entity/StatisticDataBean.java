package entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev </p>
 * <p>Date: 04.02.13</p>
 *
 * @version 1.0
 */
@XmlRootElement
public class StatisticDataBean {
    @XmlElement
    public String age;
    @XmlElement
    public String sex;
    @XmlElement
    public String country;
}
