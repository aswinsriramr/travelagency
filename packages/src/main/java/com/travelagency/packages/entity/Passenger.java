package com.travelagency.packages.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Inheritance
@DiscriminatorColumn(name = "Type")
public abstract class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID passengerNumber;

    private String name;

    @ManyToMany
    @Setter
    private Set<TravelPackage> travelPackages = new HashSet<>();

    @ManyToMany(mappedBy = "passengers",fetch = FetchType.EAGER)
    @Getter
    private Set<Activity> activities = new HashSet<>();


    protected Passenger() {
    }

    public Passenger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UUID getPassengerNumber() {
        return passengerNumber;
    }
}
