package com.house.housemanager.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.house.housemanager.enums.TaskType;
import com.house.housemanager.recurrence.Recurrence;
import com.house.housemanager.userTask.UserTask;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskType type;

    @ManyToOne
    @JoinColumn(name = "recurrence_id", nullable = false)
    private Recurrence recurrence;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<UserTask> userTasks;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt = new Date();

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt = new Date();

    private String status;
    private Date dueDate;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskType getType() {
        return this.type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Recurrence getRecurrence() {
        return this.recurrence;
    }

    public void setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<UserTask> getUserTasks() {
        return this.userTasks;
    }

    public void setUserTasks(List<UserTask> userTasks) {
        userTasks.sort(Comparator.comparing(UserTask::getCreatedAt).reversed());
        this.userTasks = userTasks;
    }

    public String getStatus() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        if(fmt.format(this.getDueDate()).equals(fmt.format(new Date()))){
            return "pending";
        }

        return this.getDueDate().after(new Date()) ? "valid" : "expired";
    }

    public Date getDueDate() {
        this.userTasks.sort(Comparator.comparing(UserTask::getCreatedAt).reversed());
        Calendar c = Calendar.getInstance();
        c.setTime(this.getUserTasks().getFirst().getCreatedAt());
        c.add(Calendar.DATE, this.getRecurrence().getDaysBetweenFrequencies());

        return c.getTime();
    }
}
