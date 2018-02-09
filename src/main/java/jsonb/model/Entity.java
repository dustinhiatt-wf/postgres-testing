package jsonb.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Map;

@javax.persistence.Entity
public class Entity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    public Long revision = 0L;

    @Column
    public String name;

    @Column(columnDefinition = "jsonb")
    @Type(type = "JsonDataUserType")
    public Map<String, Object> data;

    @PrePersist
    public void onSave() {
        this.revision++;
    }
}
