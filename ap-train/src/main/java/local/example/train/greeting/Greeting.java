package local.example.train.greeting;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Greeting {
    @Id
    private String id;
    private String message;
}