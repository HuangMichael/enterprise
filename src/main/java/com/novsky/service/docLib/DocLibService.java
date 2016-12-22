package com.novsky.service.docLib;

import com.novsky.dao.docLib.DocLibListRepository;
import com.novsky.dao.docLib.DocLibRepository;
import com.novsky.domain.docLib.DocLib;
import com.novsky.domain.docLib.DocLibList;
import com.novsky.service.app.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文档库业务类
 *
 * @author huangbin
 * @create 2016-12-08 15:12
 **/
@Service
public class DocLibService extends BaseService {

    @Autowired
    DocLibRepository docLibRepository;
    @Autowired
    DocLibListRepository docLibListRepository;


    /**
     * @param id
     * @return 根据id查询文档库对象
     */
    public DocLib findById(Long id) {

        return docLibRepository.findById(id);
    }


    /**
     * @return 查询所有文档库对象
     */
    public List<DocLib> findAll() {

        return docLibRepository.findAll();
    }


    /**
     * @param docLib
     * @return 保存文档库对象
     */
    public DocLib save(DocLib docLib) {

        return docLibRepository.save(docLib);
    }


    /**
     * 根据id删除文档库对象
     */
    public void delete(Long id) {
        docLibRepository.delete(id);

    }


    /**
     * @param docLibId
     * @return 根据文档库目录id查询所有的文档信息
     */
    public List<DocLibList> findByDocLib(Long docLibId) {
        return docLibListRepository.findByParent_Id(docLibId);
    }
}
