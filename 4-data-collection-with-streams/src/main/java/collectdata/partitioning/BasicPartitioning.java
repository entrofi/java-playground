package collectdata.partitioning;

import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @author Hasan COMAK
 */
public class BasicPartitioning implements DemoRunner {


    private static final String TITLE_PART_FLIGHTS_FROM_ESB_IST = "Partition flights from ESB to IST. The advantage\n"
                + "of partitioning over filtering is that we do not loose references to other objects in the stream.\n"
                + "Map<Boolean, List<Flight>> partitionedFlights = flightList.stream()\n"
                + "         .collect(partitioningBy(\n"
                + "                  f -> \"ESB\".equals(f.getOrigin()) && \"IST\".equals(f.getDestination())\n"
                + "                  )\n"
                + "         );";

    private final List<Flight> flightList = generateFLightPlanForESB();


    @Override
    public void run() {
        DemoMetaDataHelper.addTitle("Basic Partitioning");
        DemoMetaDataHelper.printList("Flight Plan", flightList);
        partitionFlightsFromESBToISTAndOthers();
    }

    private void partitionFlightsFromESBToISTAndOthers() {
        DemoMetaDataHelper.printMessage(TITLE_PART_FLIGHTS_FROM_ESB_IST, ".");
        Map<Boolean, List<Flight>> partitionedFlights = flightList.stream()
                    .collect(
                            partitioningBy(f -> "ESB".equals(f.getOrigin()) && "IST".equals(f.getDestination()))
                    );
        for (Boolean b : partitionedFlights.keySet()) {
            String message = Boolean.TRUE.equals(b) ? "Flights from ESB to IST: \n" : "Other flights: \n";
            DemoMetaDataHelper.printList(message, partitionedFlights.get(b));
        }
    }


    private static List<Flight> generateFLightPlanForESB() {
        List<Flight> flightList = FlightDataDemoHelper.generateESBFlightPlanFor(Instant.now(), 1);
        flightList.addAll(FlightDataDemoHelper
                    .generateESBFlightPlanFor(Instant.now().plus(4, ChronoUnit.HOURS), 1));
        flightList.addAll(FlightDataDemoHelper
                    .generateESBFlightPlanFor(Instant.now().minus(2, ChronoUnit.HOURS), 1));
        flightList.addAll(FlightDataDemoHelper
                    .generateESBFlightPlanFor(Instant.now().minus(4, ChronoUnit.HOURS), 1));

        return flightList;
    }
}
