package ir.aamnapm.history.model;


import ir.aamnapm.history.annotation.HistoryField;
import ir.aamnapm.history.annotation.HistoryFieldEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "family")
//@HistoryFieldEntity(name = "person")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @HistoryField
    @Column(name = "first_name", length = 50)
    private String firstName;

    @HistoryField
    @Column(name = "relationship", length = 50)
    private String relationship;

    @Column(name = "version", length = 10)
    private Integer version;

}
