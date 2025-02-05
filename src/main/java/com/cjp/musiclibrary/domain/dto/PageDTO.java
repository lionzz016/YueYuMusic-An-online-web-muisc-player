package com.cjp.musiclibrary.domain.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjp.musiclibrary.domain.vo.SongSearchVO;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author CJP
 * @version 1.0
 */
@Data
@Component
@Tag(name = "分页查询结果实体")
public class PageDTO<T> {
    @Schema(description = "总条数")
    private Long pageTotal;

    @Schema(description = "总页数")
    private Long pageCount;

    @Schema(description = "结果集合")
    private List<T> pageList;

    public static <PO, VO> PageDTO<VO> of(Page<PO> page, Class<VO> clazz) {
        PageDTO<VO> dto = new PageDTO<>();
        dto.setPageTotal(page.getTotal());
        dto.setPageCount(page.getPages());
        if (CollUtil.isEmpty(page.getRecords())) {
            page.setRecords(Collections.emptyList());
        }
        dto.setPageList(BeanUtil.copyToList(page.getRecords(), clazz));
        return dto;
    }
}
