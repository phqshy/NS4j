# NS4j
NationStates API wrapper for Java

Want to use the NationStates API with Java, but all the recommended wrappers are out of date? Then look no further than NS4j, the *only* good NationStates API wrapper!

Head over to the releases page to download the jar file to use!

Sample usage:
```java
import me.phqsh.ns4j.NationStatesAPI;
import me.phqsh.ns4j.enums.NationShards;

public class Example {
    public static void main(String[] args){
       NationStatesAPI api = new NationStatesAPI();
       String leader = api.getNationShards("the yeetusa", NationShards.LEADER).getLeader();
       System.out.println("The leader of The Yeetusa is " + leader); // The leader of The Yeetusa is Viktor McRain
    }
}
```
