package com.dmnoky.taxidermy.service.animal.subcategory;

import com.dmnoky.taxidermy.dao.animal.subcategory.SubsidiaryDao;
import com.dmnoky.taxidermy.model.animal.subcategory.Subsidiary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubsidiaryServiceImpl implements SubsidiaryService {
    private SubsidiaryDao subsidiaryDao;

    public void setSubsidiaryDao(SubsidiaryDao subsidiaryDao) {
        this.subsidiaryDao = subsidiaryDao;
    }

    @Override
    @Transactional
    public void addSubsidiary(Subsidiary subsidiary) {
        this.subsidiaryDao.addSubsidiary(subsidiary);
    }

    @Override
    @Transactional
    public void updateSubsidiary(Subsidiary subsidiary) {
        this.subsidiaryDao.updateSubsidiary(subsidiary);
    }

    @Override
    @Transactional
    public boolean removeSubsidiary(Long id) {
        return this.subsidiaryDao.removeSubsidiary(id);
    }

    @Override
    @Transactional(readOnly=true)
    public Subsidiary getSubsidiary(Long id) {
        return this.subsidiaryDao.getSubsidiary(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Subsidiary> getSubsidiaries() {
        return this.subsidiaryDao.getSubsidiaries();
    }
}
