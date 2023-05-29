package com.travelagency.packages;

import com.travelagency.packages.entity.Activity;
import com.travelagency.packages.entity.Destination;
import com.travelagency.packages.entity.TravelPackage;
import com.travelagency.packages.entity.passengerTypes.GoldPassenger;
import com.travelagency.packages.entity.passengerTypes.PremiumPassenger;
import com.travelagency.packages.entity.passengerTypes.StandardPassenger;
import com.travelagency.packages.service.DestinationService;
import com.travelagency.packages.service.PassengerService;
import com.travelagency.packages.service.PrintService;
import com.travelagency.packages.service.TravelPackageService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class TravelPackageServiceApplication {

	private final PassengerService passengerService;
	private final TravelPackageService travelPackageService;
	private final DestinationService destinationService;
	private final PrintService printService;



	public static void main(String[] args) {
		SpringApplication.run(TravelPackageServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run() {

		return args -> {

			// Adding three different passengers
			passengerService.addPassenger(new StandardPassenger("John"));
			passengerService.addPassenger(new GoldPassenger("Doe"));
			passengerService.addPassenger(new PremiumPassenger("Jane"));

			//Adding balances to passengers
			passengerService.addAmountToPassenger("John",2000);
			passengerService.addAmountToPassenger("Doe",2000);

			//Adding a travel package
			travelPackageService.addTravelPackage(new TravelPackage("Goa Package",5));

			//Adding two destinations to travel package
			travelPackageService.addDestinationToPackage("Goa Package",new Destination("North Goa"));
			travelPackageService.addDestinationToPackage("Goa Package",new Destination("South Goa"));

			/*
				Adding Activity to each destination
				Used builder because of the number of parameters
			 */
			destinationService.addActivityToDestination("North Goa", Activity.builder().
										activityName("Banana Boating").description("Fun Sea Activity with friends").cost(500).capacity(5).build());
			destinationService.addActivityToDestination("North Goa", Activity.builder().
					activityName("Scuba Diving").description("Scuba diving is an underwater swimming activity involving the use of self-contained underwater breathing apparatus (SCUBA)")
					.cost(1500).capacity(2).build());

			destinationService.addActivityToDestination("South Goa", Activity.builder().
					activityName("Clubbing").description("Clubbing with friends").cost(1000).capacity(5).build());
			destinationService.addActivityToDestination("South Goa", Activity.builder().
					activityName("Surfing").description("Surf with experts and experience the adventure in sea").cost(2000).capacity(1).build());

			//Adding Passenger to Package
			travelPackageService.addPassengerToPackage("John","Goa Package");

			//Adding passenger to a activity
			destinationService.addPassengerToActivity("John","North Goa","Scuba Diving");
			destinationService.addPassengerToActivity("Doe","South Goa","Clubbing");
			destinationService.addPassengerToActivity("Jane","South Goa","Surfing");

			//Print Itineraries
			printService.printItineraries();

			//Print passengers list in a package
			printService.printPassengerListFromPackage();

			//Print all passengers
			printService.printPassengerList();

			//print activity with space left
			printService.printActivityList();
		};
	}

}
