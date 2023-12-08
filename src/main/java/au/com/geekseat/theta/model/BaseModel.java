package au.com.geekseat.theta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @CreatedBy
    private String createdBy;

    @JsonIgnore
    @LastModifiedBy
    private String updatedBy;

    @JsonIgnore
    @CreatedDate
    private LocalDateTime created;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updated;

    @JsonIgnore
    @Type(type = "text")
    protected String creator;

    @JsonIgnore
    @Type(type = "text")
    protected String editor;

    @JsonIgnore
    @Type(type = "text")
    protected String mapData;

    @Transient
    @JsonIgnore
    @Type(type = "text")
    protected String transitMapData;

    @Transient
    protected Map<String, Object> map = new HashMap<>();

    @Transient
    protected Map<String, Object> transitMap = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getMapData() {
        return mapData;
    }

    public void setMapData(String mapData) {
        this.mapData = mapData;
    }

    public String getTransitMapData() {
        return transitMapData;
    }

    public void setTransitMapData(String transitMapData) {
        this.transitMapData = transitMapData;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Map<String, Object> getTransitMap() {
        return transitMap;
    }

    public void setTransitMap(Map<String, Object> transitMap) {
        this.transitMap = transitMap;
    }
}
