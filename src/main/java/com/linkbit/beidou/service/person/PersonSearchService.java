package com.linkbit.beidou.service.person;

import com.linkbit.beidou.dao.person.PersonRepository;
import com.linkbit.beidou.domain.person.Person;
import com.linkbit.beidou.service.app.BaseService;
import com.linkbit.beidou.utils.CommonStatusType;
import com.linkbit.beidou.utils.search.Searchable;
import com.linkbit.beidou.utils.search.SortedSearchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人员业务类
 */
@Service
public class PersonSearchService extends BaseService implements SortedSearchable {


    @Autowired
    PersonRepository personRepository;


    /**
     * @param pageable
     * @return 根据人员姓名模糊查询
     */

    public Page<Person> findByConditions(String searchPhrase, int paramSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return personRepository.findByPersonNoContainsAndPersonNameContains(array[0], array[1], pageable);

    }


    /**
     * @param searchPhrase
     * @return 根据人员姓名模糊查询
     */

    public List<Person> findByConditions(String searchPhrase, int paramSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return personRepository.findByPersonNoContainsAndPersonNameContains(array[0], array[1]);
    }

}
