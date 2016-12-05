package com.novsky.service.workOrder;

import com.novsky.dao.workOrder.VworkOrderFixBillRepository;
import com.novsky.domain.workOrder.VworkOrderFixBill;
import com.novsky.service.app.BaseService;
import com.novsky.utils.search.SortedSearchable;
import com.novsky.dao.workOrder.VworkOrderFixBillRepository;
import com.novsky.domain.workOrder.VworkOrderFixBill;
import com.novsky.service.app.BaseService;
import com.novsky.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangbin  on 2016/5/20.
 */
@Service
public class WorkOrderFixSearchService extends BaseService implements SortedSearchable {
    @Autowired
    VworkOrderFixBillRepository vworkOrderFixBillRepository;

    /**
     * @param searchPhrase 查询关键字
     * @param pageable     可分页
     * @return 根据条件查询
     */
    public Page<VworkOrderFixBill> findByConditions(String searchPhrase, int paramsSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramsSize);
        return vworkOrderFixBillRepository.findByOrderLineNoContainsAndOrderDescContainsAndLocationContainsAndEqClassContains(array[0], array[1], array[2], array[3], pageable);
    }

    /**
     * @param searchPhrase 查询关键字
     * @return 根据条件查询
     */
    public List<VworkOrderFixBill> findByConditions(String searchPhrase, int paramsSize) {

        String array[] = super.assembleSearchArray(searchPhrase, paramsSize);
        return vworkOrderFixBillRepository.findByOrderLineNoContainsAndOrderDescContainsAndLocationContainsAndEqClassContains(array[0], array[1], array[2], array[3]);

    }

}
