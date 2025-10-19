package com.solvd.delivery.model;

public class Achievement {

    private Long id;
    private String name;
    private String description;
    private int points;
    private boolean unlocked;

    public Achievement() {}

    public Achievement(Long id, String name, String description, int points, boolean unlocked) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.points = points;
        this.unlocked = unlocked;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public boolean isUnlocked() { return unlocked; }
    public void setUnlocked(boolean unlocked) { this.unlocked = unlocked; }

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", points=" + points +
                ", unlocked=" + unlocked +
                '}';
    }
}