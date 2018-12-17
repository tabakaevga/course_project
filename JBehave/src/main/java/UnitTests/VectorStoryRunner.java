package UnitTests;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import com.github.valfirst.jbehave.junit.monitoring.JUnitReportingRunner;



@RunWith(JUnitReportingRunner.class)
public class VectorStoryRunner extends JUnitStories {

    private Configuration configuration;

    public VectorStoryRunner() {

        super();

        CrossReference crossReference = new CrossReference();

        configuration = new MostUsefulConfiguration();

        configuration.useStoryReporterBuilder(
                new StoryReporterBuilder().withFormats(Format.HTML, Format.STATS, Format.CONSOLE)
                .withCodeLocation(CodeLocations.codeLocationFromPath("target/."))
                .withCrossReference(crossReference));

        EmbedderControls embedderControls = configuredEmbedder().embedderControls();

        embedderControls.doBatch(false);
        embedderControls.doGenerateViewAfterStories(true);
        embedderControls.doSkip(false);
        embedderControls.doVerboseFailures(true);
        embedderControls.doVerboseFiltering(true);
        embedderControls.useThreads(1);
        embedderControls.useStoryTimeouts("1800");

    }
    @Override
    public Configuration configuration() {
        return configuration;
    }
    @Override
    protected List<String> storyPaths() {
        StoryFinder finder = new StoryFinder();
        return finder.findPaths(CodeLocations.codeLocationFromClass(this.getClass()).getFile(), Arrays.asList("**/*.story"), Arrays.asList(""));
    }
    
    @Override
    public InjectableStepsFactory stepsFactory() {

        final String stepsPackage = "UnitTests";
        final String stepsLoc = "src/main/java/" + stepsPackage;

        List<Object> stepList = new ArrayList<Object>();

        File steps = new File(stepsLoc);
        File[] fileList = steps.listFiles();

        int size = fileList.length;

        for (int i = 0; i < size; i++) {

            if (fileList[i].isFile()) {                                     // also returns folders (directories)
                String value = fileList[i].getName().replace(".java", "");  // strip extensions
                if (value.toLowerCase().contains("steps")) {          // ignore testrunner itself
                    try {
                        Object stepObject = Class.forName((stepsPackage + "." + value)).newInstance();
                        stepList.add(stepObject);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return new InstanceStepsFactory(configuration(), stepList);
    }

}
