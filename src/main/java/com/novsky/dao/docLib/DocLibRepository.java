package com.novsky.dao.docLib;

import com.novsky.domain.docLib.DocLib;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by huangbin on 2016/12/8.
 * 文档库接口类
 */
public interface DocLibRepository extends CrudRepository<DocLib, Long>, PagingAndSortingRepository<DocLib, Long> {


    /**
     * @param id
     * @return 根据id查询文档库对象
     */
    DocLib findById(Long id);


    /**
     * @param docLib
     * @return 保存文档库对象
     */
    DocLib save(DocLib docLib);


    /**
     * 根据id删除文档库对象
     */
    void delete(Long id);
}
