package chapter10;

import java.sql.Timestamp;

public class TodoValueObject {
	Integer id;
	String title;
	String task;
	Timestamp limitdate;
	Timestamp lastupdate;
	String userid;
	String label;
	Integer status;
	String inputLimit;
	private String filename;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle(){
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTask(){
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}

	public Timestamp getLimitdate(){
		return limitdate;
	}
	public void setLimitdate(Timestamp limitdate) {
		this.limitdate = limitdate;
	}

	public Timestamp getLastupdate(){
		return lastupdate;
	}
	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getUserid(){
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLabel(){
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getInputLimit(){
		return inputLimit;
	}
	public void setInputLimit(String inputLimit) {
		this.inputLimit = inputLimit;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}

