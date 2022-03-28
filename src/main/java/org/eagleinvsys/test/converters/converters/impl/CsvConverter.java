package org.eagleinvsys.test.converters.converters.impl;

import org.eagleinvsys.test.converters.converters.Converter;
import org.eagleinvsys.test.converters.convertibles.ConvertibleCollection;
import org.eagleinvsys.test.converters.convertibles.ConvertibleMessage;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CsvConverter implements Converter {

  /**
   * Converts given {@link ConvertibleCollection} to CSV and outputs result as a text to the provided {@link OutputStream}
   *
   * @param collectionToConvert collection to convert to CSV format
   * @param outputStream        output stream to write CSV conversion result as text to
   */
  @Override
  public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) {
    byte[] output = makeCsv(collectionToConvert).getBytes(UTF_8);
    try {
      outputStream.write(output);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String makeCsv(ConvertibleCollection collectionToConvert) {
    Set<String> headers = (Set<String>) collectionToConvert.getHeaders();
    List<ConvertibleMessage> records = (List<ConvertibleMessage>) collectionToConvert.getRecords();

    StringBuilder builder = new StringBuilder();

    builder.append(String.join(";", headers));
    builder.append("\n");
    records.forEach(message -> {
          List<String> row = headers.stream().map(message::getElement).collect(Collectors.toList());
          builder.append(String.join(";", row));
          builder.append("\n");
        }
    );
    return builder.toString();
  }


}
