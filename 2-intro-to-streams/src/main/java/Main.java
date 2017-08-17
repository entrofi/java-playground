/*
 * Licensed under GPL.
 */


import intermediateandterminalops.IntermediateAndTerminalOpsDemo;
import net.entrofi.studies.java8.demohelpers.AbstractChapterRunner;

import java.util.Scanner;

public class Main extends AbstractChapterRunner {


    public static void main(String[] args) {
        Main mainRunner = new Main();
        mainRunner.run();

    }


    @Override
    protected void fillDemoMap() {
        demos.put("Intermediate and termminal operations demo", IntermediateAndTerminalOpsDemo::new);
    }

}
