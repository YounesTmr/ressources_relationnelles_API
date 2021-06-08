package fr.cesi.cubes.resourceRelationnelles.request.historic;

import fr.cesi.cubes.resourceRelationnelles.entities.historic.Action;

public class CreateHistoricForm {

	private long mainMemberId;

	private Long friendId;

	private Long resourceId;

	private Action action;

	public CreateHistoricForm() {

	}

	public CreateHistoricForm(long mainMemberId, Long friendId, Long resourceId, Action action) {
		super();
		this.mainMemberId = mainMemberId;
		this.friendId = friendId;
		this.resourceId = resourceId;
		this.action = action;
	}

	public long getMainMemberId() {
		return mainMemberId;
	}

	public void setMainMemberId(long mainMemberId) {
		this.mainMemberId = mainMemberId;
	}

	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}
