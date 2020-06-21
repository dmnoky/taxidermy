package com.dmnoky.taxidermy.dao.animal.subcategory;

import com.dmnoky.taxidermy.model.animal.subcategory.Subsidiary;

import java.util.List;

public interface SubsidiaryDao {
    void addSubsidiary(Subsidiary subsidiary);
    void updateSubsidiary(Subsidiary subsidiary);
    boolean removeSubsidiary(Long id);
    Subsidiary getSubsidiary(Long id);
    List<Subsidiary> getSubsidiaries();
}
