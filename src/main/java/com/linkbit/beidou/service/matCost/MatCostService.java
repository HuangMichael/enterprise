package com.linkbit.beidou.service.matCost;

import com.linkbit.beidou.dao.app.resource.ResourceRepository;
import com.linkbit.beidou.dao.macCost.MatCostRepository;
import com.linkbit.beidou.domain.matCost.MatCost;
import com.linkbit.beidou.service.app.BaseService;
import com.linkbit.beidou.utils.export.tool.Exportable;
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
public class MatCostService extends BaseService {

    @Autowired
    public ResourceRepository resourceRepository;

    @Autowired
    public MatCostRepository matCostRepository;

    /**
     * @return 返回有物资消耗单据的所有的位置
     */
    public List<String> findMyLocs() {
        return matCostRepository.findMyLocs();
    }


}
