package ir.aamnapm.history.service;

import ir.aamnapm.history.dto.FieldHistoryDTO;
import ir.aamnapm.history.model.FieldHistory;

import java.util.Date;
import java.util.List;

public interface IFieldHistoryService {

    void create(FieldHistoryDTO dto, FieldHistory filedHistory);

    FieldHistoryDTO.Info getById(Long id);

    List<FieldHistoryDTO.Info> getByRecordId(Long id);

    List<FieldHistoryDTO.Info> getByField(String field);

    List<FieldHistoryDTO.Info> getByTableName(String tableName);

    List<FieldHistoryDTO.Info> getByStartDateAndEndDate(Date startDate, Date endDate);

    List<FieldHistoryDTO.Info> getByStartDate(Date startDate);

    List<FieldHistoryDTO.Info> getByEndDate(Date endDate);

    List<FieldHistoryDTO.Info> getList();

}
