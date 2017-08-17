package lambdasandmethodrefs.behaviorparameterization;



import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;
import net.entrofi.studies.java8.demohelpers.models.Flight;
import net.entrofi.studies.java8.demohelpers.sampledatahelpers.FlightDataDemoHelper;

import java.util.Comparator;
import java.util.List;


public class BehaviorParameterizationAlternatives implements DemoRunner {


    @Override
    public void run() {
        System.out.println("Problem statement: \n Sort list of flights using various criteria. For instance a " +
                "customer might want to sort flights using flight number and other might want to sort by date...." +
                "\n Java list class already has a sort method with signature void sort(Comparator c)");
        behaviorParametrization();
        behaviorParamByAnonymousClasses();
        behaviorParamByLambda();
        behaviorParamByMethodRefs();
    }


    static void behaviorParametrization() {

        List<Flight> flightList = initFlightsAndPrintTitle("Behavior Parameterization by concrete classes");

        
        flightList.sort(new FlightComparatorByFlightNumber());
        DemoMetaDataHelper.printList("Sort by behavior parametrization (by flightnumber)", flightList);


        flightList.sort(new FlightComparatorByDate());
        DemoMetaDataHelper.printList("**** Sort by behavior parametrization (by flight date)", flightList);

    }

    static void behaviorParamByAnonymousClasses() {
        List<Flight> flightList = initFlightsAndPrintTitle("Behavior Parameterization by anonymous classes");


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
        DemoMetaDataHelper.printList("**** Sort by behavior parametrization (by flight date)", flightList);

    }



    static void behaviorParamByLambda() {
        List<Flight> flightList = initFlightsAndPrintTitle("Behavior Parameterization by lambda");
        
        flightList.sort((f1, f2) -> f1.getFlightNumber().compareTo(f2.getFlightNumber()));
        DemoMetaDataHelper.printList("Sort by behavior parametrization lambda" +
                "' (f1, f2) -> f1.getFlightNumber().compareTo(f2.getFlightNumber())'*****", flightList);

        
        flightList.sort((f1, f2) -> f1.getDate().compareTo(f2.getDate()));
        DemoMetaDataHelper.printList("Sort by behavior parametrization lambda" +
                "'(f1, f2) -> f1.getDate().compareTo(f2.getDate())'*****", flightList);
    }



    static void behaviorParamByMethodRefs() {
        List<Flight> flightList = initFlightsAndPrintTitle("Behavior parameterization by method references");
        flightList.sort(Comparator.comparing(Flight::getFlightNumber));
        DemoMetaDataHelper.printList(
                "Sort by behavior parametrization using method ref Comparator.comparing(Function)" +
                        ":Comparator : Comparator.comparing(Flight::getFlightNumber)", flightList);
       

       
        flightList.sort(Comparator.comparing(Flight::getDate));
        DemoMetaDataHelper.printList("Sort by behavior parametrization using method ref Comparator.comparing(Function)" +
                ":Comparator : Comparator.comparing(Flight::getDate)", flightList);

    }


    private static List<Flight> initFlightsAndPrintTitle(String title) {
        DemoMetaDataHelper.addTitle(title);
        FlightDataDemoHelper flightDataDemoHelper = new FlightDataDemoHelper();
        List<Flight> flightList = flightDataDemoHelper.initFlights(10);
        DemoMetaDataHelper.printList("Unsorted list", flightList);
        return flightList;
    }

    /**
     * Behavior parametrization
     */
    static class FlightComparatorByFlightNumber implements Comparator<Flight> {
        @Override
        public int compare(Flight o1, Flight o2) {
            return o1.getFlightNumber().compareTo(o2.getFlightNumber());
        }
    }

    static class FlightComparatorByDate implements Comparator<Flight> {
        @Override
        public int compare(Flight o1, Flight o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }

}
