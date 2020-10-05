package ir.aamnapm.history.repository;

import ir.aamnapm.history.model.FieldHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface FieldHistoryDAO extends JpaRepository<FieldHistory, Long>, JpaSpecificationExecutor<FieldHistory> {

    List<FieldHistory> findByRecordId(Long id);

    List<FieldHistory> findByField(String field);

    List<FieldHistory> findByTableName(String tableName);

    List<FieldHistory> findByStartDateAndEndDate(Date startDate, Date endDate);

    List<FieldHistory> findByStartDate(Date startDate);

    List<FieldHistory> findByEndDate(Date endDate);

    FieldHistory findByTableNameAndRecordIdAndFieldAndEndDate(String tableName, Long recordId, String field, Date endDate);

}
