package com.travelagency.packages.entity.passengerTypes;
import com.travelagency.packages.entity.Passenger;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@DiscriminatorValue("PREMIUM")
@NoArgsConstructor
public class PremiumPassenger extends Passenger {

    public PremiumPassenger(String name) {
        super(name);
    }

}
