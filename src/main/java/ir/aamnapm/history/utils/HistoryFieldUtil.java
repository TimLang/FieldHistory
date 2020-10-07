package ir.aamnapm.history.utils;


import ir.aamnapm.history.annotation.HistoryField;
import ir.aamnapm.history.annotation.HistoryFieldEntity;
import ir.aamnapm.history.dto.FieldHistoryDTO;
import ir.aamnapm.history.model.FieldHistory;
import ir.aamnapm.history.service.IFieldHistoryService;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class HistoryFieldUtil<D, O, F> {

    private D dto;
    private O object;
    private Class<D> dtoClass;
    private Class<O> objClass;
    private IFieldHistoryService iFieldHistoryService;

    public HistoryFieldUtil(D dto, Class<D> dtoClass, O object, Class<O> objClass, IFieldHistoryService iFieldHistoryService, F fieldHistory) {
        this.dto = dto;
        this.object = object;
        this.dtoClass = dtoClass;
        this.objClass = objClass;
        this.iFieldHistoryService = iFieldHistoryService;
    }

    private static String getKey(Field field) {
        return "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }

    public void checkAndSave() {

        Long recordId = 0L;
        Object dtoValue = null;
        Object objectValue = null;
        FieldHistory objectClassValue = null;


        for (Field field : objClass.getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(Id.class)) {
                recordId = getRecordId(field);
            }
        }

        for (Field field : objClass.getDeclaredFields()) {
            field.setAccessible(true);

            if (objClass.isAnnotationPresent(HistoryFieldEntity.class)) {
                objectValue = getObjectValue(field);
                objectClassValue = (FieldHistory) getObjectClassValue(objClass);

            }

            if (field.isAnnotationPresent(HistoryField.class)) {
                dtoValue = getDTOValue(field);
            }

            if (field.isAnnotationPresent(HistoryField.class)) {
                boolean fieldHasUpdate = checkValue(objectValue, dtoValue);

                if (fieldHasUpdate) {
                    saveOldValue(field, String.valueOf(objectValue), recordId, objectClassValue);
                }
            }
        }


    }

    private void saveOldValue(Field field, String objectValue, Long recordId, FieldHistory objectClassValue) {
        FieldHistoryDTO fieldHistoryDTO = new FieldHistoryDTO();
        fieldHistoryDTO.setField(field.getName());
        fieldHistoryDTO.setValue(objectValue);
        fieldHistoryDTO.setEndDate(new Date(System.currentTimeMillis()));
        fieldHistoryDTO.setRecordId(recordId);
        fieldHistoryDTO.setTableName(objClass.getSimpleName());
        fieldHistoryDTO.setStartDate(new Date(System.currentTimeMillis()));
        iFieldHistoryService.create(fieldHistoryDTO, objectClassValue);
    }

    private boolean checkValue(Object objectValue, Object dtoValue) {
        return objectValue != dtoValue;
    }

    private Object getObjectValue(Field field) {
        try {
            return objClass.getMethod(getKey(field)).invoke(object);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object getObjectClassValue(Class<O> field) {
        try {
            return field.getAnnotation(HistoryFieldEntity.class).sClass().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object getDTOValue(Field field) {
        try {
            return dtoClass.getMethod(getKey(field)).invoke(dto);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Long getRecordId(Field field) {
        try {
            return (Long) objClass.getMethod(getKey(field)).invoke(object);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
