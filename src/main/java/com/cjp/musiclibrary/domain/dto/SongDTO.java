package com.cjp.musiclibrary.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author CJP
 * @version 1.0
 */
@Data
@Component
public class SongDTO implements Serializable {
    private Integer sId;

    private String sName;

    private String sArtist;

    private String sDuration;

    private String sBelonged;

    private Integer sOrder;

    private static final long serialVersionUID = 1L;


}
