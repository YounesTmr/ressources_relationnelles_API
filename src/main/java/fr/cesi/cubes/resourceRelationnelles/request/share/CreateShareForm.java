package fr.cesi.cubes.resourceRelationnelles.request.share;

public class CreateShareForm {

	private Long recipientId;

	private long resourceId;

	private String group;

	public CreateShareForm() {
	}

	public CreateShareForm(Long recipientId, long resourceId, String group) {
		super();
		this.recipientId = recipientId;
		this.resourceId = resourceId;
		this.group = group;
	}

	public long getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}

	public long getResourceId() {
		return resourceId;
	}

	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
