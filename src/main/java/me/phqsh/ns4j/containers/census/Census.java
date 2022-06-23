package me.phqsh.ns4j.containers.census;


import lombok.Getter;
import me.phqsh.ns4j.enums.CensusType;

import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Census {
    @Getter @XmlTransient
    private HashMap<CensusType, CensusScale> SCALES;
    private List<CensusScale> SCALE;

    public Census(){
    }

    public void initScales(){
        SCALES = new HashMap<>();

        for (CensusScale i : SCALE){
            for (CensusType e : CensusType.values()){
                if (i.getId() == e.getId()){
                    SCALES.put(e, i);
                    break;
                }
            }
        }
    }
}
