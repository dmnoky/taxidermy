package com.dmnoky.taxidermy.service.animal.subcategory;

import com.dmnoky.taxidermy.model.animal.subcategory.Type;

import java.util.List;

public interface TypeService {
    void addType(Type type);
    void updateType(Type type);
    boolean removeType(Long id);
    Type getType(Long id);
    List<Type> getTypes();
}
