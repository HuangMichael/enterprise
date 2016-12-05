package com.novsky.service.budge;

import com.novsky.dao.budget.VecbudgetBillRepository;
import com.novsky.domain.EcBudget.VEcBudgetBill;
import com.novsky.service.app.BaseService;
import com.novsky.utils.DateUtils;
import com.novsky.utils.search.Searchable;
import com.novsky.utils.search.SortedSearchable;
import com.novsky.service.app.BaseService;
import com.novsky.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by huangbin on 2016/11/21.
 * 易耗品查询
 */
@Service
public class EcBudgeSearchService extends BaseService implements SortedSearchable {

    @Autowired
    VecbudgetBillRepository vecbudgetBillRepository;

    /**
     * @param searchPhrase
     * @param paramsSize
     * @return
     */
    public List<VEcBudgetBill> findByConditions(String searchPhrase, int paramsSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramsSize);
        return vecbudgetBillRepository.findByApplyDateBetweenAndEcnameContainsAndLocationContains(array[0], array[1], array[2], array[3]);
    }

    /**
     * @param searchPhrase
     * @param paramsSize
     * @return
     */
    public Page<VEcBudgetBill> findByConditions(String searchPhrase, int paramsSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramsSize);
        return vecbudgetBillRepository.findByApplyDateBetweenAndEcnameContainsAndLocationContains(array[0], array[1], array[2], array[3], pageable);
    }

}
