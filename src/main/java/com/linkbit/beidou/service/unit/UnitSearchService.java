package com.linkbit.beidou.service.unit;

import com.linkbit.beidou.dao.outsourcingUnit.OutsourcingUnitRepository;
import com.linkbit.beidou.domain.units.Units;
import com.linkbit.beidou.service.app.BaseService;
import com.linkbit.beidou.utils.search.Searchable;
import com.linkbit.beidou.utils.search.SortedSearchable;
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
