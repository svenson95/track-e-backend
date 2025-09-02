package com.svenson95.track_e_backend.database.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user-workouts")
public class UserWorkouts {
    @Id
    private String id;

    private String userId;
    private List<WorkoutData> workouts;

    public UserWorkouts() {}

    public UserWorkouts(String userId, List<WorkoutData> workouts) {
        this.userId = userId;
        this.workouts = workouts;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public List<WorkoutData> getWorkouts() { return workouts; }
    public void setWorkouts(List<WorkoutData> workouts) { this.workouts = workouts; }

    // --- Nested Classes ---

    public static class WorkoutData {
        private String lastUpdated; // UnixTimestring
        private String name;
        private List<ListItem> list;

        public WorkoutData() {}

        public WorkoutData(Long id, String lastUpdated, String name, List<ListItem> list) {
            this.lastUpdated = lastUpdated;
            this.name = name;
            this.list = list;
        }

        public String getLastUpdated() { return lastUpdated; }
        public void setLastUpdated(String lastUpdated) { this.lastUpdated = lastUpdated; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public List<ListItem> getList() { return list; }
        public void setList(List<ListItem> list) { this.list = list; }
    }

    public static class ListItem {
        private String name;
        private ItemType type;

        public ListItem() {}

        public ListItem(String name, ItemType type) {
            this.name = name;
            this.type = type;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public ItemType getType() { return type; }
        public void setType(ItemType type) { this.type = type; }
    }

    public static class Exercise extends ListItem {
        private MuscleGroup muscleGroup;
        private String sets;
        private String reps;
        private String rest;

        public Exercise() {}

        public Exercise(String name, ItemType type, MuscleGroup muscleGroup, String sets, String reps, String rest) {
            super(name, type);
            this.muscleGroup = muscleGroup;
            this.sets = sets;
            this.reps = reps;
            this.rest = rest;
        }

        public MuscleGroup getMuscleGroup() { return muscleGroup; }
        public void setMuscleGroup(MuscleGroup muscleGroup) { this.muscleGroup = muscleGroup; }

        public String getSets() { return sets; }
        public void setSets(String sets) { this.sets = sets; }

        public String getReps() { return reps; }
        public void setReps(String reps) { this.reps = reps; }

        public String getRest() { return rest; }
        public void setRest(String rest) { this.rest = rest; }
    }

    public enum ItemType {
        EXERCISE, LABEL, SPACE
    }

    public enum MuscleGroup {
        calves, adductors, abductors, hamstrings, quads, glutes, forearms, triceps, biceps, lats, abs, core, chest, traps, shoulders, neck
    }
}
