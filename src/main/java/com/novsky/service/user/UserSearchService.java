package com.novsky.service.user;

import com.novsky.dao.user.UserRepository;
import com.novsky.domain.user.User;
import com.novsky.service.app.BaseService;
import com.novsky.utils.search.SortedSearchable;
import com.novsky.service.app.BaseService;
import com.novsky.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangbin  on 2016/4/14.
 */

@Service
public class UserSearchService extends BaseService implements SortedSearchable {

    @Autowired
    UserRepository userRepository;
    /**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     */
    public List<User> findByConditions(String searchPhrase, int paramSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return userRepository.findByUserNameContainsAndLocationStartingWith(array[0], array[1]);
    }


    /**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     */
    public Page<User> findByConditions(String searchPhrase, int paramSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return userRepository.findByUserNameContainsAndLocationStartingWith(array[0], array[1], pageable);
    }


}
