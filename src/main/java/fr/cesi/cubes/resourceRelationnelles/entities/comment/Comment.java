package fr.cesi.cubes.resourceRelationnelles.entities.comment;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.entities.resource.Resource;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String commentBodyText;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
	private Timestamp dateOfComment;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private Member member;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "comment_id")
	private Comment comment;

	@OneToMany(mappedBy = "comment")
	private Set<Comment> comments = new HashSet<Comment>();

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_resources")
	private Resource resource;

	public Comment(long id, String commentBodyText, Timestamp dateOfComment, Member member, Comment comment,
			Set<Comment> comments, Resource resource) {
		super();
		this.id = id;
		this.commentBodyText = commentBodyText;
		this.dateOfComment = dateOfComment;
		this.member = member;
		this.comment = comment;
		this.comments = comments;
		this.resource = resource;
	}

	public Comment(String commentBodyText, Timestamp dateOfComment, Member member) {
		super();
		this.commentBodyText = commentBodyText;
		this.dateOfComment = dateOfComment;
		this.member = member;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommentBodyText() {
		return commentBodyText;
	}

	public void setCommentBodyText(String commentBodyText) {
		this.commentBodyText = commentBodyText;
	}

	public Date getDateOfComment() {
		return dateOfComment;
	}

	public void setDateOfComment(Timestamp date) {
		this.dateOfComment = date;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentBodyText=" + commentBodyText + ", dateOfComment=" + dateOfComment
				+ ", member=" + member + ", comment=" + comment + ", comments=" + comments + ", resource=" + resource
				+ "]";
	}

}
