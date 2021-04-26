import collectdata.customcollectors.CustomCollectorsChapterRunner;
import collectdata.grouping.GroupingChapterRunner;
import collectdata.partitioning.PartitioningChapterRunner;
import collectdata.reducingandsummarizing.ReducingAndSummarizingChapterRunner;
import net.entrofi.studies.java8.demohelpers.AbstractChapterRunner;


public class Main extends AbstractChapterRunner {

    public static void main(String[] args) {
        Main mainRunner = new Main();
        mainRunner.run();
    }

    @Override
    protected void fillDemoMap() {
        demos.put("Reducing and Summarizing", ReducingAndSummarizingChapterRunner::new);
        demos.put("Grouping", GroupingChapterRunner::new);
        demos.put("Partitioning", PartitioningChapterRunner::new);
        demos.put("Custom Collectors", CustomCollectorsChapterRunner::new);
    }

    @Override
    public void doBeforeRun() {
        String message = "These demos covers: \n"
                    + "1. Creating and using a collector with the Collectors \n"
                    + "2. Reducing streams of data to a single value \n"
                    + "3. Summarization as a special case of reduction "
                    + "4. Grouping and partitioning data Developing your own custom collectors";
        System.out.println(message);
    }
}
