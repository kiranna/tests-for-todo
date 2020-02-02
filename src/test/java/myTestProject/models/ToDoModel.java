package myTestProject.models;

public class ToDoModel {
	
	private String ID;
	private String title;
	private String priority;
	
	public ToDoModel(String title, String priority) {
		super();
		this.title = title;
		this.priority = priority;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
}
