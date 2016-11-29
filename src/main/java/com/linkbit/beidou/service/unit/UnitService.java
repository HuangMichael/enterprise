package com.linkbit.beidou.service.unit;

import com.linkbit.beidou.dao.equipments.EquipmentsClassificationRepository;
import com.linkbit.beidou.dao.outsourcingUnit.OutsourcingUnitRepository;
import com.linkbit.beidou.dao.workOrder.WorkOrderReportCartRepository;
import com.linkbit.beidou.domain.equipments.EquipmentsClassification;
import com.linkbit.beidou.domain.units.Units;
import com.linkbit.beidou.domain.user.User;
import com.linkbit.beidou.domain.workOrder.WorkOrderReportCart;
import com.linkbit.beidou.service.app.BaseService;
import com.linkbit.beidou.service.equipmentsClassification.EquipmentsClassificationService;
import com.linkbit.beidou.utils.search.Searchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 外委单位业务类
 */
@Service
public class UnitService extends BaseService {


    //根据设备分类查询对应外委单位
    @Autowired
    OutsourcingUnitRepository unitsRepository;
    @Autowired
    EquipmentsClassificationRepository equipmentsClassificationRepository;
    @Autowired
    EquipmentsClassificationService equipmentsClassificationService;
    @Autowired
    WorkOrderReportCartRepository workOrderReportCartRepository;

    /**
     * @return 查询所有外委单位信息
     */
    public List<Units> findAll() {
        return unitsRepository.findAll();
    }

    /**
     * @return 根据状态查询所有外委单位信息
     */
    public List<Units> findByStatus(String status) {
        return unitsRepository.findByStatus(status);
    }


    /**
     * @param eqClassId
     * @return 根据设备分类的ID查询对应的外委单位信息
     */
    public List<Object> findUnitListByEqClassIdEq(Long eqClassId) {

        return unitsRepository.findUnitListByEqClassIdEq(eqClassId);
    }

    /**
     * @param eqClassId
     * @return 根据设备分类查询非该分类对应的外委单位信息 id 描述
     */
    public List<Object> findUnitListByEqClassIdNotEq(Long eqClassId) {

        List<Long> longList = equipmentsClassificationService.getUnitsByEqClassId(eqClassId);
        return unitsRepository.findUnitListByEqClassIdNotEq(longList);
    }


    /**
     * @param uid
     * @return 根据根据ID查询外委单位信息
     */
    public Units findById(Long uid) {
        return unitsRepository.findById(uid);
    }


    /**
     * @param unit
     * @return 删除外委单位
     */
    public Boolean delete(Units unit) {
        unitsRepository.delete(unit);
        unit = unitsRepository.findById(unit.getId());
        return unit == null;
    }


    /**
     * @param cid 设备种类id
     * @param ids 外委单位id集合字符串
     * @return 加入外委单位 返回种类本身
     */
    public List<Units> addUnits(Long cid, String ids) {
        EquipmentsClassification equipmentsClassification = equipmentsClassificationRepository.findById(cid);
        List<Units> originalUnits = equipmentsClassification.getUnitSet();
        List<Units> outsourcingUnitSet = new ArrayList<Units>();
        if (equipmentsClassification != null && ids != null) {
            String idArray[] = ids.split(",");
            for (String id : idArray) {
                outsourcingUnitSet.add(unitsRepository.findById(Long.parseLong(id)));
            }
            outsourcingUnitSet.addAll(originalUnits);
            equipmentsClassification.setUnitSet(outsourcingUnitSet);
            equipmentsClassificationRepository.save(equipmentsClassification);
        }
        return outsourcingUnitSet;
    }

    /**
     * @param cid 设备种类id
     * @param ids 外委单位id集合字符串
     * @return 加入外委单位 返回外委单位集合
     */
    @Transactional
    public List<Units> addU2c(Long cid, String ids, Long workOrderId) {
        WorkOrderReportCart workOrderReportCart = workOrderReportCartRepository.findById(workOrderId);
        EquipmentsClassification equipmentsClassification = equipmentsClassificationRepository.findById(cid);
        workOrderReportCart.setEquipmentsClassification(equipmentsClassification);
        workOrderReportCartRepository.save(workOrderReportCart);
        List<Units> originalUnits = equipmentsClassification.getUnitSet();
        List<Units> outsourcingUnitSet = new ArrayList<Units>();
        if (equipmentsClassification != null && ids != null) {
            String idArray[] = ids.split(",");
            for (String id : idArray) {
                outsourcingUnitSet.add(unitsRepository.findById(Long.parseLong(id)));
            }
            outsourcingUnitSet.addAll(originalUnits);
            equipmentsClassification.setUnitSet(outsourcingUnitSet);
            equipmentsClassificationRepository.save(equipmentsClassification);
        }
        return outsourcingUnitSet;
    }


    /**
     * @param cid 设备种类id
     * @param ids 外委单位id集合字符串
     * @return 加入外委单位 返回种类本身
     */
    public EquipmentsClassification removeUnits(Long cid, String ids) {
        EquipmentsClassification equipmentsClassification = equipmentsClassificationRepository.findById(cid);
        List<Units> originalUnits = equipmentsClassification.getUnitSet();
        List<Units> outsourcingUnitSet = new ArrayList<Units>();
        if (equipmentsClassification != null && ids != null) {
            String idArray[] = ids.split(",");
            for (String id : idArray) {
                outsourcingUnitSet.add(unitsRepository.findById(Long.parseLong(id)));
            }
            originalUnits.removeAll(outsourcingUnitSet);
            equipmentsClassification.setUnitSet(originalUnits);
            equipmentsClassification = equipmentsClassificationRepository.save(equipmentsClassification);
        }
        return equipmentsClassification;
    }

    /**
     * @param unitCode
     * @return 查询维修历史信息
     */
    public Boolean unitNoExists(String unitCode) {
        List<Units> equipmentsList = new ArrayList<Units>();
        if (unitCode != null && !unitCode.equals("")) {
            equipmentsList = unitsRepository.findByUnitNo(unitCode);
        }
        return !equipmentsList.isEmpty();
    }


    /**
     * @param outsourcingUnit 外委单位信息
     * @param eqClassId       设备分类ID
     * @return
     */
    public List<Units> saveLink(Units outsourcingUnit, Long eqClassId) {
        EquipmentsClassification equipmentsClassification = equipmentsClassificationRepository.findById(eqClassId);
        List<Units> unitList = equipmentsClassification.getUnitSet();
        if (unitList.isEmpty()) {
            unitList = new ArrayList<Units>();
        }
        unitList.add(outsourcingUnit);
        equipmentsClassification.setUnitSet(unitList);
        equipmentsClassification = equipmentsClassificationRepository.save(equipmentsClassification);
        return equipmentsClassification.getUnitSet();
    }


    /**
     * @return 查询所有的id
     */
    public List<Long> findAllIds() {
        List<Long> ids = unitsRepository.findAllIds();
        return ids;
    }

}
