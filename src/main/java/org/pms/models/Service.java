package org.pms.models;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * User: tijo.
 */
public class Service implements Serializable {

    private static final long serialVersionUID = -8681990044907329539L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "service_auto_id")
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "category")
    private String category;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "day")
    private String day;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "updated_date")
    private String updatedDate;

    public Service() {
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (category != null ? !category.equals(service.category) : service.category != null) return false;
        if (categoryName != null ? !categoryName.equals(service.categoryName) : service.categoryName != null)
            return false;
        if (date != null ? !date.equals(service.date) : service.date != null) return false;
        if (day != null ? !day.equals(service.day) : service.day != null) return false;
        if (endTime != null ? !endTime.equals(service.endTime) : service.endTime != null) return false;
        if (frequency != null ? !frequency.equals(service.frequency) : service.frequency != null) return false;
        if (id != null ? !id.equals(service.id) : service.id != null) return false;
        if (serviceName != null ? !serviceName.equals(service.serviceName) : service.serviceName != null) return false;
        if (startTime != null ? !startTime.equals(service.startTime) : service.startTime != null) return false;
        if (updatedDate != null ? !updatedDate.equals(service.updatedDate) : service.updatedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (frequency != null ? frequency.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", frequency='" + frequency + '\'' +
                ", day='" + day + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                '}';
    }
}
