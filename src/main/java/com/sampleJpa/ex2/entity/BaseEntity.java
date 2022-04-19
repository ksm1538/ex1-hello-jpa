package com.sampleJpa.ex2.entity;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    private String writer;
    private LocalDateTime writeDt;
    private String modifier;
    private LocalDateTime modifyDt;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public LocalDateTime getWriteDt() {
        return writeDt;
    }

    public void setWriteDt(LocalDateTime writeDt) {
        this.writeDt = writeDt;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public LocalDateTime getModifyDt() {
        return modifyDt;
    }

    public void setModifyDt(LocalDateTime modifyDt) {
        this.modifyDt = modifyDt;
    }
}
