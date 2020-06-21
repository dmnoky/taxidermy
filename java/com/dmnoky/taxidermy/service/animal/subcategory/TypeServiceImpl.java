package com.dmnoky.taxidermy.service.animal.subcategory;

import com.dmnoky.taxidermy.dao.animal.subcategory.TypeDao;
import com.dmnoky.taxidermy.model.animal.subcategory.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    private TypeDao typeDao;

    public void setTypeDao(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    @Override
    @Transactional
    public void addType(Type type) {
        this.typeDao.addType(type);
    }

    @Override
    @Transactional
    public void updateType(Type type) {
        this.typeDao.updateType(type);
    }

    @Override
    @Transactional
    public boolean removeType(Long id) {
        return this.typeDao.removeType(id);
    }

    @Override
    @Transactional(readOnly=true)
    public Type getType(Long id) {
        return this.typeDao.getType(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Type> getTypes() {
        return this.typeDao.getTypes();
    }
}
