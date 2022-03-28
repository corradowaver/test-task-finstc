package org.eagleinvsys.test.converters.convertibles.impl;

import java.util.Map;

public class MessageFactory {

  public static Map<String, String> createValidMessage1() {
    return Map.of("name", "Ivan",
        "surname", "Ivanov",
        "email", "ii@gmail.com");
  }

  public static Map<String, String> createValidMessage2() {
    return Map.of("name", "John",
        "surname", "Doe",
        "email", "jd@gmail.com");
  }

  public static Map<String, String> createIncompleteMessage1() {
    return Map.of("name", "hello",
        "surname", "world");
  }

  public static Map<String, String> createIncompleteMessage2() {
    return Map.of("name", "unknown",
        "age", "21");
  }

  public static Map<String, String> createEmptyMessage() {
    return Map.of();
  }
}
