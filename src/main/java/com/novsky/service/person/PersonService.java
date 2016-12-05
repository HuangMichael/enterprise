package com.novsky.service.person;

import com.novsky.dao.person.PersonRepository;
import com.novsky.domain.person.Person;
import com.novsky.service.app.BaseService;
import com.novsky.utils.CommonStatusType;
import com.novsky.utils.search.Searchable;
import com.novsky.service.app.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 人员业务类
 */
@Service
public class PersonService extends BaseService {


    @Autowired
    PersonRepository personRepository;

    /**
     * @return 查询激活状态的人员
     */
    public List<Person> findActivePerson() {
        return personRepository.findByStatus(CommonStatusType.STATUS_YES);
    }


    /**
     * @return 查询所有人员
     */
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    /**
     * @param id
     * @return 根据人员id查询人员
     */
    public Person findById(Long id) {
        return personRepository.findById(id);
    }


    /**
     * @param person
     * @return 保存人员信息
     */
    public Person save(Person person) {
        return personRepository.save(person);
    }


    /**
     * @param person
     * @return 更新人员信息
     */
    public Person update(Person person) {
        return personRepository.save(person);
    }


    /**
     * @param id
     * @return 删除人员信息
     */
    public boolean delete(Long id) {
        personRepository.delete(id);
        return (personRepository.findById(id) == null);
    }




    /**
     * @return 查询所有的id
     */

    public List<Long> selectAllId() {


        return personRepository.selectAllId();
    }





}
