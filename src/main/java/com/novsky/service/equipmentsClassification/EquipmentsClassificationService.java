package com.novsky.service.equipmentsClassification;

import com.novsky.dao.equipments.EquipmentsClassificationRepository;
import com.novsky.domain.equipments.EquipmentsClassification;
import com.novsky.domain.units.Units;
import com.novsky.service.app.BaseService;
import com.novsky.dao.equipments.EquipmentsClassificationRepository;
import com.novsky.domain.equipments.EquipmentsClassification;
import com.novsky.domain.units.Units;
import com.novsky.service.app.BaseService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangbin on 2016/3/24.
 */
@Service
@Cacheable(value = "eqClassCache")
public class EquipmentsClassificationService extends BaseService {

    @Autowired
    EquipmentsClassificationRepository equipmentsClassificationRepository;

    /**
     * 根据上级设备种类生成子类编号
     */
    public String getCodeByParent(EquipmentsClassification equipmentClassification) {
        String code = "";
        if (equipmentClassification != null) {
            code += equipmentClassification.getClassId();
            int childrenSize = equipmentClassification.getClassificationList().size();
            for (int i = 1; i < 3 - (childrenSize + "").length(); i++) {
                code += "0";
            }
            code += (childrenSize + 1);
        } else {
            code = "001";
        }
        return code;
    }

    /**
     * 新建设备分类
     */
    public EquipmentsClassification create(Long parentId) {
        EquipmentsClassification parent = equipmentsClassificationRepository.findById(parentId);
        EquipmentsClassification newObj = new EquipmentsClassification();
        newObj.setClassId(this.getCodeByParent(parent));
        newObj.setParent(parent);
        return newObj;

    }


    /**
     * 查询所有的设备分类
     *
     * @return
     */
    @Cacheable(value = "eqClasses", key = "'eqClasses'")
    public List<EquipmentsClassification> findAll() {
        List<EquipmentsClassification> equipmentsClassificationList = equipmentsClassificationRepository.findAll();
        return equipmentsClassificationList;
    }

    /**
     * 保存设备分类
     */
    @CacheEvict(value = "eqClass", key = "'eqClass'+#p0.id", allEntries = true)
    public EquipmentsClassification save(EquipmentsClassification equipmentsClassification) {
        return equipmentsClassificationRepository.save(equipmentsClassification);
    }

    /**
     * 根据编号查询种类
     */

    public EquipmentsClassification findById(Long id) {
        return equipmentsClassificationRepository.findById(id);
    }

    /**
     * 删除设备种类
     */

    public void delete(EquipmentsClassification equipmentsClassification) {
        equipmentsClassificationRepository.delete(equipmentsClassification);
    }


    /**
     * @param cid
     * @return 查询外委单位对应的设备分类
     */
    public List<Long> getUnitsByEqClassId(Long cid) {
        EquipmentsClassification equipmentsClassification;
        List<Long> idList = new ArrayList<Long>();
        if (cid != null) {
            equipmentsClassification = equipmentsClassificationRepository.findById(cid);
            List<Units> unitList = equipmentsClassification.getUnitSet();
            for (Units unit : unitList) {
                idList.add(unit.getId());
            }

            if (idList.isEmpty()) {
                idList.add(0l);
            }
        }
        return idList;
    }
}
