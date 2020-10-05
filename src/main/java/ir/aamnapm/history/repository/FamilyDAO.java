package ir.aamnapm.history.repository;

import ir.aamnapm.history.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FamilyDAO extends JpaRepository<Family, Long>, JpaSpecificationExecutor<Family> {
}
