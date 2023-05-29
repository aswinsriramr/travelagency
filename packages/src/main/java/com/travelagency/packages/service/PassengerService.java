package com.travelagency.packages.service;

import com.travelagency.packages.entity.Passenger;
import com.travelagency.packages.entity.passengerTypes.GoldPassenger;
import com.travelagency.packages.entity.passengerTypes.StandardPassenger;
import com.travelagency.packages.repository.PassengerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public void addPassenger(Passenger passenger){
        passengerRepository.save(passenger);
    }

    public void addAmountToPassenger(String passengerName,double amount) throws Exception {
        Passenger passenger = passengerRepository.findByNameEquals(passengerName);

        //Adding amount to passenger by casting to it's type
        if(passenger instanceof StandardPassenger){
            StandardPassenger standardPassenger = (StandardPassenger) passenger;
            standardPassenger.setAmount(amount);
            passengerRepository.save(standardPassenger);
        }else if(passenger instanceof GoldPassenger){
            GoldPassenger standardPassenger = (GoldPassenger) passenger;
            standardPassenger.setAmount(amount);
            passengerRepository.save(standardPassenger);
        }else{
            throw new Exception("Wrong passenger type to add amount");
        }
    }

    public Passenger findByPassengerName(String passengerName){
        return passengerRepository.findByNameEquals(passengerName);
    }


}
