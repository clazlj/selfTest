package service.dto;

import java.util.List;

public class FrequencyDTO {
    /**
     * 生效起始日期
     */
    private String beginTime;

    /**
     * 生效截止日期
     */
    private String endTime;
    /**
     * 定时类型：1，一次性；2，每天；3，每周；4，每月
     */
    private String type;

    /**
     * 周几集合(定时类型为每周时用)
     */
    private List<String> weekDays;

    /**
     * 日期集合(定时类型为每月时用)
     */
    private List<String> days;

    /**
     * 每日起始日期(定时类型为一次性之外的用)
     */
    private String dayBeginTime;

    /**
     * 每日截止日期(定时类型为一次性之外的用)
     */
    private String dayEndTime;



    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<String> weekDays) {
        this.weekDays = weekDays;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public String getDayBeginTime() {
        return dayBeginTime;
    }

    public void setDayBeginTime(String dayBeginTime) {
        this.dayBeginTime = dayBeginTime;
    }

    public String getDayEndTime() {
        return dayEndTime;
    }

    public void setDayEndTime(String dayEndTime) {
        this.dayEndTime = dayEndTime;
    }
}
