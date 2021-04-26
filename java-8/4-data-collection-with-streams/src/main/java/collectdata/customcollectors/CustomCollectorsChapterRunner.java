package collectdata.customcollectors;

import net.entrofi.studies.java8.demohelpers.AbstractChapterRunner;

/**
 * @author Hasan COMAK
 */
public class CustomCollectorsChapterRunner extends AbstractChapterRunner {

    @Override
    protected void fillDemoMap() {
        demos.put("Basic Collection with Java's collectors", PrimeNumberCollection::new);
        demos.put("Collection with a custom collector", PrimeNumberCustomCollection::new);
    }
}
