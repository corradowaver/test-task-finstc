package org.eagleinvsys.test.converters.convertibles.impl;

import org.eagleinvsys.test.converters.convertibles.ConvertibleMessage;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageCollectionTest {

  @Test
  public void testHeadersCollecting() {
    ConvertibleMessage message1 = new Message(
        Map.of("name", "Ivan",
            "surname", "Ivanov",
            "email", "ii@gmail.com")
    );

    ConvertibleMessage message2 = new Message(
        Map.of("name", "John",
            "surname", "Doe",
            "email", "jd@gmail.com")
    );

    ConvertibleMessage message3 = new Message(
        Map.of("name", "Missing",
            "surname", "Email")
    );

    ConvertibleMessage message4 = new Message(
        Map.of()
    );
    MessageCollection collection = new MessageCollection(List.of(message1, message2, message3, message4));

    assertEquals(3, collection.getHeaders().size());
  }

}
