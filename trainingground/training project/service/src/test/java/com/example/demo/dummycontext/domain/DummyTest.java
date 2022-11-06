package com.example.demo.dummycontext.domain;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DummyTest {

  @Test
  void domain_logic_returns_true_because_of_reasons() {
    // GIVEN
    var dummy = new Dummy("id", "name");
    // WHEN
    var result = dummy.dummyDomainLogic();
    // THEN
    assertTrue(result);
  }

  @Test
  void you_are_not_a_dummy() {
    // GIVEN
    var dummy = new Dummy("id", "name");
    // WHEN
    var result = dummy.areYouADummy();
    // THEN
    assertNotEquals("You are a dummy.", result);
  }
}