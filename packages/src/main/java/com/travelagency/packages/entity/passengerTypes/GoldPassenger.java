package com.travelagency.packages.entity.passengerTypes;



import com.travelagency.packages.entity.Passenger;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@DiscriminatorValue("GOLD")
@NoArgsConstructor
@Data
public class GoldPassenger extends Passenger {

    private double amount;

    public GoldPassenger(String name) {
        super(name);
    }
}
