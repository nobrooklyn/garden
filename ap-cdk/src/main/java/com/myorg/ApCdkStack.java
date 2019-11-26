package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.s3.Bucket;

public class ApCdkStack extends Stack {
    public ApCdkStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public ApCdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Bucket.Builder.create(this, "ap-cdk-bucket").bucketName("ap-cdk-bucket-1").publicReadAccess(false)
                .versioned(true).build();
    }
}
