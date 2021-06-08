package fr.cesi.cubes.resourceRelationnelles.request.interaction;

public class UpdateInteractionForm {

    private Boolean favorite;

    private Boolean complete;

    public UpdateInteractionForm() {

    }

    public UpdateInteractionForm( Boolean favorite, Boolean complete ) {
        super();
        this.favorite = favorite;
        this.complete = complete;
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
