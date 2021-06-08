package fr.cesi.cubes.resourceRelationnelles.request.statistics;

public class CountGroupEnum {

    private String name;
    private int    total;

    public CountGroupEnum( String name, int total ) {
        this.name = name;
        this.total = total;
    }

    public String getName() {
        return this.name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal( int total ) {
        this.total = total;

    }

}
