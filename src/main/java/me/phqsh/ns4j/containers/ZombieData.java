package me.phqsh.ns4j.containers;

import lombok.Getter;
import lombok.Setter;

/**
 * Outdated, ignore
 */
public class ZombieData {
    @Getter @Setter
    private String zaction = null;
    @Getter @Setter
    private String zactionintended = null;
    @Getter @Setter
    private int survivors = 0;
    @Getter @Setter
    private int zombies = 0;
    @Getter @Setter
    private int dead = 0;

    public ZombieData(){
    }

    public ZombieData(String zaction, String zactionintended, int survivors, int zombies, int dead){
        this.dead = dead;
        this.survivors = survivors;
        this.zombies = zombies;
        this.zaction = zaction;
        this.zactionintended = zactionintended;
    }

}
