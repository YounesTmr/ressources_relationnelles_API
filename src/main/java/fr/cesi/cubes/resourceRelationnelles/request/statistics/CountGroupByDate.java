package fr.cesi.cubes.resourceRelationnelles.request.statistics;

import java.util.Date;

public class CountGroupByDate {

    // @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Paris" )
    private Date date;

    private int  total;

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal( int total ) {
        this.total = total;
    }

}
