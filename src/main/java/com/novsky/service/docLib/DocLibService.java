package com.novsky.service.docLib;

import com.novsky.dao.docLib.DocLibRepository;
import com.novsky.domain.docLib.DocLib;
import com.novsky.service.app.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 文档库业务类
 *
 * @author huangbin
 * @create 2016-12-08 15:12
 **/
public class DocLibService extends BaseService {

    @Autowired
    DocLibRepository docLibRepository;


    /**
     * @param id
     * @return 根据id查询文档库对象
     */
    public DocLib findById(Long id) {

        return docLibRepository.findById(id);
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


}
