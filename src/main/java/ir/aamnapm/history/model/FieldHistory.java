package ir.aamnapm.history.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FieldHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "record_id")
    private Long recordId;

    @Column(name = "field")
    private String field;

    @Column(name = "value")
    private String value;

    @Column(name = "table_name")
    private String tableName;

}
