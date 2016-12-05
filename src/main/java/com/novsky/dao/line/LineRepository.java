package com.novsky.dao.line;


import com.novsky.domain.line.Line;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by huangbin on 2016/3/15 0008.
 * 线路信息查询接口
 */
public interface LineRepository extends CrudRepository<Line, Long>, PagingAndSortingRepository<Line, Long> {
    /**
     * 查询所有线路
     */
    @Query("select l from Line l where l.description <> '-' order by l.id")
    List<Line> findAll();

    /**
     * 根据状态查询线路
     */
    List<Line> findByStatus(String status);

    /**
     * 根据id查询
     */
    Line findById(long id);


    /**
     * 根据类型查询 1为线路  2为段
     */
    List<Line> findByType(String type);


    Page<Line> findAll(Pageable pageable);


    /**
     * @param lineNo
     * @param desc     线路描述
     * @param pageable
     * @return 线路描述模糊查询 可分页
     */
    Page<Line> findByLineNoContainsAndDescriptionContains(String lineNo, String desc, Pageable pageable);


    /**
     * @param lineNo
     * @param desc   线路描述
     * @return 线路描述模糊查询 可分页
     */
    List<Line> findByLineNoContainsAndDescriptionContains(String lineNo, String desc);


    /**
     * @return 查询所有的id
     */
    @Query("select u.id from Line u")
    List<Long> findAllIds();

}
