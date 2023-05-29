package com.travelagency.packages.repository;

import com.travelagency.packages.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, UUID> {
    TravelPackage findByPackageNameEquals(String packageName);
}
