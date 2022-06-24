package com.xjh.gamestory.common.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Page
 * @Description 分页实体类
 * @Author simonsfan
 * @Date 2019/1/8
 * Version  1.0
 */
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 130050641959720183L;

    /**
     * 数据集合
     */
    private List<T> list;

    /**
     * 总记录数
     */
    private Integer totalRecord;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer startIndex;
    private Integer totalPage;


    /**
     * 将Mybatis的Page转为自定义的分页结果
     * @param page
     * @return
     */
    public PageResult<T> convertToPageResult(Page<T> page){
        PageResult<T> pageResult = new PageResult<>();
        //所有数据集合
        pageResult.setList(page.getRecords());
        //总记录数
        pageResult.setTotalRecord((int)page.getTotal());
        //当前页码
        pageResult.setCurrentPage((int)page.getCurrent());
        //每页大小
        pageResult.setPageSize((int)page.getSize());
        //总页数:  总记录数 / 每页大小 + 1
        pageResult.setTotalPage((int)(page.getTotal() / page.getSize() + 1));
        return pageResult;
    }



    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
