package com.travelagency.packages.repository;

import com.travelagency.packages.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DestinationRepository  extends JpaRepository<Destination, UUID> {
    Destination findByDestinationNameEquals(String destinationName);
}
