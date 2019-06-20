package iducs.springboot.board.domain;

import java.time.LocalDateTime;

public class Answer {

	private long id; // primary key
	private User writer; // 글 작성자
	private Board question; // 
	private String contents;
	private LocalDateTime createTime;
	private long size;
	
	public Answer() {}
	public Answer(User writer, String contents, Board question) {
		super();
		this.writer = writer;
		this.question = question;
		this.contents = contents;
		this.createTime = LocalDateTime.now();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public User getWriter() {
		return writer;
	}
	
	public void setWriter(User writer) {
		this.writer = writer;
	}
	
	public Board getQuestion() {
		return question;
	}
	
	public void setQuestion(Board question) {
		this.question = question;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	
	
	
	public boolean isSameUser(User user, User writer) {
		if(user.equals(writer))
			return true;
		return false;
	}
	
}
