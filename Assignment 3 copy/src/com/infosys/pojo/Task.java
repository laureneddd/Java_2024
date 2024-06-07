package com.infosys.pojo;

public class Task {
    private int taskId;
    private String taskTitle;
    private String taskText;
    private String assignedTo;  
    private String status; // mark as completed

    public Task(int taskId, String taskTitle, String taskText, String assignedTo, String status){
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskText = taskText;
        this.assignedTo = assignedTo;
        this.status = status;
    }

    public int getTaskId(){
        return taskId;
    }

    public void setTaskId(int taskId){
        this.taskId = taskId;
    }

    public String getTaskTitle(){
        return this.taskTitle;
    }

    public void setTaskTitle(String taskTitle){
        this.taskTitle = taskTitle;
    }

    public String getTaskText(){
        return this.taskText;
    }

    public void setTaskText(String taskText){
        this.taskText = taskText;
    }

    public String getAssignedTo(){
        return this.assignedTo;
    }

    public void setAssignedTo(String assignedTo){
        this.assignedTo = assignedTo;
    }

    public String getTaskStatus(){
        return status;
    }

    public void setTaskStatus(String status){
        this.status = status;
    }

    @Override
    public String toString(){
        return taskId + " " + taskTitle + " " + taskText + " " + assignedTo; 
    }
}
