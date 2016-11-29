package com.linkbit.beidou.service.line;

import com.linkbit.beidou.dao.line.LineRepository;
import com.linkbit.beidou.domain.line.Line;
import com.linkbit.beidou.domain.units.Units;
import com.linkbit.beidou.service.app.BaseService;
import com.linkbit.beidou.utils.search.Searchable;
import com.linkbit.beidou.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by huangbin on 2016/3/24.
 * 线路查询业务类
 */
@Service
public class LineSearchService extends BaseService implements SortedSearchable {

    @Autowired
    LineRepository lineRepository;

    /**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     */
    public List<Line> findByConditions(String searchPhrase, int paramSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return lineRepository.findByLineNoContainsAndDescriptionContains(array[0], array[1]);
    }


    /**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     */
    public Page<Line> findByConditions(String searchPhrase, int paramSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return lineRepository.findByLineNoContainsAndDescriptionContains(array[0], array[1], pageable);
    }

}

