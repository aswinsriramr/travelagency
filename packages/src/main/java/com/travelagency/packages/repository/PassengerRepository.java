package com.travelagency.packages.repository;

import com.travelagency.packages.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PassengerRepository<T extends Passenger> extends JpaRepository<Passenger, UUID>{
    Passenger findByNameEquals(String name);
}
