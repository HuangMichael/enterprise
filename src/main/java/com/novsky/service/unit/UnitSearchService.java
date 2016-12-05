package com.novsky.service.unit;

import com.novsky.dao.outsourcingUnit.OutsourcingUnitRepository;
import com.novsky.domain.units.Units;
import com.novsky.service.app.BaseService;
import com.novsky.utils.search.Searchable;
import com.novsky.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 外委单位业务类
 */
@Service
public class UnitSearchService extends BaseService implements SortedSearchable {

    @Autowired
    OutsourcingUnitRepository unitsRepository;

    /**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     */
    public List<Units> findByConditions(String searchPhrase, int paramSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return unitsRepository.findByDescriptionContainsAndLinkmanContains(array[0], array[1]);
    }


    /**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     */
    public Page<Units> findByConditions(String searchPhrase, int paramSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return unitsRepository.findByDescriptionContainsAndLinkmanContains(array[0], array[1], pageable);
    }


}
