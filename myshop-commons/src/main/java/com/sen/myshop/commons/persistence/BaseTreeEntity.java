package com.sen.myshop.commons.persistence;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Sen
 * @Date: 2019/8/10 02:17
 * @Description:
 */
@Data
public abstract class BaseTreeEntity<T extends BaseEntity> extends BaseEntity implements Serializable {
    private T parent;

    private Boolean isParent;
}
