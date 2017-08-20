/*
 * Licensed under GPL.
 */


import filteringandslicing.Filtering;
import filteringandslicing.Mapping;
import findingandmatching.FindingAndMatching;
import net.entrofi.studies.java8.demohelpers.AbstractChapterRunner;
import reducing.BasicReducing;

public class Main extends AbstractChapterRunner {

    public static void main(String[] args) {
        Main mainRunner = new Main();
        mainRunner.run();

    }

    @Override
    protected void fillDemoMap() {
        demos.put("Filtering and Slicing Samples", Filtering::new);
        demos.put("Mapping Samples", Mapping::new);
        demos.put("Finding and matching", FindingAndMatching::new);
        demos.put("Basic reducing (folding) samples", BasicReducing::new);
    }

}
