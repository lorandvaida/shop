package ro.msg.learning.shop.utils;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Component
public class CsvConverter<T> extends AbstractGenericHttpMessageConverter<List<T>> {

    @Override
    protected List<T> readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {

        return CsvUtil.readFromCsv(null,aClass,httpInputMessage.getBody());
    }

    @Override
    public List<T> read(Type type, Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {

        return CsvUtil.readFromCsv(type,aClass,httpInputMessage.getBody());
    }

    @Override
    protected void writeInternal(List<T> ts, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

        CsvUtil.writeToCsv(null, null, ts, httpOutputMessage.getBody());
    }
}
