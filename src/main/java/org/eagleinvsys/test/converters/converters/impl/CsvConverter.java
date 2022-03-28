package org.eagleinvsys.test.converters.converters.impl;

import org.eagleinvsys.test.converters.converters.Converter;
import org.eagleinvsys.test.converters.convertibles.ConvertibleCollection;

import java.io.OutputStream;

public class CsvConverter implements Converter {

    /**
     * Converts given {@link ConvertibleCollection} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) {
        // TODO: implement
    }

}
