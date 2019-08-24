package com.connection.processor;


import java.util.List;

@FunctionalInterface
public interface RequestProcessor<T> {

    List<T> processRequest(T message);

}
