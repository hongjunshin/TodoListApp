package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private int id;
	private String title;
    private String category;
    private String desc;
    private String due_date;
    private String current_date;
    private int is_completed;


    public int getIs_completed() {
		return is_completed;
	}
	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}
	public TodoItem(String title, String category, String desc, String due_date, int is_completed){
        this.title=title;
        this.category = category;
        this.desc=desc;
        this.due_date = due_date;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date=f.format(new Date());
        this.is_completed = is_completed;
    }
    public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public int getId() {
 		return id;
 	}
 	public void setId(int id) {
 		this.id = id;
 	}
 	public String checkcomplete() {
 		String check = "";
 		if(is_completed==1) {
 			check= "[V]";
 		}
 		return check;

 	}
	@Override
	public String toString() {
		return id +". ["+ category +"] "+title+checkcomplete()+" - " + desc + " - " + due_date+" - " + current_date;
	}
    
    
}
