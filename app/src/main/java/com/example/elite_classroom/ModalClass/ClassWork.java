package com.example.elite_classroom.ModalClass;

public class ClassWork {
    String title;
    String work_id;
    String description;
    String attachment;
    String created_date;
    String due_date;
    int type;

    public ClassWork(String title, String work_id, String description, String attachment, String created_date, String due_date, int type) {
        this.title = title;
        this.work_id = work_id;
        this.description = description;
        this.attachment = attachment;
        this.created_date = created_date;
        this.due_date = due_date;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWork_id() {
        return work_id;
    }

    public void setWork_id(String work_id) {
        this.work_id = work_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
