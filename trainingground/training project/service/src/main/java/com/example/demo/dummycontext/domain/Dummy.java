package com.example.demo.dummycontext.domain;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Document("dummy-collection")
public class Dummy {

  @Id
  String id;
  String entityName;

  public boolean dummyDomainLogic(){
    return true;
  }

  public String areYouADummy(){
    return "No, you are amazing!";
  }
}
