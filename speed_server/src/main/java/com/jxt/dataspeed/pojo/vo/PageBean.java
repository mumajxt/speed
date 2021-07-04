package com.jxt.dataspeed.pojo.vo;

import com.jxt.dataspeed.pojo.dto.RecordDTO;
import lombok.Data;

import java.util.List;

/**
 * 分页对象
 */
@Data
public class PageBean {

    private int totalCount;//总记录数
    private int screenCount;//筛选出的总记录数
    private int totalPage;//总页数
    private int currentPage;//当前页码
    private int pageSize;//每页显示的条数
    private List<RecordDTO> records;//每页显示的数据集合
}
