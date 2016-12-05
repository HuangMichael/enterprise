package com.novsky.service.locations;

import com.novsky.dao.locations.LocationsRepository;
import com.novsky.dao.locations.VlocationsRepository;
import com.novsky.domain.locations.Locations;
import com.novsky.domain.locations.Vlocations;
import com.novsky.object.ReturnObject;
import com.novsky.service.app.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangbin on 2016/3/24.
 */
@Service
public class LocationsService extends BaseService {


    @Autowired
    LocationsRepository locationsRepository;

    @Autowired
    VlocationsRepository vlocationsRepository;


    /**
     * 设置位置编码
     */
    public String getLocationsNo(Locations locations) {
        List<Locations> locationsList = locationsRepository.findByParentOrderByLocationDesc(locations.getId());
        String locationNo = "";
        if (!locationsList.isEmpty()) {
            Locations youngestChild = locationsList.get(0);
            if (youngestChild != null) {
                String location = youngestChild.getLocation();
                String index = location.substring(location.length() - 2, location.length());
                if (index != null && !index.equals("")) {
                    long n = (Long.parseLong(index) + 1);
                    locationNo = locations.getLocation() + ((n < 10) ? "0" + n : n);
                }
            }
        } else {
            locationNo = locations.getLocation() + "01";
        }
        return locationNo;
    }

    /**
     * @param locations 保存位置信息
     * @return
     */
    public Locations save(Locations locations) {

        return locationsRepository.save(locations);
    }


    public List<Locations> findAll() {
        return locationsRepository.findAll();
    }


    /**
     * @param location 位置编号
     * @return 当前用户分配的所有位置节点
     */
    public List<Object> findTree(String location) {
        List<Object> objectList = null;

        if (location != null && !location.equals("")) {
            objectList = locationsRepository.findTree(location);
        }
        return objectList;
    }


    /**
     * @param locLevel 节点级别
     * @return 查询节点级别小于locLevel的记录
     */
    public List<Locations> findByLocLevelLessThan(Long locLevel) {
        return locationsRepository.findByLocLevelLessThan(locLevel);
    }

    /**
     * @param id 根据ID查询位置
     * @return
     */
    public Locations findById(Long id) {
        return locationsRepository.findById(id);
    }



    /**
     * @param id 根据ID查询位置
     * @return
     */
    public List<Locations> findByParentId(Long id) {
        Locations locations = locationsRepository.findById(id);
        return locationsRepository.findByParent(id);
    }

    /**
     * @param locations 位置信息
     * @return 删除位置信息
     */
    public Boolean delete(Locations locations) {
        Long id = locations.getId();
        locationsRepository.delete(locations);
        return locationsRepository.findById(id) == null;
    }


    /**
     * 新建位置
     *
     * @param parentId 上级位置
     * @return 如果有上级根据上级生成对象  如果没有将其当做根节点
     */
    public Locations create(Long parentId) {
        Locations newObj = new Locations();
        if (parentId != null) {
            Locations parent = locationsRepository.findById(parentId);
            newObj.setLocation(getLocationsNo(parent));  //编号不自动生成
            newObj.setParent(parent.getId());
            Long level = 0l;
            if (parent.getLocLevel() != null) {
                level = parent.getLocLevel();
            }
            newObj.setLocLevel(level + 1);
        } else {
            newObj.setLocation("01");
        }
        return newObj;

    }


    /**
     * @param location
     * @return 根据位置编码模糊查询
     */
    public List<Locations> findByLocationStartingWithAndStatus(String location, String status) {

        return locationsRepository.findByLocationStartingWithAndStatus(location, status);
    }


    /**
     * @param location
     * @return 根据位置编码模糊查询
     */
    public List<Vlocations> findByLocationStartingWithAndStatus(String location) {

        return vlocationsRepository.findByLocationStartingWith(location);
    }

}

