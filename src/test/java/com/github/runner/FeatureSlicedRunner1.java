package com.github.runner;

import org.junit.runner.RunWith;

import com.github.runner.runners.CucumberSerneityRunner1;

import cucumber.api.CucumberOptions;

@RunWith(CucumberSerneityRunner1.class)
@CucumberOptions(features = "classpath:features", glue = "com.github.runner")
public class FeatureSlicedRunner1 {
}
