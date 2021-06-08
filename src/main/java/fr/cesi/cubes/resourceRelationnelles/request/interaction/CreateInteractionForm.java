package fr.cesi.cubes.resourceRelationnelles.request.interaction;

public class CreateInteractionForm {

    private long    resourceId;

    private Boolean favorite;

    private Boolean complete;

    public CreateInteractionForm() {

    }

    public CreateInteractionForm( long resourceId, Boolean favorite, Boolean complete ) {
        super();
        this.resourceId = resourceId;
        this.favorite = favorite;
        this.complete = complete;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId( long resourceId ) {
        this.resourceId = resourceId;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite( Boolean favorite ) {
        this.favorite = favorite;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete( Boolean complete ) {
        this.complete = complete;
    }

}
