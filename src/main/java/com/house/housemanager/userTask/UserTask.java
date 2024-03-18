package com.house.housemanager.userTask;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.house.housemanager.task.Task;
import com.house.housemanager.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserTask {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "user_id", insertable = true, updatable = false, nullable=false)
    private User user;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "task_id", insertable = true, updatable = false, nullable=false)
    private Task task;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt = new Date();

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return this.task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
