package com.antoncharov.statistics.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class PhoneCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Boolean Destination;    //- направление звонка(Входящий/Исходящий)
    @NotBlank(message = "Номер не должен быть пустым")
    private String Number;          //- номер телефона
    private String Caller;          //- ФИО абонента
    private String Operator;        //- ФИО оператора принявшего звонок
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date CallDate;          //- время и дата обработки звонка
    private String Comment;         //- комментарии с содержанием звонка

    public PhoneCall() {
        Destination = false;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Boolean getDestination() {
        return Destination;
    }

    public void setDestination(Boolean destination) {
        Destination = destination;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getCaller() {
        return Caller;
    }

    public void setCaller(String caller) {
        Caller = caller;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String operator) {
        Operator = operator;
    }

    public Date getCallDate() {
        return CallDate;
    }

    public void setCallDate(Date callDate) {
        CallDate = callDate;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
