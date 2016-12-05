package com.novsky.service.preMaint;

import com.novsky.dao.preMaint.VpreMaintRepository;
import com.novsky.domain.preMaint.VpreMaint;
import com.novsky.service.app.BaseService;
import com.novsky.utils.search.Searchable;
import com.novsky.utils.search.SortedSearchable;
import com.novsky.dao.preMaint.VpreMaintRepository;
import com.novsky.domain.preMaint.VpreMaint;
import com.novsky.service.app.BaseService;
import com.novsky.utils.search.SortedSearchable;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangbin on 2016年10月9日11:46:27
 * 预防性维修查询业务类
 */
@Service
@Data
public class PreMaintSearchService extends BaseService implements SortedSearchable {


    @Autowired
    VpreMaintRepository vpreMaintRepository;

    /**
     * @param searchPhrase
     * @param paramsSize
     * @return
     */
    public List<VpreMaint> findByConditions(String searchPhrase, int paramsSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramsSize);

        return vpreMaintRepository.findByPmDescContainingAndLocationContains(array[0], array[1]);
    }

    /**
     * @param searchPhrase
     * @param paramsSize
     * @return
     */
    public Page<VpreMaint> findByConditions(String searchPhrase, int paramsSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramsSize);
        return vpreMaintRepository.findByPmDescContainingAndLocationContains(array[0], array[1], pageable);
    }


}
