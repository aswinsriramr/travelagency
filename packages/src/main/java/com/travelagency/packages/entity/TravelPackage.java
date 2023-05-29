package com.travelagency.packages.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String packageName;
    private int capacity;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "travel_package_id")
    private Set<Destination> itinerary = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="package_passenger")
    @Setter
    private Set<Passenger> passengers = new HashSet<>();


    public TravelPackage(String packageName, int capacity) {
        this.packageName = packageName;
        this.capacity = capacity;
    }
}
