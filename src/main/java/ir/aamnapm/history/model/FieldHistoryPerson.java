package ir.aamnapm.history.model;

import ir.aamnapm.history.annotation.HistoryFieldEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "field_history_person")
public class FieldHistoryPerson extends FieldHistory {
}
