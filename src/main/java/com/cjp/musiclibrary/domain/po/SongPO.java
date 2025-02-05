package com.cjp.musiclibrary.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * song
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@TableName("song")
public class SongPO implements Serializable {
    @TableId(value = "s_id", type = IdType.AUTO)
    private Integer sId;

    private String sName;

    private String sArtist;

    private String sDuration;

    private String sBelonged;

    private Integer sOrder;

    private static final long serialVersionUID = 1L;


}