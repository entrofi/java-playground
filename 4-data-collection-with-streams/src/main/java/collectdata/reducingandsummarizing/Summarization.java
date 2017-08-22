package collectdata.reducingandsummarizing;


import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.time.Duration;
import java.util.List;
import java.util.LongSummaryStatistics;

import static java.util.stream.Collectors.averagingLong;
import static java.util.stream.Collectors.summarizingLong;
import static java.util.stream.Collectors.summingLong;

public class Summarization implements DemoRunner {

    public static final String ESB_CODE = "ESB";
    public static final String IST_CODE = "IST";

    @Override
    public void run() {
        DemoMetaDataHelper.addTitle("Summarization Demos :\n"
                + "1. Collectors.summingInt(), summingLong()....\n"
                + "2. Collectors.averagingInt(), averagingLong, ...\n"
                + "3. IntSummaryStatistics\n");
        summingDemo();
        averagingDemo();
        summaryStatisticsDemo();
    }


    private void summingDemo() {
        List<Flight> flightList = FlightDataDemoHelper.createFlightsFrom(ESB_CODE, 5);
        flightList.addAll(FlightDataDemoHelper.createFlightsFromTo(ESB_CODE, IST_CODE, 5));
        String message = "Given the flight plan how many hours of flight will there be from Ankara ESB to Istanbul "
                + "IST?\n"
                + "flightList.stream()\n"
                + "                    .filter(f -> \"ESB\".equals(f.getOrigin()) "
                + "&& \"IST\".equals(f.getDestination()))\n"
                + "                    .collect(\nsummingLong(f -> Duration.between(f.getDate(), "
                + "f.getScheduledArrivalTime()).toHours())\n"
                + "                    );";
        DemoMetaDataHelper.printList(message, flightList);
        long hours = flightList.stream()
                .filter(f -> ESB_CODE.equals(f.getOrigin()) && IST_CODE.equals(f.getDestination()))
                .collect(
                        summingLong(f -> Duration.between(f.getDate(), f.getScheduledArrivalTime()).toHours())
                );
        DemoMetaDataHelper.printMessage("There will be " + hours
                + " hours of flight from ESB to IST according to the given plan");

    }

    private void averagingDemo() {
        List<Flight> flightList = FlightDataDemoHelper.createFlightsFrom(ESB_CODE, 5);
        flightList.addAll(FlightDataDemoHelper.createFlightsFromTo(ESB_CODE, IST_CODE, 5));
        String message = "Given the flight plan what is the average flight hour from ESB to IST:\n"
                + ".collect(\n"
                + "        averagingLong("
                + "f -> Duration.between(f.getDate(), f.getScheduledArrivalTime()).toHours())\n"
                + "         );";
        DemoMetaDataHelper.printList(message, flightList);
        double averageHours = flightList.stream()
                .filter(f -> ESB_CODE.equals(f.getOrigin()) && IST_CODE.equals(f.getDestination()))
                .collect(
                        averagingLong(f -> Duration.between(f.getDate(), f.getScheduledArrivalTime()).toHours())
                );
        DemoMetaDataHelper.printMessage("Average flight duration from ESB to IST is " + averageHours
                + " hours according to the given plan");
    }

    private void summaryStatisticsDemo() {
        List<Flight> flightList = FlightDataDemoHelper.createFlightsFrom(ESB_CODE, 5);
        flightList.addAll(FlightDataDemoHelper.createFlightsFromTo(ESB_CODE, IST_CODE, 5));
        String message = "Let's get summary statistics for the flights from Ankara ESB to"
                + " Istanbul IST within the given flight plan:\n"
                + "...collect(summarizingLong(\n"
                + "             f -> Duration.between(f.getDate(), f.getScheduledArrivalTime()).toHours()\n"
                + "                           )\n"
                + "           );";
        DemoMetaDataHelper.printList(message, flightList);
        LongSummaryStatistics statistics = flightList.stream()
                .filter(f -> ESB_CODE.equals(f.getOrigin()) && IST_CODE.equals(f.getDestination()))
                .collect(
                        summarizingLong(f -> Duration.between(f.getDate(), f.getScheduledArrivalTime()).toHours())
                );
        DemoMetaDataHelper.printMessage("Summary statistics for flights from ESB to IST within the given flight plan:"
                + statistics);
    }
}
