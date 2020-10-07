package ir.aamnapm.history.service;

import ir.aamnapm.history.dto.FieldHistoryDTO;
import ir.aamnapm.history.model.FieldHistory;
import ir.aamnapm.history.repository.FieldHistoryDAO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FieldHistoryService implements IFieldHistoryService {

    private final FieldHistoryDAO<FieldHistory, Long> fieldHistoryDAO;

    @Override
    public void create(FieldHistoryDTO dto, FieldHistory filedHistory) {
        //set endDate for old value
        FieldHistory byTableNameAndRecordIdAndFieldAndEndDate = fieldHistoryDAO.findByTableNameAndRecordIdAndFieldAndEndDate(dto.getTableName(), dto.getRecordId(), dto.getField(), null);
        if (byTableNameAndRecordIdAndFieldAndEndDate != null) {
            byTableNameAndRecordIdAndFieldAndEndDate.setEndDate(dto.getEndDate());
            fieldHistoryDAO.save(byTableNameAndRecordIdAndFieldAndEndDate);
        }

        FieldHistory fieldHistory1 = filedHistory;
        fieldHistory1.setField(dto.getField());
        fieldHistory1.setValue(dto.getValue());
        fieldHistory1.setRecordId(dto.getRecordId());
        fieldHistory1.setTableName(dto.getTableName());
        fieldHistory1.setStartDate(dto.getStartDate());
        fieldHistory1.setEndDate(dto.getEndDate());
        fieldHistoryDAO.save(fieldHistory1);
    }

    @SneakyThrows
    @Override
    public FieldHistoryDTO.Info getById(Long id) {
        FieldHistory fieldHistory = fieldHistoryDAO.findById(id).orElseThrow(null);

        FieldHistoryDTO.Info fieldHistoryDTO = new FieldHistoryDTO.Info();
        fieldHistoryDTO.setId(fieldHistory.getId());
        fieldHistoryDTO.setField(fieldHistory.getField());
        fieldHistoryDTO.setValue(fieldHistory.getValue());
        fieldHistoryDTO.setEndDate(fieldHistory.getEndDate());
        fieldHistoryDTO.setRecordId(fieldHistory.getRecordId());
        fieldHistoryDTO.setTableName(fieldHistory.getTableName());
        fieldHistoryDTO.setStartDate(fieldHistory.getStartDate());

        return fieldHistoryDTO;
    }

    @Override
    public List<FieldHistoryDTO.Info> getByRecordId(Long id) {
        List<FieldHistory> all = fieldHistoryDAO.findByRecordId(id);

        return all.stream()
                .map(fieldHistory -> {
                    FieldHistoryDTO.Info fieldHistoryDTO = new FieldHistoryDTO.Info();
                    fieldHistoryDTO.setId(fieldHistory.getId());
                    fieldHistoryDTO.setField(fieldHistory.getField());
                    fieldHistoryDTO.setValue(fieldHistory.getValue());
                    fieldHistoryDTO.setEndDate(fieldHistory.getEndDate());
                    fieldHistoryDTO.setRecordId(fieldHistory.getRecordId());
                    fieldHistoryDTO.setTableName(fieldHistory.getTableName());
                    fieldHistoryDTO.setStartDate(fieldHistory.getStartDate());
                    return fieldHistoryDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<FieldHistoryDTO.Info> getByField(String field) {
        List<FieldHistory> all = fieldHistoryDAO.findByField(field);

        return all.stream()
                .map(fieldHistory -> {
                    FieldHistoryDTO.Info fieldHistoryDTO = new FieldHistoryDTO.Info();
                    fieldHistoryDTO.setId(fieldHistory.getId());
                    fieldHistoryDTO.setField(fieldHistory.getField());
                    fieldHistoryDTO.setValue(fieldHistory.getValue());
                    fieldHistoryDTO.setEndDate(fieldHistory.getEndDate());
                    fieldHistoryDTO.setRecordId(fieldHistory.getRecordId());
                    fieldHistoryDTO.setTableName(fieldHistory.getTableName());
                    fieldHistoryDTO.setStartDate(fieldHistory.getStartDate());

                    return fieldHistoryDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<FieldHistoryDTO.Info> getByTableName(String tableName) {
        List<FieldHistory> all = fieldHistoryDAO.findByTableName(tableName);

        return all.stream()
                .map(fieldHistory -> {
                    FieldHistoryDTO.Info fieldHistoryDTO = new FieldHistoryDTO.Info();
                    fieldHistoryDTO.setId(fieldHistory.getId());
                    fieldHistoryDTO.setField(fieldHistory.getField());
                    fieldHistoryDTO.setValue(fieldHistory.getValue());
                    fieldHistoryDTO.setEndDate(fieldHistory.getEndDate());
                    fieldHistoryDTO.setRecordId(fieldHistory.getRecordId());
                    fieldHistoryDTO.setTableName(fieldHistory.getTableName());
                    fieldHistoryDTO.setStartDate(fieldHistory.getStartDate());

                    return fieldHistoryDTO;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<FieldHistoryDTO.Info> getByStartDateAndEndDate(Date startDate, Date endDate) {
        List<FieldHistory> all = fieldHistoryDAO.findByStartDateAndEndDate(startDate, endDate);

        return all.stream()
                .map(fieldHistory -> {
                    FieldHistoryDTO.Info fieldHistoryDTO = new FieldHistoryDTO.Info();
                    fieldHistoryDTO.setId(fieldHistory.getId());
                    fieldHistoryDTO.setField(fieldHistory.getField());
                    fieldHistoryDTO.setValue(fieldHistory.getValue());
                    fieldHistoryDTO.setEndDate(fieldHistory.getEndDate());
                    fieldHistoryDTO.setRecordId(fieldHistory.getRecordId());
                    fieldHistoryDTO.setTableName(fieldHistory.getTableName());
                    fieldHistoryDTO.setStartDate(fieldHistory.getStartDate());

                    return fieldHistoryDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<FieldHistoryDTO.Info> getByStartDate(Date startDate) {
        List<FieldHistory> all = fieldHistoryDAO.findByStartDate(startDate);

        return all.stream()
                .map(fieldHistory -> {
                    FieldHistoryDTO.Info fieldHistoryDTO = new FieldHistoryDTO.Info();
                    fieldHistoryDTO.setId(fieldHistory.getId());
                    fieldHistoryDTO.setField(fieldHistory.getField());
                    fieldHistoryDTO.setValue(fieldHistory.getValue());
                    fieldHistoryDTO.setEndDate(fieldHistory.getEndDate());
                    fieldHistoryDTO.setRecordId(fieldHistory.getRecordId());
                    fieldHistoryDTO.setTableName(fieldHistory.getTableName());
                    fieldHistoryDTO.setStartDate(fieldHistory.getStartDate());

                    return fieldHistoryDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<FieldHistoryDTO.Info> getByEndDate(Date endDate) {
        List<FieldHistory> all = fieldHistoryDAO.findByEndDate(endDate);

        return all.stream()
                .map(fieldHistory -> {
                    FieldHistoryDTO.Info fieldHistoryDTO = new FieldHistoryDTO.Info();
                    fieldHistoryDTO.setId(fieldHistory.getId());
                    fieldHistoryDTO.setField(fieldHistory.getField());
                    fieldHistoryDTO.setValue(fieldHistory.getValue());
                    fieldHistoryDTO.setEndDate(fieldHistory.getEndDate());
                    fieldHistoryDTO.setRecordId(fieldHistory.getRecordId());
                    fieldHistoryDTO.setTableName(fieldHistory.getTableName());
                    fieldHistoryDTO.setStartDate(fieldHistory.getStartDate());

                    return fieldHistoryDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<FieldHistoryDTO.Info> getList() {
        List<FieldHistory> all = fieldHistoryDAO.findAll();

        return all.stream()
                .map(fieldHistory -> {
                    FieldHistoryDTO.Info fieldHistoryDTO = new FieldHistoryDTO.Info();
                    fieldHistoryDTO.setId(fieldHistory.getId());
                    fieldHistoryDTO.setField(fieldHistory.getField());
                    fieldHistoryDTO.setValue(fieldHistory.getValue());
                    fieldHistoryDTO.setEndDate(fieldHistory.getEndDate());
                    fieldHistoryDTO.setRecordId(fieldHistory.getRecordId());
                    fieldHistoryDTO.setTableName(fieldHistory.getTableName());
                    fieldHistoryDTO.setStartDate(fieldHistory.getStartDate());

                    return fieldHistoryDTO;
                })
                .collect(Collectors.toList());
    }
}
