package com.myorg;

import java.util.ArrayList;
import java.util.List;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Duration;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.LifecycleRule;

public class ApCdkStack extends Stack {
    public ApCdkStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public ApCdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        List<LifecycleRule> lifecycleRules = new ArrayList<>();
        lifecycleRules.add(LifecycleRule.builder().abortIncompleteMultipartUploadAfter(Duration.days(1)).build());
        Bucket.Builder.create(this, "ap-cdk-bucket").bucketName("ap-cdk-bucket-1").publicReadAccess(false)
                .versioned(true).lifecycleRules(lifecycleRules).build();
    }
}
