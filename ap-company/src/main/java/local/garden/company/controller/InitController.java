package local.garden.company.controller;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
public class InitController {
    @Autowired
    private DynamoDB db;

    @PostMapping
    public void init() {
        createTable("Company", "id", "S");
    }

    private void createTable(String tableName, String partitionKeyName, String partitionKeyType) {
        List<KeySchemaElement> keySchema = new ArrayList<>();
        keySchema.add(new KeySchemaElement().withAttributeName(partitionKeyName).withKeyType(KeyType.HASH));

        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();
        attributeDefinitions
                .add(new AttributeDefinition().withAttributeName(partitionKeyName).withAttributeType(partitionKeyType));

        CreateTableRequest request = new CreateTableRequest().withTableName(tableName).withKeySchema(keySchema)
                .withProvisionedThroughput(
                        new ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L));

        request.setAttributeDefinitions(attributeDefinitions);

        System.out.println("Issuing CreateTable request for " + tableName);

        Table table = db.createTable(request);
        try {
            table.waitForActive();
        } catch (InterruptedException e) {
            System.err.println("Creata table request failed for " + tableName);
            e.printStackTrace();
        }
    }
}