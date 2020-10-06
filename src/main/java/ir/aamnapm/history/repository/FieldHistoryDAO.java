package ir.aamnapm.history.repository;


import ir.aamnapm.history.model.FieldHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FieldHistoryDAO<E extends FieldHistory, ID> extends JpaRepository<E, ID> {

    List<E> findByRecordId(ID id);

    List<E> findByField(String field);

    List<E> findByTableName(String tableName);

    List<E> findByStartDateAndEndDate(Date startDate, Date endDate);

    List<E> findByStartDate(Date startDate);

    List<E> findByEndDate(Date endDate);

    E findByTableNameAndRecordIdAndFieldAndEndDate(String tableName, ID recordId, String field, Date endDate);

}
