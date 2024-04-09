package com.house.housemanager.userTask;

import java.util.UUID;

public class UserTaskDTO {

    private UUID userId;
    private UUID taskId;

    public UserTaskDTO() {
    }

    public UserTaskDTO(UUID userId, UUID taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }
}
