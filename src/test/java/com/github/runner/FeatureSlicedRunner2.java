package com.github.runner;

import org.junit.runner.RunWith;

import com.github.runner.runners.CucumberSerneityRunner2;

import cucumber.api.CucumberOptions;

@RunWith(CucumberSerneityRunner2.class)
@CucumberOptions(features = "classpath:features", glue = "com.github.runner")
public class FeatureSlicedRunner2 {
}
