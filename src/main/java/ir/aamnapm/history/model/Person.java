package ir.aamnapm.history.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "age", length = 4)
    private Integer age;

    @Column(name = "comment", length = 5000)
    private String comment;

    @Column(name = "version", length = 10)
    private Integer version;

}
