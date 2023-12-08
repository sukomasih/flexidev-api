package au.com.geekseat.theta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Type(type = "text")
    protected String mapData;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime created;
    private LocalDateTime updated;
    @Type(type = "text")
    protected String creator;
    @Type(type = "text")
    protected String editor;
}
