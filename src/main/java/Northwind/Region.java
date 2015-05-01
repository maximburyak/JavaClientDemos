package Northwind;

import java.util.List;

/**
 * Created by Maxim on 01/05/2015.
 */
public class Region {
    private String id ;
    private String name ;
    private List<Territory> territories ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(List<Territory> territories) {
        this.territories = territories;
    }
}
