package org.eagleinvsys.test.converters.convertibles.impl;

import org.eagleinvsys.test.converters.convertibles.ConvertibleCollection;
import org.eagleinvsys.test.converters.convertibles.ConvertibleMessage;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class MessageCollection implements ConvertibleCollection {

  private final List<ConvertibleMessage> collection;
  private final LinkedHashSet<String> headers;

  public MessageCollection(List<ConvertibleMessage> collection) {
    this.collection = collection;
    headers = collectHeaders(collection);
  }

  private LinkedHashSet<String> collectHeaders(List<ConvertibleMessage> collection) {
    return collection.stream()
        .flatMap(convertibleMessage ->
            ((Message) convertibleMessage).keySet().stream()
        )
        .sorted()
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  @Override
  public Collection<String> getHeaders() {
    return headers;
  }

  @Override
  public Iterable<ConvertibleMessage> getRecords() {
    return collection;
  }

}
