package local.garden.rsocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GreetingData {
    private String word;

    public static GreetingData fromException(Exception e) {
        return new GreetingData(e.getMessage());
    }
}