package ir.aamnapm.history.repository;

import ir.aamnapm.history.model.FieldHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonDAO extends JpaRepository<FieldHistory, Long>, JpaSpecificationExecutor<FieldHistory> {
}
