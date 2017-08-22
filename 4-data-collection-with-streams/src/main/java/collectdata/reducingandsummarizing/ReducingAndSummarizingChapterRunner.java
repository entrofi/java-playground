package collectdata.reducingandsummarizing;


import net.entrofi.studies.java8.demohelpers.AbstractChapterRunner;

public class ReducingAndSummarizingChapterRunner extends AbstractChapterRunner {


    @Override
    protected void fillDemoMap() {
        demos.put("Finding Maximum and Minimum in a Stream", FindingMaxAndMinInaStream::new);
        demos.put("Summarization", Summarization::new);
        demos.put("Joining", JoiningStrings::new);
        demos.put("Summarization with reduction", SummarizationWithReduction::new);
    }

    @Override
    public void doBeforeRun() {
        super.doBeforeRun();
    }

    @Override
    public void doAfterRun() {
        super.doAfterRun();
    }
}
