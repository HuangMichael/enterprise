package com.novsky.service.equipments;

import com.novsky.dao.equipments.VEqUpdateBillRepository;
import com.novsky.domain.equipments.VEqUpdateBill;
import com.novsky.service.app.BaseService;
import com.novsky.utils.search.Searchable;
import com.novsky.service.app.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备更新查询业务类
 */
@Service
public class EqUpdateBillSearchService extends BaseService implements Searchable {

    @Autowired
    VEqUpdateBillRepository vEqUpdateBillRepository;
    /**
     * @param searchPhrase
     * @param paramsSize
     * @return
     */
    public List<VEqUpdateBill> findByConditions(String searchPhrase, int paramsSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramsSize);
        return vEqUpdateBillRepository.findByApplyDateBetweenAndEqNameContainsAndEqClassContainsAndLocNameContains(array[0], array[1],  array[2], array[3], array[4]);
    }

    /**
     * @param searchPhrase
     * @param paramsSize
     * @return
     */
    public Page<VEqUpdateBill> findByConditions(String searchPhrase, int paramsSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramsSize);
        return vEqUpdateBillRepository.findByApplyDateBetweenAndEqNameContainsAndEqClassContainsAndLocNameContains(array[0], array[1], array[2], array[3], array[4], pageable);
    }


}
