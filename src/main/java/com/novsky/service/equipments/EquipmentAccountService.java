package com.novsky.service.equipments;

import com.novsky.dao.equipments.EquipmentsRepository;
import com.novsky.dao.equipments.VEqRepository;
import com.novsky.dao.locations.VlocationsRepository;
import com.novsky.dao.outsourcingUnit.OutsourcingUnitRepository;
import com.novsky.domain.equipments.Equipments;
import com.novsky.domain.equipments.Vequipments;
import com.novsky.domain.locations.Locations;
import com.novsky.domain.locations.Vlocations;
import com.novsky.domain.role.Role;
import com.novsky.domain.units.Units;
import com.novsky.service.app.BaseService;
import com.novsky.service.locations.LocationsService;
import com.novsky.utils.CommonStatusType;
import com.novsky.utils.search.Searchable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangbin on 2016/5/4.
 * 设备台账业务类
 */
@Service
public class EquipmentAccountService extends BaseService {

    Log log = LogFactory.getLog(this.getClass());


    @Autowired
    EquipmentsRepository equipmentsRepository;


    @Autowired
    VEqRepository vEqRepository;


    @Autowired
    VlocationsRepository vlocationsRepository;


    @Autowired
    OutsourcingUnitRepository outsourcingUnitRepository;


    @Autowired
    LocationsService locationsService;


    /**
     * @param equipments 保存设备信息
     * @return
     */
    @Transactional
    public Equipments save(Equipments equipments) {
        // equipments = updateLocation(equipments);
        equipments = equipmentsRepository.save(equipments);
        return equipments;
    }



    /**
     * @param eqCode 设备编号
     * @return 根据设备编号查询设备数量
     */
    public boolean checkExist(String eqCode) {

        boolean exists = equipmentsRepository.selectCountByEqcode(eqCode) > 0;
        return exists;
    }


    /**
     * @param id 根据id查询设备信息
     * @return
     */
    public Equipments findById(Long id) {
        return equipmentsRepository.findById(id);
    }

    /**
     * @param id 根据id查询设备信息
     * @return
     */
    public Vequipments findOne(Long id) {
        return vEqRepository.findOne(id);
    }

    /**
     * @return 查询所有
     */
    public List<Equipments> findAll() {
        return equipmentsRepository.findAll();
    }

    /**
     * @return 查询所有
     */
    public Page<Vequipments> findAll(Pageable pageable) {
        return vEqRepository.findAll(pageable);
    }


    /**
     * @param location
     * @return 查询位置下的设备信息
     */
    public List<Equipments> findByLocation(Locations location) {

        log.info("-------------------按照位置查询设备");
        return equipmentsRepository.findByLocations(location);
    }


    /**
     * @param id 根据id删除设备信息
     */
    public void delete(Long id) {
        equipmentsRepository.delete(id);
    }


    /**
     * @return 查询所有的外委单位
     */
    public List<Units> findAllUnit() {
        return outsourcingUnitRepository.findByStatus(CommonStatusType.STATUS_YES);
    }


    /**
     * @param eid 保存设备信息
     * @return
     */
    public List<Object> findFixingStepByEid(Long eid) {
        return equipmentsRepository.findFixingStepByEid(eid);
    }


    /**
     * @param eid 根据设备id查询维修过程的所有节点
     * @return
     */
    public List<Object> findFixStepsByEid(Long eid) {
        return equipmentsRepository.findFixStepsByEid(eid);
    }


    /**
     * @param orderLineNo 跟踪号
     * @return 根据跟踪号查询节点信息
     */
    public List<Object> findFixStepsByOrderLineNo(String orderLineNo) {
        return equipmentsRepository.findFixStepsByOrderLineNo(orderLineNo);
    }

    public List<Object> findFixHistoryByEid(Long eid) {
        return equipmentsRepository.findFixHistoryByEid(eid);
    }

    public List<Object> findLastFixHistoryByEid(Long eid) {
        return equipmentsRepository.findLastFixHistoryByEid(eid);
    }


    public List<Object> findAllFixStepsByOrderLineNo(String orderLineNo) {
        return equipmentsRepository.findAllFixStepsByOrderLineNo(orderLineNo);
    }


    public List<Object> findAllFixStepsByEid(Long eid) {
        return equipmentsRepository.findEndFixStepsByEid(eid);
    }


    /**
     * @param eqCode
     * @return 查询维修历史信息
     */
    public Boolean eqCodeExists(String eqCode) {
        List<Equipments> equipmentsList = new ArrayList<Equipments>();
        if (eqCode != null && !eqCode.equals("")) {
            equipmentsList = equipmentsRepository.findByEqCode(eqCode);
        }
        return !equipmentsList.isEmpty();
    }


    /**
     * @param equipments
     * @return 返回true 删除成功
     */
    public Boolean delete(Equipments equipments) {
        equipmentsRepository.delete(equipments);
        Equipments e = equipmentsRepository.findById(equipments.getId());
        return e == null;
    }


    /**
     * @param equipments
     * @return 返回true 删除成功
     */
    public String abandon(Equipments equipments) {
        equipments.setStatus("2");
        equipments = equipmentsRepository.save(equipments);
        return equipments.getStatus();

    }

    /**
     * @param eqName   设备名称
     * @param pageable
     * @return 分页查询 根据易耗品名称去查询
     */
    public Page<Vequipments> findByEqNameContains(String eqName, Pageable pageable) {

        return vEqRepository.findByEqNameContains(eqName, pageable);
    }


    /**
     * @param eqName   设备名称
     * @param pageable
     * @return 分页查询 根据易耗品名称去查询
     */
    public Page<Vequipments> findByEqNameContainsAndLocationId(String eqName, Long locationId, Pageable pageable) {

        Locations locations = locationsService.findById(locationId);

        return vEqRepository.findByEqNameContainsAndLocation(eqName, locations, pageable);
    }


    /**
     * @param searchPhrase 查询字符串数组
     * @param pageable
     * @return 分页查询 根据易耗品名称去查询
     */
    public Page<Vequipments> findByComplex(String searchPhrase, Pageable pageable) {
        String eqName = "", eqClass = "", locName = "";
        if (searchPhrase != null && !searchPhrase.equals("")) {
            String searchParams[] = searchPhrase.split(",");
            eqName = searchParams[0];
            eqClass = searchParams[1];
            locName = searchParams[2];
        }
        return vEqRepository.findByEqNameContainsAndLocNameContainsAndEqClassContains(eqName, locName, eqClass, pageable);
    }


    /**
     * @param eqName 设备名称
     * @return 分页查询 根据易耗品名称去查询
     */
    public List<Vequipments> findByEqNameContains(String eqName) {

        return vEqRepository.findByEqNameContains(eqName);
    }

    public List<Long> selectAllId() {

        return equipmentsRepository.findAllId();
    }

}
