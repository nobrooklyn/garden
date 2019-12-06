package local.garden.fluxs3;

import java.util.HashMap;
import java.util.Map;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;

public class FileInfoDynamodbMapper {
    public Map<String, AttributeValue> to(FileInfo from) {
        Map<String, AttributeValue> o = new HashMap<>();
        o.put("fid", AttributeValue.builder().s(from.getFid()).build());
        o.put("fname", AttributeValue.builder().s(from.getFname()).build());
        return o;
    }

    public FileInfo to(GetItemResponse from) {
        return to(from.item());
    }

    public FileInfo to(Map<String, AttributeValue> from) {
        return new FileInfo(from.get("fid").s(), from.get("fname").s());
    }
}