package ro.msg.learning.shop.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CsvUtil {

    public static <T> void writeToCsv(T type, Class<T> myClass, List<T> inputPojoList, OutputStream outputStream) throws FileNotFoundException, UnsupportedEncodingException, IOException {

        Class typeClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(typeClass);
        schema = schema.withColumnSeparator(',');
        ObjectWriter objectWriter = mapper.writer(schema);
        objectWriter.writeValue(outputStream, inputPojoList);
    }

    public static <T> List<T> readFromCsv(T type, Class<T> myClass, InputStream inputStream) throws FileNotFoundException, UnsupportedEncodingException, IOException {

        Class typeClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(typeClass);
        schema = schema.withColumnSeparator(',');

        ObjectReader objectReader = mapper.reader(typeClass).with(schema);

        Reader reader = new InputStreamReader(inputStream);
        MappingIterator<T> mappingIterator = objectReader.readValues(reader);

        return mappingIterator.readAll();
    }

}
