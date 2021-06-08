package fr.cesi.cubes.resourceRelationnelles.request.friendship;

public class UpdateFriendshipGroupForm {

    private String group;

    public UpdateFriendshipGroupForm() {
    }

    public UpdateFriendshipGroupForm( String group ) {
        super();
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup( String group ) {
        this.group = group;
    }

}
