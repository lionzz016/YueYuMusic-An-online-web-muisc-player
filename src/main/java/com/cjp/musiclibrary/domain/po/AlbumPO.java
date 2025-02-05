package com.cjp.musiclibrary.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * album
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@TableName("album")
public class AlbumPO implements Serializable {
    @TableId(value = "a_id", type = IdType.AUTO)
    private Integer aId;

    private String aName;

    private String aProducer;

    private String aCast;

    private String aReleased;

    private String aType;

    private String aSort;

    private static final long serialVersionUID = 1L;

}