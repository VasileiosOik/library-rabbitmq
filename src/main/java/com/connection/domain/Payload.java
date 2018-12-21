package com.connection.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload<T> {

    private T payloadItem;

    public Payload(T payloadItem) {
        this.payloadItem = payloadItem;
    }

    public Payload() {

    }

    public T getPayloadItem() {
        return payloadItem;
    }

    public void setPayloadItem(T payloadItem) {
        this.payloadItem = payloadItem;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
