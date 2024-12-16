package com.example.model;

public class Task {
    private int id;
    private String description;
    private Boolean completed;

    public Task(int id, String description, Boolean completed) {
        this.id = id;
        this.description = description;
        this.completed = completed;
        
    }

    public int getId() {
        return id;
    }
    public void  setId(int id){
        this.id=id;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public Boolean getCompleted(){
        return completed;
    }
    public void setCompleted(boolean completed){
        this.completed=completed;
    }
    
}
