package com.linkbit.beidou.service.equipments;

import com.linkbit.beidou.dao.equipments.VEqAddBillRepository;
import com.linkbit.beidou.dao.equipments.VEqUpdateBillRepository;
import com.linkbit.beidou.domain.equipments.VEqAddBill;
import com.linkbit.beidou.domain.equipments.VEqUpdateBill;
import com.linkbit.beidou.service.app.BaseService;
import com.linkbit.beidou.utils.search.Searchable;
import com.linkbit.beidou.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备新置查询业务类
 */
@Service
public class EqAddBillSearchService extends BaseService implements SortedSearchable {

    @Autowired
    VEqAddBillRepository vEqAddBillRepository;

    /**
     * @param searchPhrase
     * @param paramsSize
     * @return
     */
    public List<VEqAddBill> findByConditions(String searchPhrase, int paramsSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramsSize);
        return vEqAddBillRepository.findByApplyDateBetweenAndEqNameContainsAndEqClassContainsAndLocNameContains(array[0], array[1], array[2], array[3], array[4]);
    }

    /**
     * @param searchPhrase
     * @param paramsSize
     * @return
     */
    public Page<VEqAddBill> findByConditions(String searchPhrase, int paramsSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramsSize);
        return vEqAddBillRepository.findByApplyDateBetweenAndEqNameContainsAndEqClassContainsAndLocNameContains(array[0], array[1], array[2], array[3], array[4], pageable);
    }


}
