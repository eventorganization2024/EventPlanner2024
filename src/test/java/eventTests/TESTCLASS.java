

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features",
         plugin = {"html:target/cucumber/wikipedia.html"},
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"eventTests"}
       
            )

public class TESTCLASS {
 
}

