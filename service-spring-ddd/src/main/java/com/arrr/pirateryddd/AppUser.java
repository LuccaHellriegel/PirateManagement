package com.arrr.pirateryddd;

import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Getter
public class AppUser {

  @Id
  private String id;
  private String name;
  private String username;
  private String password;
  private Collection<Role> roles = new ArrayList<>();
}
