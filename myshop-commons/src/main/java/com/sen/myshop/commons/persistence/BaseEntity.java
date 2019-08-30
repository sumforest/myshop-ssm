package com.sen.myshop.commons.persistence;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity implements Serializable {
    private Long id;
    private Date updated;
    private Date created;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", updated=" + updated +
                ", created=" + created +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    // @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm")
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
