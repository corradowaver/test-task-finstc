package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.convertibles.ConvertibleMessage;
import org.eagleinvsys.test.converters.convertibles.impl.Message;
import org.eagleinvsys.test.converters.convertibles.impl.MessageCollection;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvConverterTests {

  private final CsvConverter converter = new CsvConverter();
  private final ConvertibleMessage valid1 = new Message(
      Map.of("name", "Ivan",
          "surname", "Ivanov",
          "email", "ii@gmail.com")
  );

  private final ConvertibleMessage valid2 = new Message(
      Map.of("name", "John",
          "surname", "Doe",
          "email", "jd@gmail.com")
  );

  private final ConvertibleMessage incomplete1 = new Message(
      Map.of("name", "hello",
          "surname", "world")
  );

  private final ConvertibleMessage incomplete2 = new Message(
      Map.of("name", "unknown",
          "age", "21")
  );

  private final ConvertibleMessage empty1 = new Message(
      Map.of()
  );

  MessageCollection validCollection = new MessageCollection(List.of(valid1, valid2));
  MessageCollection incompleteCollection = new MessageCollection(List.of(valid1, incomplete1, incomplete2));
  MessageCollection emptyCollection = new MessageCollection(List.of(valid1, valid2, empty1));

  @Test
  public void converterMustConvertWithValidMessages() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    String expected = """
        email;name;surname
        ii@gmail.com;Ivan;Ivanov
        jd@gmail.com;John;Doe
        """;
    converter.convert(validCollection, outputStream);
    assertEquals(expected, outputStream.toString());
  }

  @Test
  public void converterMustContainAllHeadersWithIncompleteMessages() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    String expected = """
        age;email;name;surname
        ;ii@gmail.com;Ivan;Ivanov
        ;;hello;world
        21;;unknown;
        """;
    converter.convert(incompleteCollection, outputStream);
    assertEquals(expected, outputStream.toString());
  }

  @Test
  public void converterMustConvertWithEmptyMessages() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    String expected = """
        email;name;surname
        ii@gmail.com;Ivan;Ivanov
        jd@gmail.com;John;Doe
        ;;
        """;
    converter.convert(emptyCollection, outputStream);
    assertEquals(expected, outputStream.toString());
  }
}
