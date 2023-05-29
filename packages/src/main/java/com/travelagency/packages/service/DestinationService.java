package com.travelagency.packages.service;

import com.travelagency.packages.entity.Activity;
import com.travelagency.packages.entity.Destination;
import com.travelagency.packages.entity.Passenger;
import com.travelagency.packages.entity.passengerTypes.GoldPassenger;
import com.travelagency.packages.entity.passengerTypes.StandardPassenger;
import com.travelagency.packages.repository.DestinationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;
    private final PassengerService passengerService;
    private final TravelPackageService travelPackageService;

    public void addDestination(Destination destination){
        destinationRepository.save(destination);
    }

    public void addActivityToDestination(String destinationName, Activity activity){
        //Get destination by name
        Destination destination = destinationRepository.findByDestinationNameEquals(destinationName);
        //Get the activity set and add the activity
        destination.getActivities().add(activity);
        //Saving the updated destination
        destinationRepository.save(destination);
    }

    public void addPassengerToActivity(String passengerName,String destinationName,String activityName) throws Exception {

        //Get Passenger by name
        Passenger passenger = passengerService.findByPassengerName(passengerName);
        //Get destination by name
        Destination destination = destinationRepository.findByDestinationNameEquals(destinationName);
        //Get the activity by name by filtering through the set
        Activity activity = destination.getActivities().stream().filter( act -> act.getActivityName().equals(activityName)).findAny().orElse(null);

        //Check the capacity of the activity
        if(activity.getPassengers().size()==activity.getCapacity())
            throw new Exception("Activity capacity reached");

        //Check for type of passenger and set the amount accordingly
        if(passenger instanceof StandardPassenger){
            if(((StandardPassenger) passenger).getAmount()-activity.getCost()>=0){
                ((StandardPassenger) passenger).setAmount(((StandardPassenger) passenger).getAmount()- activity.getCost());
                passengerService.addPassenger(passenger);
            }else{
                throw new Exception("Insufficient Funds");
            }
        }else if(passenger instanceof GoldPassenger){
            if(((GoldPassenger) passenger).getAmount()-(activity.getCost()*0.10)>=0){
                ((GoldPassenger) passenger).setAmount(((GoldPassenger) passenger).getAmount()-(activity.getCost()*0.10));
                passengerService.addPassenger(passenger);
            }else{
                throw new Exception("Insufficient Funds");
            }
        }
        //To make sure that the passenger is added to package and also the activity
        travelPackageService.addPassengerToPackage(passengerName,destination.getTravelPackage().getPackageName());

        //Add activity to passenger
        activity.getPassengers().add(passenger);

        //Saving the changes to database
        destinationRepository.save(destination);

    }
}
