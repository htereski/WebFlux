package br.com.udemy.tasks.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Task {

    @Id
    private String id;

    private String title;

    private String description;

    private int priority;

    private TaskState state;

    private Address address;

    private LocalDate created;

    public Task() {}

    public Task(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        this.state = builder.state;
        this.address = builder.address;
        this.created = builder.created;
    }

    public Task insert() {
        return builderFrom(this)
                .withState(TaskState.INSERT)
                .withCreated(LocalDate.now())
                .build();
    }

    public Task update(Task oldTask) {
        return builderFrom(this)
                .withState(oldTask.getState())
                .build();
    }

    public Task updateAddress(Address address) {
        return builderFrom(this)
                .withAddress(address)
                .build();
    }

    public Task start() {
        return builderFrom(this)
                .withState(TaskState.DOING)
                .build();
    }

    public Task done() {
        return builderFrom(this)
                .withState(TaskState.DONE)
                .build();
    }

    public Task createdNow() {
        return builderFrom(this)
                .withCreated(LocalDate.now())
                .build();
    }

    public boolean createdIsEmpty() {
        return this.created == null;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public TaskState getState() {
        return state;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDate getCreated() {
        return created;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builderFrom(Task task) {
        return new Builder(task);
    }

    public static class Builder {
            private String id;

            private String title;

            private String description;

            private int priority;

            private TaskState state;

            private Address address;

            private LocalDate created;

            public Builder() {}

            public Builder(Task task) {
                this.id = task.id;
                this.title = task.title;
                this.description = task.description;
                this.priority = task.priority;
                this.state = task.state;
                this.address = task.address;
                this.created = task.created;
            }

            public Builder withId(String id) {
                this.id = id;
                return this;
            }

            public Builder withTitle(String title) {
                this.title = title;
                return this;
            }

            public Builder withDescription(String description) {
                this.description = description;
                return this;
            }

            public Builder withPriority(int priority) {
                this.priority = priority;
                return this;
            }

            public Builder withState(TaskState state) {
                this.state = state;
                return this;
            }

            public Builder withAddress(Address address) {
                this.address = address;
                return this;
            }

            public Builder withCreated(LocalDate created) {
                this.created = created;
                return this;
            }

            public Task build() {
                return new Task(this);
            }
    }

}
