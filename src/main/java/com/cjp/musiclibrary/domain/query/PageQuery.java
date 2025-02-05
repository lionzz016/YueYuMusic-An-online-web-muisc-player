package com.cjp.musiclibrary.domain.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjp.musiclibrary.domain.po.SongPO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author CJP
 * @version 1.0
 */
@Data
@Component
@Tag(name = "统一分页查询实体，需要被继承")
public class PageQuery {
    //    分页条件
//    1.查询第几页,页码
    private Long pageNum = 1L;
    //    2.每页的结果数量,当前页码数据量
    private Long pageSize = 5L;

    //    排序条件
//    1.升序或降序
    private Boolean isAsc = true;
    //    2.排序依据
    private String sortBy;

    public <T> Page<T> toPage(OrderItem... items) {
        Page<T> page = new Page<>(pageNum, pageSize);
        if (StrUtil.isNotBlank(sortBy)) {
            page.addOrder(new OrderItem().setAsc(isAsc).setColumn(sortBy));
        } else if (items != null) {
            page.addOrder(items);
        }
        return page;
    }


}
