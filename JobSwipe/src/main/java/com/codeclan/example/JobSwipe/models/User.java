package com.codeclan.example.JobSwipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer salary;

    @Column
    private Integer initial_salary;

    @Column
    private Integer salary_weight;

    @Column
    private String location;

    @JsonIgnoreProperties("users")
    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "users_jobs",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="savedjob_id", nullable = false, updatable = false)}
    )

    private List<SavedJob> savedJobs;

    @JsonIgnoreProperties("users")
    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "users_disliked_jobs",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="disliked_jobs_id", nullable = false, updatable = false)}
    )

    private List<DislikedJob> dislikedJobs;


    public User (String name, Integer salary, Integer initial_salary, Integer salary_weight, String location){
        this.name = name;
        this.salary = salary;
        this.initial_salary = initial_salary;
        this.salary_weight = salary_weight;
        this.location = location;
        this.savedJobs = new ArrayList<SavedJob>();
        this.dislikedJobs = new ArrayList<DislikedJob>();
    }

    public void addSavedJob(SavedJob job){
        this.savedJobs.add(job);
        updateUserSalary(job);
    }


    public User () {
    }

    public List<SavedJob> getSavedJobs() {
        return savedJobs;
    }

    public void setSavedJobs(List<SavedJob> savedJobs) {
        this.savedJobs = savedJobs;
    }

    public List<DislikedJob> getDislikedJobs() {
        return dislikedJobs;
    }

    public void setDislikedJobs(List<DislikedJob> dislikedJobs) {
        this.dislikedJobs = dislikedJobs;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getInitial_salary() {
        return initial_salary;
    }

    public void setInitial_salary(Integer initial_salary) {
        this.initial_salary = initial_salary;
    }


    public Integer getSalary_weight() {
        return salary_weight;
    }

    public void setSalary_weight(Integer salary_weight) {
        this.salary_weight = salary_weight;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int savedJobCount() {
        return savedJobs.size();
    }

    public void updateUserSalaryWeight(SavedJob job1) {
        this.salary_weight += job1.getSalary_weight();
    }

    public void updateUserSalary(SavedJob job1){
        Integer salaryDifference = (this.salary * this.salary_weight) + (job1.getSalary() * job1.getSalary_weight());
        updateUserSalaryWeight(job1);
        this.salary = salaryDifference/(this.salary_weight);
    }

    public void resetSalary() {
        this.salary = this.initial_salary;
        this.salary_weight = 5;
    }

    public void addDislikedJob(DislikedJob job) {
        this.dislikedJobs.add(job);
    }
}
