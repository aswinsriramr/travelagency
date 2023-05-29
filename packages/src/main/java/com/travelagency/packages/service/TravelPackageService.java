package com.travelagency.packages.service;

import com.travelagency.packages.entity.Destination;
import com.travelagency.packages.entity.Passenger;
import com.travelagency.packages.entity.TravelPackage;
import com.travelagency.packages.repository.TravelPackageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TravelPackageService{

    private final TravelPackageRepository travelPackageRepository;
    private final PassengerService passengerService;

    public void addTravelPackage(TravelPackage travelPackage){
        travelPackageRepository.save(travelPackage);
    }

    public void addDestinationToPackage(String packageName,Destination destination){
        TravelPackage travelPackage = travelPackageRepository.findByPackageNameEquals(packageName);
        travelPackage.getItinerary().add(destination);
        travelPackageRepository.save(travelPackage);
    }

    public void addPassengerToPackage(String passengerName,String packageName) throws Exception {
        Passenger passenger = passengerService.findByPassengerName(passengerName);
        TravelPackage travelPackage = travelPackageRepository.findByPackageNameEquals(packageName);
        //Check the passenger capacity of the package and then add passenger
        if(travelPackage.getPassengers().size()<=travelPackage.getCapacity())
            travelPackage.getPassengers().add(passenger);
        else
            throw new Exception("Package Capacity reached");
        travelPackageRepository.save(travelPackage);
    }

}
