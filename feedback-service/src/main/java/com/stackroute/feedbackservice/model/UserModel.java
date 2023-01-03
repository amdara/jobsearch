package com.stackroute.feedbackservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "user")
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    private UserKey userKey;
    private String email;

}
