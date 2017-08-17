package net.entrofi.studies.java8.demohelpers;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class AbstractChapterRunner implements ChapterRunner {

    protected final Map<String, Supplier> demos = new LinkedHashMap<>();


    protected abstract void fillDemoMap();


    public void doBeforeRun(){

    }

    public void doAfterRun(){

    }

    @Override
    public void run() {
        fillDemoMap();
        this.init();
    }


    @Override
    public final Map<String, Supplier> getDemoMap() {
        Map<String, Supplier> demoMap = new LinkedHashMap<>();
        demoMap.putAll(demos);
        return demoMap;
    }
}
