package collectdata.grouping;

import net.entrofi.studies.java8.demohelpers.AbstractChapterRunner;

/**
 * Chapter runner for collecting and grouping data from streams. <br>
 * <ul>
 *     <li>Basic Grouping: The most basic demos for grouping </li>
 *     <li>Multilevel Grouping: Examples of multilevel grouping</li>
 * </ul>
 * @author Hasan COMAK
 * @see BasicGrouping
 * @see MultilevelGrouping
 */
public class GroupingChapterRunner extends AbstractChapterRunner {

    @Override
    protected void fillDemoMap() {
        demos.put("Basic Grouping", BasicGrouping::new);
        demos.put("Multilevel Grouping", MultilevelGrouping::new);
    }


}
