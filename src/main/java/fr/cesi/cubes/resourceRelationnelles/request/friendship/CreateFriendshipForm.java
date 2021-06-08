package fr.cesi.cubes.resourceRelationnelles.request.friendship;

public class CreateFriendshipForm {

    private long   memberId;

    private String group;

    public CreateFriendshipForm() {
    }

    public CreateFriendshipForm( long memberId, String group ) {
        super();
        this.memberId = memberId;
        this.group = group;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMember( long memberId ) {
        this.memberId = memberId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup( String group ) {
        this.group = group;
    }

}
