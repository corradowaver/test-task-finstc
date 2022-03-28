package org.eagleinvsys.test.converters.convertibles.impl;

import org.eagleinvsys.test.converters.convertibles.ConvertibleMessage;

import java.util.Map;
import java.util.Set;

public class Message implements ConvertibleMessage {

  private final Map<String, String> message;

  public Message(Map<String, String> message) {
    this.message = message;
  }

  public Set<String> keySet() {
    return message.keySet();
  }

  @Override
  public String getElement(String elementId) {
    return this.message.getOrDefault(elementId, "");
  }

}
