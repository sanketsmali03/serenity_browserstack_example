package com.github.runner;

import org.junit.runner.RunWith;

import com.github.runner.runners.CucumberSerneityRunner2;
import com.github.runner.runners.CucumberSerneityRunner3;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberSerneityRunner3.class)
@CucumberOptions(features = "classpath:features", glue = "com.github.runner")
public class FeatureSlicedRunner3 {
}
