package com.novsky.service.workOrderMatCost;

import com.novsky.dao.macCost.WorkOrderMatCostRepository;
import com.novsky.service.app.BaseService;
import com.novsky.utils.search.Searchable;
import com.novsky.utils.search.SortedSearchable;
import com.novsky.dao.macCost.WorkOrderMatCostRepository;
import com.novsky.service.app.BaseService;
import com.novsky.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangbin
 * 2016年9月29日10:03:31
 * <p/>
 * 物料消耗查询业务类
 */
@Service
public class WorkOrderMatCostSearchService extends BaseService implements SortedSearchable {


    @Autowired
    public WorkOrderMatCostRepository workOrderMatCostRepository;


    /**
     * @param searchPhrase
     * @param paramSize
     * @param pageable
     * @return 多条件查询
     */
    @Override
    public Page findByConditions(String searchPhrase, int paramSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return workOrderMatCostRepository.findByOrderLineNoContainsAndMatNameContainsAndMatModelContains(array[0], array[1], array[2], pageable);
    }

    /**
     * @param searchPhrase
     * @param paramSize
     * @return 多条件查询
     */
    @Override
    public List findByConditions(String searchPhrase, int paramSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return workOrderMatCostRepository.findByOrderLineNoContainsAndMatNameContainsAndMatModelContains(array[0], array[1], array[2]);
    }
}
