package org.eagleinvsys.test.converters.converters.impl;

import org.eagleinvsys.test.converters.converters.StandardConverter;
import org.eagleinvsys.test.converters.convertibles.ConvertibleCollection;
import org.eagleinvsys.test.converters.convertibles.impl.Message;
import org.eagleinvsys.test.converters.convertibles.impl.MessageCollection;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StandardCsvConverter implements StandardConverter {

  private final CsvConverter csvConverter;

  public StandardCsvConverter(CsvConverter csvConverter) {
    this.csvConverter = csvConverter;
  }

  /**
   * Converts given {@link List<Map>} to CSV and outputs result as a text to the provided {@link OutputStream}
   *
   * @param collectionToConvert collection to convert to CSV format. All maps must have the same set of keys
   * @param outputStream        output stream to write CSV conversion result as text to
   */
  @Override
  public void convert(List<Map<String, String>> collectionToConvert, OutputStream outputStream) {
    ConvertibleCollection collection = toConvertibleCollection(collectionToConvert);
    csvConverter.convert(collection, outputStream);
  }

  public ConvertibleCollection toConvertibleCollection(List<Map<String, String>> collection) {
    if (collection.isEmpty()) {
      throw new IllegalArgumentException("Collection to convert cannot be empty");
    }
    if (notSameSetOfKeys(collection)) {
      throw new IllegalArgumentException("Maps key sets are different");
    }
    return new MessageCollection(
        collection.stream()
            .map(Message::new)
            .collect(Collectors.toList())
    );
  }

  private boolean notSameSetOfKeys(List<Map<String, String>> collection) {
    return !collection.stream()
        .allMatch(it ->
            it.keySet().equals(collection.get(0).keySet())
        );
  }

}
