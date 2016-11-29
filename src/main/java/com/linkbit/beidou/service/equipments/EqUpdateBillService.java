package com.linkbit.beidou.service.equipments;

import com.linkbit.beidou.dao.equipments.EqUpdateBillRepository;
import com.linkbit.beidou.dao.equipments.EquipmentsRepository;
import com.linkbit.beidou.dao.equipments.VEqRecordRepository;
import com.linkbit.beidou.dao.equipments.VEqUpdateBillRepository;
import com.linkbit.beidou.domain.equipments.EqUpdateBill;
import com.linkbit.beidou.domain.equipments.VEqRecord;
import com.linkbit.beidou.domain.equipments.VEqUpdateBill;
import com.linkbit.beidou.service.app.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备更新单业务类
 */
@Service
public class EqUpdateBillService extends BaseService {


    @Autowired
    EqUpdateBillRepository eqUpdateBillRepository;

    @Autowired
    VEqUpdateBillRepository vEqUpdateBillRepository;

    @Autowired
    VEqRecordRepository vEqRecordRepository;

    @Autowired
    EquipmentsRepository equipmentsRepository;


    /**
     * @param eqName
     * @param pageable 分页
     * @return 按照配件名称模糊查询分页查询
     */
    public Page<VEqUpdateBill> findByEqNameContaining(String eqName, Pageable pageable) {
        return vEqUpdateBillRepository.findByEqNameContaining(eqName, pageable);
    }


    /**
     * @param eqName
     * @return 按照配件名称模糊查询分页查询
     */
    public List<VEqUpdateBill> findByEqNameContaining(String eqName) {
        return vEqUpdateBillRepository.findByEqNameContaining(eqName);
    }


    /**
     * @return 查询所有
     */
    public EqUpdateBill findById(Long id) {
        return eqUpdateBillRepository.findById(id);
    }


    /**
     * @return 查询所有
     */
    public EqUpdateBill save(EqUpdateBill budgetBill) {
        budgetBill.setDataType("2"); //1为新置 2为更新
        return eqUpdateBillRepository.save(budgetBill);
    }


    /**
     * @param id 根据id删除 删除成功返回true/EqAddBillService
     * @return
     */
    public boolean delete(Long id) {
        if (id != null) {
            eqUpdateBillRepository.delete(id);
        }
        return eqUpdateBillRepository.findById(id) == null;
    }


    /**
     * @return 查询所有的id
     */
    public List<Long> findAllIds() {
        List<Long> ids = eqUpdateBillRepository.findAllIds();
        return ids;
    }


    /**
     * @param eid 设备id
     * @return 根据设备id查询设备的更新历史
     */
    public List<EqUpdateBill> getUpdateHistoryById(Long eid) {
        return eqUpdateBillRepository.findByEquipmentsId(eid);
    }


    /**
     * @param eid 设备id
     * @return 根据设备id查询设备的更新历史
     */
    public List<VEqRecord> getEqRecordsByEid(Long eid) {
        // Equipments equipments = equipmentsRepository.findById(eid);
        return vEqRecordRepository.findByEquipmentId(eid);
    }


}
