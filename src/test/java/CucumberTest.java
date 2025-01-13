
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.runner.RunWith;

@CucumberOptions(
        features = "src/test/features/resources",
        glue = "org.example")
public class CucumberTest {
}
