package org.eagleinvsys.test.converters.convertibles.impl;

import org.eagleinvsys.test.converters.convertibles.ConvertibleCollection;
import org.eagleinvsys.test.converters.convertibles.ConvertibleMessage;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageCollection implements ConvertibleCollection {

  private final Map<String, List<ConvertibleMessage>> collection;

  public MessageCollection(Map<String, List<ConvertibleMessage>> collection) {
    this.collection = collection;
  }

  @Override
  public Collection<String> getHeaders() {
    return collection.keySet();
  }

  @Override
  public Iterable<ConvertibleMessage> getRecords() {
    return collection.values().stream().flatMap(List::stream).collect(Collectors.toList());
  }

}
