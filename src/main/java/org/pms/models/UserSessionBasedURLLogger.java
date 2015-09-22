package org.pms.models;

/**
 * Created by tijo on 22/9/15.
 */

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usersessionurllog")
public class UserSessionBasedURLLogger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "init_time")
    private DateTime urlVisitInitTime;

    @Column(name = "end_time")
    private DateTime urlVisitEndTime;

    @Column(name = "total_spent_time")
    private Double totalSpentTime;

    @Column(name = "visited_url")
    private String visitedURL;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_session_url_log_id")
    private UserSessionLogger userSessionLogger;

    public UserSessionBasedURLLogger() {
    }

    public Long getId() {
        return id;
    }

    public DateTime getUrlVisitInitTime() {
        return urlVisitInitTime;
    }

    public void setUrlVisitInitTime(DateTime urlVisitInitTime) {
        this.urlVisitInitTime = urlVisitInitTime;
    }

    public DateTime getUrlVisitEndTime() {
        return urlVisitEndTime;
    }

    public void setUrlVisitEndTime(DateTime urlVisitEndTime) {
        this.urlVisitEndTime = urlVisitEndTime;
    }

    public Double getTotalSpentTime() {
        return totalSpentTime;
    }

    public void setTotalSpentTime(Double totalSpentTime) {
        this.totalSpentTime = totalSpentTime;
    }

    public String getVisitedURL() {
        return visitedURL;
    }

    public void setVisitedURL(String visitedURL) {
        this.visitedURL = visitedURL;
    }

    public UserSessionLogger getUserSessionLogger() {
        return userSessionLogger;
    }

    public void setUserSessionLogger(UserSessionLogger userSessionLogger) {
        this.userSessionLogger = userSessionLogger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSessionBasedURLLogger that = (UserSessionBasedURLLogger) o;

        if (totalSpentTime != null ? !totalSpentTime.equals(that.totalSpentTime) : that.totalSpentTime != null)
            return false;
        if (urlVisitEndTime != null ? !urlVisitEndTime.equals(that.urlVisitEndTime) : that.urlVisitEndTime != null)
            return false;
        if (!urlVisitInitTime.equals(that.urlVisitInitTime)) return false;
        if (!userSessionLogger.equals(that.userSessionLogger)) return false;
        if (!visitedURL.equals(that.visitedURL)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = urlVisitInitTime.hashCode();
        result = 31 * result + visitedURL.hashCode();
        result = 31 * result + userSessionLogger.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("urlVisitInitTime", urlVisitInitTime)
                .append("urlVisitEndTime", urlVisitEndTime)
                .append("totalSpentTime", totalSpentTime)
                .append("visitedURL", visitedURL)
                .append("userSessionLogger", userSessionLogger)
                .toString();
    }
}
