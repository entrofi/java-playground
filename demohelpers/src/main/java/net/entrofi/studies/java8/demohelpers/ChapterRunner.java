package net.entrofi.studies.java8.demohelpers;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public interface ChapterRunner extends Runnable{

    default void run() {
        init();
    }

    default Map<String, Supplier> getDemoMap(){
        return new LinkedHashMap<>();
    }

    default void init() {
        System.out.println("Please choose a demo index to runExample from the list below:");
        printExampleList();
        System.out.println("Enter a number n <= 0 to exit");
        runExample();
    }

    default void printExampleList() {
        int i = 1;
        for(Map.Entry<String, Supplier> entry: getDemoMap().entrySet()) {
            System.out.println(i + ". " + entry.getKey());
            i++;
        }
        System.out.println();
    }

    default void runExample() {
        Scanner in = new Scanner(System.in);
        int sampleIndex = scanIndex(in);
        if(sampleIndex >= 0 && getDemoMap() != null && getDemoMap().size() > 0) {
            runAt(sampleIndex);
            sampleIndex = scanIndex(in);
            while(sampleIndex >= 0) {
                runAt(sampleIndex);
                sampleIndex = scanIndex(in);
            }
        }
        System.out.println("Good bye");
        in.close();
    }

   default void runAt(int sampleIndex) {
        String key = (String) getDemoMap().keySet().toArray()[sampleIndex];
        if(getDemoMap().get(key).get() instanceof DemoRunner) {
            DemoRunner demoRunner = (DemoRunner) getDemoMap().get(key).get();
            demoRunner.run();
        } else if(getDemoMap().get(key).get() instanceof ChapterRunner) {
            ChapterRunner chapterRunner = (ChapterRunner) getDemoMap().get(key).get();
            Thread chapterThread = new Thread(chapterRunner);
            chapterThread.start();
            try {
                chapterThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


   static int scanIndex(Scanner in) {
       System.out.print("\nYour choice: ");
       return in.hasNext()? in.nextInt() - 1 : -1;
    }
}
