package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.converters.Converter;
import org.eagleinvsys.test.converters.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.convertibles.ConvertibleMessage;
import org.eagleinvsys.test.converters.convertibles.impl.Message;
import org.eagleinvsys.test.converters.convertibles.impl.MessageCollection;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static org.eagleinvsys.test.converters.convertibles.impl.MessageFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvConverterTests {

  private final Converter converter = new CsvConverter();

  private final ConvertibleMessage valid1 = new Message(
      createValidMessage1()
  );
  private final ConvertibleMessage valid2 = new Message(
      createValidMessage2()
  );
  private final ConvertibleMessage incomplete1 = new Message(
      createIncompleteMessage1()
  );
  private final ConvertibleMessage incomplete2 = new Message(
      createIncompleteMessage2()
  );
  private final ConvertibleMessage empty1 = new Message(
      createEmptyMessage()
  );


  @Test
  public void converterMustConvertWithValidMessages() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    MessageCollection validCollection = new MessageCollection(List.of(valid1, valid2));
    String expected = String.format("""
            email;name;surname
            %s;%s;%s
            %s;%s;%s
            """,
        valid1.getElement("email"), valid1.getElement("name"), valid1.getElement("surname"),
        valid2.getElement("email"), valid2.getElement("name"), valid2.getElement("surname"));
    converter.convert(validCollection, outputStream);
    assertEquals(expected, outputStream.toString());
  }

  @Test
  public void converterMustContainAllHeadersWithIncompleteMessages() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    MessageCollection incompleteCollection = new MessageCollection(List.of(valid1, incomplete1, incomplete2));
    String expected = String.format("""
            age;email;name;surname
            %s;%s;%s;%s
            %s;%s;%s;%s
            %s;%s;%s;%s
            """,
        valid1.getElement("age"), valid1.getElement("email"), valid1.getElement("name"), valid1.getElement("surname"),
        incomplete1.getElement("age"), incomplete1.getElement("email"), incomplete1.getElement("name"), incomplete1.getElement("surname"),
        incomplete2.getElement("age"), incomplete2.getElement("email"), incomplete2.getElement("name"), incomplete2.getElement("surname")
    );

    converter.convert(incompleteCollection, outputStream);
    assertEquals(expected, outputStream.toString());
  }

  @Test
  public void converterMustConvertWithEmptyMessages() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    MessageCollection emptyCollection = new MessageCollection(List.of(valid1, empty1));
    String expected = String.format("""
            email;name;surname
            %s;%s;%s
            %s;%s;%s
            """,
        valid1.getElement("email"), valid1.getElement("name"), valid1.getElement("surname"),
        empty1.getElement("email"), empty1.getElement("name"), empty1.getElement("surname"));
    converter.convert(emptyCollection, outputStream);
    assertEquals(expected, outputStream.toString());
  }
}
