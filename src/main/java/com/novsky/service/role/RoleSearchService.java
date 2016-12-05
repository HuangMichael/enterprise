package com.novsky.service.role;

import com.novsky.dao.role.RoleRepository;
import com.novsky.dao.user.UserRepository;
import com.novsky.domain.role.Role;
import com.novsky.domain.user.User;
import com.novsky.object.ReturnObject;
import com.novsky.service.app.BaseService;
import com.novsky.utils.CommonStatusType;
import com.novsky.utils.search.Searchable;
import com.novsky.utils.search.SortedSearchable;
import com.novsky.dao.role.RoleRepository;
import com.novsky.domain.role.Role;
import com.novsky.service.app.BaseService;
import com.novsky.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 角色业务类
 */
@Service
public class RoleSearchService extends BaseService implements SortedSearchable {

    @Autowired
    RoleRepository roleRepository;
    
    /**
     * @param searchPhrase 条件
     * @param pageable     可分页
     * @return 根据角色描述关键字进行查询
     */

    public Page<Role> findByConditions(String searchPhrase, int paramSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return roleRepository.findByRoleNameContainsAndRoleDescContains(array[0], array[1], pageable);
    }


    /**
     * @param searchPhrase
     * @return 根据角色描述关键字进行查询
     */

    public List<Role> findByConditions(String searchPhrase, int paramSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return roleRepository.findByRoleNameContainsAndRoleDescContains(array[0], array[1]);
    }

}
