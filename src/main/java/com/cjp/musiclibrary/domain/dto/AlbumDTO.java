package com.cjp.musiclibrary.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author CJP
 * @version 1.0
 */
@Data
@Component
public class AlbumDTO implements Serializable {
    private Integer aId;

    private String aName;

    private String aProducer;

    private String aCast;

    private String aReleased;

    private String aType;


    private static final long serialVersionUID = 1L;

}
