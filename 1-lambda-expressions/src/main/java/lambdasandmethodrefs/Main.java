package lambdasandmethodrefs;


import lambdasandmethodrefs.behaviorparameterization.BehaviorParameterizationAlternatives;
import lambdasandmethodrefs.composition.ComparatorComposition;
import lambdasandmethodrefs.composition.FunctionComposition;
import lambdasandmethodrefs.composition.PredicateComposition;
import net.entrofi.studies.java8.demohelpers.AbstractChapterRunner;

public class Main extends AbstractChapterRunner {

    public static void main(String[] args) {
        Main mainRunner = new Main();
        mainRunner.run();

    }

    @Override
    protected void fillDemoMap() {
        demos.put("Behavior Parameterization", BehaviorParameterizationAlternatives::new);
        demos.put("Composition - Comparator Composition", ComparatorComposition::new);
        demos.put("Composition - Predicate Composition", PredicateComposition::new);
        demos.put("Function Chaining and Composition", FunctionComposition::new);
    }


}
