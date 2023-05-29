package com.travelagency.packages.service;

import com.travelagency.packages.entity.Activity;
import com.travelagency.packages.entity.Destination;
import com.travelagency.packages.entity.Passenger;
import com.travelagency.packages.entity.TravelPackage;
import com.travelagency.packages.entity.passengerTypes.GoldPassenger;
import com.travelagency.packages.entity.passengerTypes.StandardPassenger;
import com.travelagency.packages.repository.ActivityRepository;
import com.travelagency.packages.repository.PassengerRepository;
import com.travelagency.packages.repository.TravelPackageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PrintService {

    private final TravelPackageRepository travelPackageRepository;
    private final PassengerRepository passengerRepository;

    private final ActivityRepository activityRepository;


    public void printItineraries(){
        List<TravelPackage> packageList = travelPackageRepository.findAll();
        for(TravelPackage travelPackage : packageList){
            System.out.println("Travel Package Name : "+travelPackage.getPackageName());
            System.out.println();
            System.out.println("Itineraries : ");
            System.out.println();
            Set<Destination> destinations = travelPackage.getItinerary();
            for(Destination destination : destinations){
                System.out.println("Destination Name : "+destination.getDestinationName());
                System.out.println();
                System.out.println("Activities : ");
                System.out.println();
                for(Activity activity : destination.getActivities()){
                    System.out.println("Activity Name : "+activity.getActivityName());
                    System.out.println("Description : "+activity.getDescription());
                    System.out.println("Cost : "+activity.getCost());
                    System.out.println("Capacity : "+activity.getCapacity());
                    System.out.println();
                }
            }
            System.out.println("-----------------------------------------------------------------------");
        }
        System.out.println(".......................................................................................................");
    }

    public void printPassengerListFromPackage(){
        List<TravelPackage> packageList = travelPackageRepository.findAll();
        for(TravelPackage travelPackage : packageList){
            System.out.println("Travel Package Name : "+travelPackage.getPackageName());
            System.out.println("Passenger Capacity : "+travelPackage.getCapacity());
            System.out.println("Passengers Enrolled : "+travelPackage.getPassengers().size());
            System.out.println();
            System.out.println("Passenger List : ");
            System.out.println();
            for(Passenger passenger : travelPackage.getPassengers()){
                System.out.println("Passenger Name : "+ passenger.getName());
                System.out.println("Passenger Number : "+passenger.getPassengerNumber());
                System.out.println();
            }
            System.out.println("-----------------------------------------------------------------------");
        }
        System.out.println(".......................................................................................................");
    }

    public void printPassengerList(){
        List<Passenger> passengers = passengerRepository.findAll();
        for(Passenger passenger : passengers){
            System.out.println("Passenger Name : "+passenger.getName());
            System.out.println("Passenger Number : "+passenger.getPassengerNumber());
            if((passenger instanceof StandardPassenger)){
                System.out.println("Balance :"+ ((StandardPassenger) passenger).getAmount());
            }else if((passenger instanceof GoldPassenger)){
                System.out.println("Balance :"+ ((GoldPassenger) passenger).getAmount());
            }
            System.out.println();
            System.out.println("Activities : ");
            System.out.println();
            for(Activity activity : passenger.getActivities()){
                System.out.println("Activity Name : "+activity.getActivityName());
                System.out.println("Destination :"+activity.getDestination().getDestinationName());
                if((passenger instanceof StandardPassenger)){
                    System.out.println("Cost :"+ activity.getCost());
                }else if((passenger instanceof GoldPassenger)){
                    System.out.println("Cost :"+ (activity.getCost()*0.1));
                }
                System.out.println();
            }
            System.out.println("-----------------------------------------------------------------------");
        }
        System.out.println(".......................................................................................................");
    }

    public void printActivityList(){
        List<Activity> activities = activityRepository.findAll();
        System.out.println("Activities with space left : ");
        for(Activity activity : activities){
            if(activity.getCapacity()-activity.getPassengers().size()>0){
                System.out.println("Activity Name "+activity.getActivityName());
                System.out.println("Capacity Left : "+(activity.getCapacity()-activity.getPassengers().size()));
                System.out.println();
            }
        }
        System.out.println(".......................................................................................................");
    }
}
