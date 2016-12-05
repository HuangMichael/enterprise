package com.novsky.service.user;

import com.novsky.dao.locations.VlocationsRepository;
import com.novsky.dao.person.PersonRepository;
import com.novsky.dao.user.UserRepository;
import com.novsky.domain.locations.Vlocations;
import com.novsky.domain.person.Person;
import com.novsky.domain.role.Role;
import com.novsky.domain.user.User;
import com.novsky.service.app.BaseService;
import com.novsky.utils.CommonStatusType;
import com.novsky.utils.MD5Util;
import com.novsky.utils.search.Searchable;
import com.novsky.utils.search.SortedSearchable;
import com.novsky.service.app.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangbin  on 2016/4/14.
 */

@Service
public class UserService extends BaseService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    VlocationsRepository vlocationsRepository;

    /**
     * 根据状态查询用户
     */
    public List<User> findByStatus(String status) {
        return userRepository.findByStatus(status);
    }


    /**
     * 根据状态查询用户
     */
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * @return 查询所有的用户
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 对用户进行加密
     */
    public User save(User user) {

        if (user.getPassword() == null) {
            user.setPassword(MD5Util.md5("123456"));
        }
        if (user.getLocation() == null) {
           // System.out.println("user.getVlocations()-----" + user.getVlocations().getLocName());
            user.setLocation("BJ");
        }
        if (user.getSortNo() == 0) {
            user.setSortNo(1l);
        }
        user.setStatus("1");
        return userRepository.save(user);
    }

    /**
     * 对用户进行加密
     */
    public User createUser(User user) {
        user.setPassword("123456");
        String password = user.getPassword();
        if (user.getPassword() != null) {
            user.setPassword(MD5Util.md5(password));
        }
        user.setStatus("1");
        return userRepository.save(user);
    }


    /**
     * 对用户进行加密
     */
    public User update(Long userId, Long personId, Long locationId, String status) {
        User user = userRepository.findById(userId);
        Person person = personRepository.findById(personId);
        Vlocations vlocations = vlocationsRepository.findById(locationId);
        user.setPerson(person);
        user.setVlocations(vlocations);
        user.setLocation(vlocations.getLocation());
        user.setStatus(status);
        return userRepository.save(user);
    }

    /**
     * 查询加密后的用户
     */

    public List<User> findByUserNameAndPasswordAndStatus(String userName, String password, String status) {

        return userRepository.findByUserNameAndPasswordAndStatus(userName, password, status);
    }


    /**
     * @param userName 用户名
     * @param status   状态
     * @return
     */
    public User findByUserNameAndStatus(String userName, String status) {
        List<User> userList = userRepository.findByUserNameAndStatus(userName, status);
        User user;
        if (!userList.isEmpty()) {
            user = userList.get(0);
        } else {
            user = null;
        }
        return user;
    }

    /**
     * 修改用户密码
     */
    public boolean changePwd(String userName, String password) {
        List<User> userList = userRepository.findByUserNameAndStatus(userName, CommonStatusType.STATUS_YES);
        if (!userList.isEmpty()) {
            User user = userList.get(0);
            user.setPassword(MD5Util.md5(password));
            user = userRepository.save(user);
            return user != null;
        } else {
            return false;
        }
    }


    public List<Long> selectAllId() {

        return userRepository.findAllId();
    }

/*
    *//**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     *//*
    public List<User> findByConditions(String searchPhrase, int paramSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return userRepository.findByUserNameContainsAndLocationStartingWith(array[0], array[1]);
    }


    *//**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询
     *//*
    public Page<User> findByConditions(String searchPhrase, int paramSize, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        return userRepository.findByUserNameContainsAndLocationStartingWith(array[0], array[1], pageable);
    }


    *//**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询 可排序
     *//*
    public List<User> findByConditions(String searchPhrase, String[] sortStr, int paramSize) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        Sort sort = super.assembleSort(sortStr);
        return userRepository.findByUserNameContainsAndLocationStartingWith(array[0], array[1], sort);
    }


    *//**
     * @param searchPhrase
     * @return 根据多条件关键字进行查询 可排序
     *//*
    public Page<User> findByConditions(String searchPhrase, int paramSize, String[] sortStr, Pageable pageable) {
        String array[] = super.assembleSearchArray(searchPhrase, paramSize);
        Sort sort = super.assembleSort(sortStr);
        return userRepository.findByUserNameContainsAndLocationStartingWith(array[0], array[1], sort, pageable);
    }*/


}
