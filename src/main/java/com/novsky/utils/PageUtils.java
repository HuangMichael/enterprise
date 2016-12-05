package com.novsky.utils;/**
 * Created by Administrator on 2016/9/2.
 */

import com.novsky.domain.app.MyPage;
import com.novsky.utils.search.Searchable;
import com.novsky.utils.search.SortedSearchable;
import com.novsky.domain.app.MyPage;
import com.novsky.utils.search.SortedSearchable;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * 分页对象转换类
 *
 * @author
 * @create 2016-09-02 15:06
 **/
@Data
public class PageUtils {


    public PageUtils() {

    }

    /**
     * @param searchable   实现多条件查询的接口的业务类
     * @param searchPhrase 查询字符串
     * @param paramSize    查询参数个数
     * @param current      当前页
     * @param rowCount     每页条数
     * @return
     */
    public MyPage searchByService(Searchable searchable, String searchPhrase, int paramSize, int current, Long rowCount) {
        Page page = searchable.findByConditions(searchPhrase, paramSize, new PageRequest(current - 1, rowCount.intValue()));
        MyPage myPage = new MyPage();
        myPage.setRows(page.getContent());
        myPage.setRowCount(rowCount);
        myPage.setCurrent(current);
        myPage.setTotal(page.getTotalElements());
        return myPage;
    }

    /**
     * @param sortedSearchable 实现排序多条件查询的接口的业务类
     * @param searchPhrase     查询字符串
     * @param paramSize        查询参数个数
     * @param current          当前页
     * @param rowCount         每页条数
     * @param pageable         分页参数
     * @return
     */
    public MyPage searchBySortService(SortedSearchable sortedSearchable, String searchPhrase, int paramSize, int current, Long rowCount, Pageable pageable) {
        Page page = sortedSearchable.findByConditions(searchPhrase, paramSize, pageable);
        MyPage myPage = new MyPage();
        myPage.setRows(page.getContent());
        myPage.setRowCount(rowCount);
        myPage.setCurrent(current);
        myPage.setTotal(page.getTotalElements());
        return myPage;
    }


}
