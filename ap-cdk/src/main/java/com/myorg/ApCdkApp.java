package com.myorg;

import software.amazon.awscdk.core.App;

public class ApCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        new ApCdkStack(app, "ap-cdk");

        app.synth();
    }
}
