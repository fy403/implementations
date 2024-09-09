package com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.converter;

public interface Converter<T, R> {

    R serialize(T t);

    T deserialize(R r);

}
