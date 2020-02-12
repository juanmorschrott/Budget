package com.habitissimo.backend.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Data
public class BudgetDTO {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private String title;

    @NotNull
    private String description;

    private String category;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phone;

    private String address;

    private String date;

    private String subCategory;

    private String pricePreference;

    public Date getSubmissionDateConverted(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.date);
    }

    public void setSubmissionDate(Date date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.date = dateFormat.format(date);
    }

}
