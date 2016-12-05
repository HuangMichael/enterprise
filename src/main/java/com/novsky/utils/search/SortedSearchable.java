package com.novsky.utils.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by huangbin on 2016/11/18.
 * <p>
 * 可排序复合条件查询接口
 */
public interface SortedSearchable {

    /**
     * @param searchPhrase 搜索关键字组合
     * @param paramSize    搜索条件个数
     * @return
     */
    List findByConditions(String searchPhrase, int paramSize);


    /**
     * @param searchPhrase 搜索关键字组合
     * @param paramSize    搜索条件个数
     * @param pageable
     * @return
     */
    Page findByConditions(String searchPhrase, int paramSize, Pageable pageable);

}
