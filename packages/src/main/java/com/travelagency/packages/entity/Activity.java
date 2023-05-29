package com.travelagency.packages.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String activityName;
    private String description;
    private double cost;
    private int capacity;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="activity_passenger")
    private Set<Passenger> passengers = new HashSet<>();

    @ManyToOne
    private Destination destination;

}
