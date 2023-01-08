package com.pvrschcms.pvrcinemaschdulernew.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public abstract class DateAuditView implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at",nullable = true, updatable = false)
    private Date createdAt =new Date();

	@JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_at",nullable = true)
    private Date updatedAt =new Date();

}
