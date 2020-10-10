package ir.aamnapm.history.model;


import ir.aamnapm.history.annotation.HistoryField;
import ir.aamnapm.history.annotation.HistoryFieldEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "person")
@EntityListeners(AuditingEntityListener.class)
@HistoryFieldEntity(sClass = FieldHistoryPerson.class)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @HistoryField
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @HistoryField
    @Column(name = "age", length = 4)
    private Integer age;

    @Column(name = "comment", length = 5000)
    private String comment;

    @Column(name = "version", length = 10)
    private Integer version;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private Date createdDate;
}
