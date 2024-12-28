package me.phqsh.ns4j.containers;

import me.phqsh.ns4j.containers.nation.Nation;
import me.phqsh.ns4j.containers.region.Region;
import me.phqsh.ns4j.containers.wa.WorldAssembly;
import me.phqsh.ns4j.containers.world.World;
import me.phqsh.ns4j.containers.world.shards.Faction;

import java.util.Map;

public class ContainerType {
    public static final Map<Class<?>, String> CLASSES = Map.ofEntries(
            Map.entry(Nation.class, "nation"),
            Map.entry(Region.class, "region"),
            Map.entry(World.class, "q"),
            Map.entry(WorldAssembly.class, "wa"),
            Map.entry(Faction.class, "faction")
    );
}
