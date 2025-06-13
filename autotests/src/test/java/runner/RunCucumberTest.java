package runner;

import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import static io.cucumber.core.options.Constants.*;

@Suite
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "glueSteps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@qa-cucumber")
public class RunCucumberTest {}
