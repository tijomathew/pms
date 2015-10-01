package org.pms.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tijo on 22/9/15.
 */

@Entity
@Table(name = "usersessionlog")
public class UserSessionLogger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login_time")
    private Long loginTime;

    @Column(name = "logout_time")
    private Long logoutTime;

    @Column(name = "remark")
    private String remark;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "accessed_ip")
    private String accessedIP;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "region")
    private String region;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_session_log_id")
    private User sessionLogMappedUser;

    public UserSessionLogger() {
    }

    public Long getId() {
        return id;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Long logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAccessedIP() {
        return accessedIP;
    }

    public void setAccessedIP(String accessedIP) {
        this.accessedIP = accessedIP;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public User getSessionLogMappedUser() {
        return sessionLogMappedUser;
    }

    public void setSessionLogMappedUser(User sessionLogMappedUser) {
        this.sessionLogMappedUser = sessionLogMappedUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSessionLogger that = (UserSessionLogger) o;

        if (!accessedIP.equals(that.accessedIP)) return false;
        if (!countryCode.equals(that.countryCode)) return false;
        if (!countryName.equals(that.countryName)) return false;
        if (!sessionLogMappedUser.equals(that.sessionLogMappedUser)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accessedIP.hashCode();
        result = 31 * result + countryCode.hashCode();
        result = 31 * result + countryName.hashCode();
        result = 31 * result + sessionLogMappedUser.hashCode();
        return result;
    }


}
