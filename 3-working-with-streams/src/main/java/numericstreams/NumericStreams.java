/*
 * Licensed under GPL.
 */
package numericstreams;


import model.Phone;
import net.entrofi.studies.java8.demohelpers.DemoMetaDataHelper;
import net.entrofi.studies.java8.demohelpers.DemoRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO add javadoc
 */
public class NumericStreams implements DemoRunner {

    @Override
    public void run() {
        DemoMetaDataHelper.addTitle("Demos for primitive stream specializations");
    }


    public void mapingToNumericStream() {
        DemoMetaDataHelper.addTitle("To avoid boxing effort required to by Stream<Integer>, Stream<Double> ...."
                    + " \n we have primitive specializations:\n"
                    + " \t  List<Phone> inventory = generateSamples();\n"
                    + "        inventory.stream().mapToDouble(Phone::getPrice).average();");
        List<Phone> inventory = generateSamples();
        inventory.stream().mapToDouble(Phone::getPrice).average();
    }


    private List<Phone> generateSamples() {
        List<Phone> phones = new ArrayList<>();
        final String samsung = "samsung";
        phones.add(new Phone(250, samsung, "s4", 51.00));
        phones.add(new Phone(261, samsung, "s5", 60.00));
        phones.add(new Phone(180, samsung, "note 6", 150.00));
        phones.add(new Phone(270, samsung, "note 8", 251.00));

        final String lg = "lg";
        phones.add(new Phone(224, lg, "g3", 55));
        phones.add(new Phone(237, lg, "g4", 65));
        phones.add(new Phone(240, lg, "g5", 255));

        final String nexus = "nexus";
        phones.add(new Phone(240, nexus, "x5", 275));

        return phones;
    }
}
