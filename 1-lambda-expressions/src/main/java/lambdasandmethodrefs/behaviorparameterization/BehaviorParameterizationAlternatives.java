package lambdasandmethodrefs.behaviorparameterization;


import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.util.Comparator;
import java.util.List;


public class BehaviorParameterizationAlternatives implements DemoRunner {


    private static final String SORT_BY_BEHAVIOR_PARAM_BY_FLIGHT_DATE = "**** Sort by behavior parametrization "
                + "(by flight date)";

    private static void behaviorParametrization() {

        final List<Flight> flightList = initFlightsAndPrintTitle("Behavior Parameterization by concrete classes");


        flightList.sort(new FlightComparatorByFlightNumber());
        DemoMetaDataHelper.printList("Sort by behavior parametrization (by flightnumber)", flightList);


        flightList.sort(new FlightComparatorByDate());
        DemoMetaDataHelper.printList(SORT_BY_BEHAVIOR_PARAM_BY_FLIGHT_DATE, flightList);

    }

    private static void behaviorParamByAnonymousClasses() {
        final List<Flight> flightList = initFlightsAndPrintTitle("Behavior Parameterization by anonymous classes");


        flightList.sort(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return o1.getFlightNumber().compareTo(o2.getFlightNumber());
            }
        });
        DemoMetaDataHelper.printList("**** Sort by behavior parametrization (by flightnumber)", flightList);


        flightList.sort(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        DemoMetaDataHelper.printList(SORT_BY_BEHAVIOR_PARAM_BY_FLIGHT_DATE, flightList);

    }

    private static void behaviorParamByLambda() {
        final List<Flight> flightList = initFlightsAndPrintTitle("Behavior Parameterization by lambda");

        flightList.sort((f1, f2) -> f1.getFlightNumber().compareTo(f2.getFlightNumber()));
        DemoMetaDataHelper.printList("Sort by behavior parametrization "
                    + "lambda ' (f1, f2) -> f1.getFlightNumber().compareTo(f2.getFlightNumber())'*****", flightList);


        flightList.sort((f1, f2) -> f1.getDate().compareTo(f2.getDate()));
        DemoMetaDataHelper.printList("Sort by behavior parametrization lambda"
                    +                    "'(f1, f2) -> f1.getDate().compareTo(f2.getDate())'*****", flightList);
    }

    private static void behaviorParamByMethodRefs() {
        final List<Flight> flightList = initFlightsAndPrintTitle("Behavior parameterization by method references");
        flightList.sort(Comparator.comparing(Flight::getFlightNumber));
        DemoMetaDataHelper.printList(
                    "Sort by behavior parametrization using method ref Comparator.comparing(Function)"
                               + ":Comparator : Comparator.comparing(Flight::getFlightNumber)", flightList);


        flightList.sort(Comparator.comparing(Flight::getDate));
        DemoMetaDataHelper.printList("Sort by behavior parametrization using method ref "
                    + "Comparator.comparing(Function):Comparator : Comparator.comparing(Flight::getDate)", flightList);

    }

    private static List<Flight> initFlightsAndPrintTitle(String title) {
        DemoMetaDataHelper.addTitle(title);
        List<Flight> flightList = FlightDataDemoHelper.createFlightsFromTo(null, null, 10);
        DemoMetaDataHelper.printList("Unsorted list", flightList);
        return flightList;
    }

    /**
     * Runs behavior parameterization examples.
     */
    @Override
    public void run() {
        DemoMetaDataHelper.printMessage("Problem statement: \n Sort list of flights using various criteria. "
                    + "For instance a customer might want to sort flights using flight number and other might "
                    + "want to sort by date....\n Java list class already has a sort method with "
                    + "signature void sort(Comparator c)");
        behaviorParametrization();
        behaviorParamByAnonymousClasses();
        behaviorParamByLambda();
        behaviorParamByMethodRefs();
    }

    /**
     * Behavior parametrization
     */
    private static class FlightComparatorByFlightNumber implements Comparator<Flight> {
        @Override
        public int compare(final Flight o1, final Flight o2) {
            return o1.getFlightNumber().compareTo(o2.getFlightNumber());
        }
    }

    private static class FlightComparatorByDate implements Comparator<Flight> {
        @Override
        public int compare(final Flight o1, final Flight o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }

}
