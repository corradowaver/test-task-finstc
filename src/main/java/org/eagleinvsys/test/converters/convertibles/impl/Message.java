package org.eagleinvsys.test.converters.convertibles.impl;

import org.eagleinvsys.test.converters.convertibles.ConvertibleMessage;

import java.util.Map;

public class Message implements ConvertibleMessage {

  private final Map<String, String> message;

  public Message(Map<String, String> message) {
    this.message = message;
  }

  @Override
  public String getElement(String elementId) {
    return this.message.get(elementId);
  }

}
