package com.novsky.service.line;

import com.novsky.dao.line.StationRepository;
import com.novsky.domain.line.Line;
import com.novsky.domain.line.Station;
import com.novsky.service.app.BaseService;
import com.novsky.utils.search.Searchable;
import com.novsky.utils.search.SortedSearchable;
import com.novsky.dao.line.StationRepository;
import com.novsky.service.app.BaseService;
import com.novsky.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangbin on 2016/3/24.
 * 线路业务类
 */
@Service
public class StationSearchService extends BaseService implements SortedSearchable {
    @Autowired
    StationRepository stationRepository;


    @Autowired
    LineService lineService;

    /**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     */
    public List<Station> findByConditions(String searchPhrase, int paramSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        Line line = null;
        if (!array[0].isEmpty()) {
            line = lineService.findById(Long.parseLong(array[0]));
        }
        return stationRepository.findByLineAndStationNoContainsAndDescriptionContains(line, array[1], array[2]);
    }


    /**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     */
    public Page<Station> findByConditions(String searchPhrase, int paramSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        Line line = null;
        if (!array[0].isEmpty()) {
            line = lineService.findById(Long.parseLong(array[0]));
        }
        return stationRepository.findByLineAndStationNoContainsAndDescriptionContains(line, array[1], array[2], pageable);
    }

}