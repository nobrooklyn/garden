package com.myorg;

import java.util.ArrayList;
import java.util.List;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Duration;
import software.amazon.awscdk.core.RemovalPolicy;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.BillingMode;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.s3.BlockPublicAccess;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.LifecycleRule;

public class ApCdkStack extends Stack {
    public ApCdkStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public ApCdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);
        bucket("ap-cdk-bucket", "ap-cdk-bucket-1");
        dynamodb("ap-cdk-dynamodb", "ap-cdk-dynamodb-1");
    }

    private void bucket(String id, String bucketName) {
        List<LifecycleRule> lifecycleRules = new ArrayList<>();
        lifecycleRules.add(LifecycleRule.builder().abortIncompleteMultipartUploadAfter(Duration.days(1)).build());

        Bucket.Builder.create(this, id).bucketName(bucketName).publicReadAccess(false).versioned(false)
                .lifecycleRules(lifecycleRules).blockPublicAccess(BlockPublicAccess.BLOCK_ALL)
                .removalPolicy(RemovalPolicy.DESTROY).build();
    }

    private void dynamodb(String id, String tableName) {
        String partitionKeyName = "fid";
        Attribute partitionKey = Attribute.builder().name(partitionKeyName).type(AttributeType.STRING).build();
        Table.Builder.create(this, id).billingMode(BillingMode.PAY_PER_REQUEST).removalPolicy(RemovalPolicy.DESTROY)
                .tableName(tableName).partitionKey(partitionKey).build();
    }
}
