package collectdata.partitioning;

import net.entrofi.studies.java8.demohelpers.AbstractChapterRunner;

/**
 *
 * @author Hasan COMAK
 */
public class PartitioningChapterRunner extends AbstractChapterRunner {

    @Override
    protected void fillDemoMap() {
        demos.put("Basic Partitioning", BasicPartitioning::new);
    }
}
