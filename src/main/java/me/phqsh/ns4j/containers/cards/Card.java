package me.phqsh.ns4j.containers.cards;

import lombok.Getter;
import me.phqsh.ns4j.containers.Container;
import me.phqsh.ns4j.containers.TargetedContainer;
import me.phqsh.ns4j.containers.cards.shards.Market;
import me.phqsh.ns4j.containers.cards.shards.Trade;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@XmlRootElement(name="CARD")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class Card extends Container implements TargetedContainer {
    @XmlElement(name = "CARDID")
    private int cardId;

    @XmlElement(name = "CATEGORY")
    private String category;

    @XmlElement(name = "MARKET_VALUE")
    private double marketValue;

    @XmlElement(name = "SEASON")
    private int season;

    @XmlElement(name = "FLAG")
    private String flag;

    @XmlElement(name = "GOVT")
    private String pretitle;

    @XmlElement(name = "NAME")
    private String nation;

    @XmlElement(name = "REGION")
    private String region;

    @XmlElement(name = "SLOGAN")
    private String slogan;

    @XmlElement(name = "TYPE")
    private String type;

    @XmlElementWrapper(name = "MARKETS") @XmlElement(name = "MARKET")
    private List<Market> markets;

    @XmlElementWrapper(name = "OWNERS") @XmlElement(name = "OWNER")
    private List<String> ownersList;

    private Map<String, Long> owners;

    @XmlElementWrapper(name = "TRADES") @XmlElement(name = "TRADE")
    private List<Trade> trades;

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent){
        owners = ownersList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
