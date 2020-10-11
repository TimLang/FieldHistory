package ir.aamnapm.history.service;

import ir.aamnapm.history.dto.ChangeLogDTO;
import ir.aamnapm.history.dto.FieldHistoryDTO;
import ir.aamnapm.history.exeption.DataException;
import ir.aamnapm.history.exeption.DateException;
import ir.aamnapm.history.exeption.NotFoundException;
import ir.aamnapm.history.model.FieldHistory;
import ir.aamnapm.history.repository.FieldHistoryDAO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FieldHistoryService implements IFieldHistoryService {

    private final FieldHistoryDAO<FieldHistory, Long> fieldHistoryDAO;

    @Override
    public void create(Field field, String objectValue, Long recordId, FieldHistory objectClassValue, String simpleName, ChangeLogDTO changeLogDTO, Object createDate) {

        if (changeLogDTO != null) {

            if (changeLogDTO.getHasDate()) {

                LocalDate endDate = checkDate(convertDateToLocalDate(changeLogDTO.getDate()));

                List<FieldHistory> all = fieldHistoryDAO.findAll();

                if (all.size() > 0) {
                    Sort sortById = Sort.by(Sort.Direction.ASC, "id");
                    List<FieldHistory> byTableNameAndRecordIdAndField = fieldHistoryDAO.findByTableNameAndRecordIdAndField(simpleName, recordId, field.getName(), sortById);
                    FieldHistory byTableNameAndRecordIdAndFieldAndEndDate = byTableNameAndRecordIdAndField.get(byTableNameAndRecordIdAndField.size() - 1);

                    if (byTableNameAndRecordIdAndFieldAndEndDate != null) {

                        if (endDate.isAfter(convertDateToLocalDate(new Date(byTableNameAndRecordIdAndFieldAndEndDate.getEndDate().getTime())))) {
                            fieldHistoryDAO.save(generateFieldHistory(recordId, field, objectValue, simpleName, byTableNameAndRecordIdAndFieldAndEndDate.getEndDate(), endDate, objectClassValue));
                        } else if (endDate.equals(convertDateToLocalDate(new Date(byTableNameAndRecordIdAndFieldAndEndDate.getEndDate().getTime())))) {
                            //in this case we dont have log. we update current record
                        } else {
                            throw new DateException("تاریخ ارسالی باید بزرگتر از تاریخ پایان آخرین رکورد باشد");
                        }
                    } else {
                        throw new DataException("دیتایی با این مشخصات در سیستم یافت نشد.");
                    }
                } else {

                    if (endDate.isAfter(((LocalDate) createDate))) {
                        fieldHistoryDAO.save(generateFieldHistory(recordId, field, objectValue, simpleName, createDate, endDate, objectClassValue));
                    } else {
                        throw new DateException("تاریخ ارسالی باید بزرگتر از تاریخ پایان آخرین رکورد باشد");
                    }
                }
            }
        } else {
            throw new DataException("ChangeLogDTO cannot be null");
        }
    }

    private LocalDate convertDateToLocalDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        return LocalDate.of(y, m, d);
    }

    private Date convertLocalDateToDate(LocalDate date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        return calendar.getTime();
    }

    private LocalDate checkDate(LocalDate date) {
        if (date == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH);
            int d = calendar.get(Calendar.DAY_OF_MONTH);
            date = LocalDate.of(y, m, d);
        }
        return date;
    }

    @SneakyThrows
    @Override
    public FieldHistoryDTO.Info getById(Long id) {
        FieldHistory fieldHistory = fieldHistoryDAO.findById(id).orElseThrow(() -> new NotFoundException("رکوردی با این شناسه پیدا نشد."));

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

    private FieldHistory generateFieldHistory(Long recordId, Field field, String objectValue, String simpleName, Object startDate, LocalDate endDate, FieldHistory objectClassValue) {
        FieldHistory fieldHistory = objectClassValue;
        fieldHistory.setField(field.getName());
        fieldHistory.setValue(objectValue);
        fieldHistory.setRecordId(recordId);
        fieldHistory.setTableName(simpleName);
        fieldHistory.setStartDate((Date) startDate);
        fieldHistory.setEndDate(convertLocalDateToDate(endDate));
        return fieldHistory;
    }
}
