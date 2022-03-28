package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.converters.impl.StandardCsvConverter;
import org.eagleinvsys.test.converters.convertibles.ConvertibleCollection;
import org.eagleinvsys.test.converters.convertibles.impl.Message;
import org.eagleinvsys.test.converters.convertibles.impl.MessageCollection;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.eagleinvsys.test.converters.convertibles.impl.MessageFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class StandardCsvConverterTests {

  private final CsvConverter converter = mock(CsvConverter.class);
  private final StandardCsvConverter standardConverter = new StandardCsvConverter(converter);

  private final Map<String, String> valid1 = createValidMessage1();
  private final Map<String, String> valid2 = createValidMessage2();
  private final Map<String, String> incomplete1 = createIncompleteMessage1();
  private final Map<String, String> incomplete2 = createIncompleteMessage2();

  @Test
  void converterMustConvertRawListToConvertibleCollectionWithValidMessages() {
    List<Map<String, String>> collection = List.of(valid1, valid2);
    ConvertibleCollection expected = new MessageCollection(List.of(new Message(valid1), new Message(valid2)));
    ConvertibleCollection converted = standardConverter.toConvertibleCollection(collection);
    assertEquals(expected.getRecords(), converted.getRecords());
  }

  @Test
  void converterMustFailIfMapsContainsDifferentKeySets() {
    List<Map<String, String>> collection = List.of(incomplete1, incomplete2);
    standardConverter.toConvertibleCollection(collection);
    assertThrows(IllegalArgumentException.class, () -> {
    });
  }

}
