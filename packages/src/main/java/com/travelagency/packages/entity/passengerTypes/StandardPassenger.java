package com.travelagency.packages.entity.passengerTypes;


import com.travelagency.packages.entity.Passenger;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("STANDARD")
@Data
@NoArgsConstructor
public class StandardPassenger extends Passenger {

    private double amount;

    public StandardPassenger(String name) {
        super(name);
    }

}
