package com.linkbit.beidou.service.role;

import com.linkbit.beidou.dao.role.RoleRepository;
import com.linkbit.beidou.dao.user.UserRepository;
import com.linkbit.beidou.domain.role.Role;
import com.linkbit.beidou.domain.user.User;
import com.linkbit.beidou.object.ReturnObject;
import com.linkbit.beidou.service.app.BaseService;
import com.linkbit.beidou.utils.CommonStatusType;
import com.linkbit.beidou.utils.search.Searchable;
import com.linkbit.beidou.utils.search.SortedSearchable;
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
