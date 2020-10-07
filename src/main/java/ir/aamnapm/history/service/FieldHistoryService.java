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
public class FieldHistoryService<T extends FieldHistory> implements IFieldHistoryService<T> {

    private final FieldHistoryDAO<T, Long> fieldHistoryDAO;

    @Override
    public void create(FieldHistoryDTO dto, T filedHistory) {
        //set endDate for old value
        T byTableNameAndRecordIdAndFieldAndEndDate = fieldHistoryDAO.findByTableNameAndRecordIdAndFieldAndEndDate(dto.getTableName(), dto.getRecordId(), dto.getField(), null);
        if (byTableNameAndRecordIdAndFieldAndEndDate != null) {
            byTableNameAndRecordIdAndFieldAndEndDate.setEndDate(dto.getEndDate());
            fieldHistoryDAO.save(byTableNameAndRecordIdAndFieldAndEndDate);
        }

        T fieldHistory = filedHistory;
        fieldHistory.setField(dto.getField());
        fieldHistory.setValue(dto.getValue());
        fieldHistory.setRecordId(dto.getRecordId());
        fieldHistory.setTableName(dto.getTableName());
        fieldHistory.setStartDate(dto.getStartDate());
        fieldHistoryDAO.save(fieldHistory);
    }

    @SneakyThrows
    @Override
    public FieldHistoryDTO.Info getById(Long id) {
        T fieldHistory = fieldHistoryDAO.findById(id).orElseThrow(null);

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
        List<T> all = fieldHistoryDAO.findByRecordId(id);

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
        List<T> all = fieldHistoryDAO.findByField(field);

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
        List<T> all = fieldHistoryDAO.findByTableName(tableName);

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
        List<T> all = fieldHistoryDAO.findByStartDateAndEndDate(startDate, endDate);

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
        List<T> all = fieldHistoryDAO.findByStartDate(startDate);

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
        List<T> all = fieldHistoryDAO.findByEndDate(endDate);

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
        List<T> all = fieldHistoryDAO.findAll();

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
