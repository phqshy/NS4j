package me.phqsh.ns4j.containers.nation.shards;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents the governmental spending of a nation. Represented in percent GDP per category.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Government {
    @Getter
    @XmlElement(name = "ADMINISTRATION")
    private double administration;

    @Getter
    @XmlElement(name = "DEFENCE")
    private double defence;

    @Getter
    @XmlElement(name = "EDUCATION")
    private double education;

    @Getter
    @XmlElement(name = "ENVIRONMENT")
    private double envirnomment;

    @Getter
    @XmlElement(name = "HEALTHCARE")
    private double healthcare;

    @Getter
    @XmlElement(name = "COMMERCE")
    private double commerce;

    @Getter
    @XmlElement(name = "INTERNATIONALAID")
    private double internationalAid;

    @Getter
    @XmlElement(name = "LAWANDORDER")
    private double lawAndOrder;

    @Getter
    @XmlElement(name = "PUBLICTRANSPORT")
    private double publicTransport;

    @Getter
    @XmlElement(name = "SOCIALEQUALITY")
    private double socialEquality;

    @Getter
    @XmlElement(name = "SPIRITUALITY")
    private double spirituality;

    @Getter
    @XmlElement(name = "WELFARE")
    private double welfare;
}
