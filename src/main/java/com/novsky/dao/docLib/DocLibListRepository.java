package com.novsky.dao.docLib;

import com.novsky.domain.docLib.DocLibList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/12/8.
 * 文档库接口类
 */
public interface DocLibListRepository extends CrudRepository<DocLibList, Long>, PagingAndSortingRepository<DocLibList, Long> {


    /**
     * @param id
     * @return 根据id查询文档库对象
     */
    DocLibList findById(Long id);


    /**
     * @return 保存文档库对象
     */
    DocLibList save(DocLibList docLibList);


    /**
     * 根据id删除文档库对象
     */
    void delete(Long id);

    /**
     * @return 查询所有的文档库
     */
    @Override
    List<DocLibList> findAll();


    /**
     * @param id
     * @return 根据上级节点查询目录下的所有文档信息
     */
    List<DocLibList> findByParent_Id(Long id);
}
