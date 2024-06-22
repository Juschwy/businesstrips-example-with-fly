package ch.clip.trips.repo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class BusinessTripsBackendApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BusinessTripsBackendApplication.class);

        // workaround cause fly.io provides us an db connection string in the format: postgres://<username>:<password>@<internal-ip>:<port>/<db>?sslmode=disable
        // but postgres driver needs in format: jdbc:postgresql://<ip>:<port>/<db>
        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl == null) {
            databaseUrl = "postgresql://postgres:mysecretpassword@localhost:5432/businesstrips";
        }
        String[] databaseUrlSplit = databaseUrl.split("([/:@])");
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("spring.datasource.url", "jdbc:postgresql://" + databaseUrlSplit[5] + ":" + databaseUrlSplit[6] + "/" + databaseUrlSplit[7]);
        properties.put("spring.datasource.username", databaseUrlSplit[3]);
        properties.put("spring.datasource.password", databaseUrlSplit[4]);
        application.setDefaultProperties(properties);

        application.run(args);
    }


    @Bean
    public CommandLineRunner demoData(FlightRepository flightRepository, EmployeeRepository employeeRepository,
                                      BusinessTripRepository businessTripRepository, MeetingRepository meetingRepository) {
        // https://spring.io/guides/gs/accessing-data-jpa/
        return (args) -> {

            // acouple of employees
            Employee giuanne = new Employee(1L, "Giuanne Sarnataro", "SW-Engineer");
            Employee sam = new Employee(2L, "Sam Sony", "Web-Architect");
            Employee gino = new Employee(3L, "Gino Brambilla", "Design-Engineer");
            Employee joe = new Employee(4L, "Joe Santo", "SW-Engineer");
            Employee susan = new Employee(5L, "Susan de Vito", "UX-Engineer");

            // a couple of Flights
            Flight zhsf = new Flight(1L, 4711L, "ZH", "SF", LocalDateTime.of(2021, 2, 13, 7, 56), giuanne);
            Flight sfzh = new Flight(2L, 4719L, "SF", "ZH", LocalDateTime.of(2021, 2, 15, 15, 56), giuanne);
            Flight amsf = new Flight(3L, 4711L, "AM", "SF", LocalDateTime.of(2021, 2, 13, 15, 56), sam);
            Flight sfam = new Flight(4L, 4799L, "SF", "AM", LocalDateTime.of(2021, 2, 15, 15, 56), sam);
            Flight rmsf = new Flight(5L, 4788L, "RM", "SF", LocalDateTime.of(2021, 2, 13, 15, 56), gino);
            Flight sfrm = new Flight(6L, 4757L, "SF", "RM", LocalDateTime.of(2021, 2, 15, 15, 56), gino);

            employeeRepository.save(giuanne);
            employeeRepository.save(sam);
            employeeRepository.save(gino);
            employeeRepository.save(joe);
            employeeRepository.save(susan);

            flightRepository.save(zhsf);
            flightRepository.save(sfzh);
            flightRepository.save(amsf);
            flightRepository.save(sfam);
            flightRepository.save(rmsf);
            flightRepository.save(sfrm);

            // save a couple of BusinessTrips
            BusinessTrip bt01 = new BusinessTrip(1L, "BT01", "San Francisco World Trade Center on new Server/IOT/Client ", LocalDateTime.of(2021, 2, 13, 0, 0), LocalDateTime.of(2021, 2, 15, 16, 56));
            BusinessTrip bt02 = new BusinessTrip(2L, "BT02", "Santa Clara Halley on new Server/IOT/Client", LocalDateTime.of(2021, 6, 23, 9, 0), LocalDateTime.of(2021, 6, 27, 16, 56));
            BusinessTrip bt03 = new BusinessTrip(3L, "BT03", "San Cose City Halley on Docker/IOT/Client", LocalDateTime.of(2021, 12, 13, 9, 0), LocalDateTime.of(2021, 12, 15, 16, 56));

            businessTripRepository.save(bt01);
            businessTripRepository.save(bt02);
            businessTripRepository.save(bt03);

            // save a couple of meetings

            Meeting one = new Meeting(1L, "One Conference", "Key Note on One Conference", bt01);
            Meeting zero = new Meeting(2L, "Zero Conference", "Workshop Zero on One Conference", bt01);
            Meeting handsOn = new Meeting(3L, "One Conference", "HandsOn on One Conference", bt02);
            Meeting workOn = new Meeting(4L, "One Conference", "Key Note on One Conference", bt02);
            Meeting stayTuned = new Meeting(5L, "One Conference", "Key Note on One Conference", bt03);

            meetingRepository.save(one);
            meetingRepository.save(zero);
            meetingRepository.save(handsOn);
            meetingRepository.save(workOn);
            meetingRepository.save(stayTuned);


            // List<Trips>

            List<BusinessTrip> wishTrips = new ArrayList<>();

            wishTrips.add(bt01);
            wishTrips.add(bt02);

            gino.setTrips(wishTrips);
            employeeRepository.save(gino);
        };
    }
}
