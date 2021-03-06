/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.mobilebasicsanity.wmstudio;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Jobs generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`JOBS`")
public class Jobs implements Serializable {

    private String jobId;
    private String jobTitle;
    private Integer minSalary;
    private Integer maxSalary;

    @Id
    @Column(name = "`JOB_ID`", nullable = false, length = 10)
    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Column(name = "`JOB_TITLE`", nullable = false, length = 35)
    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Column(name = "`MIN_SALARY`", nullable = true, scale = 0, precision = 6)
    public Integer getMinSalary() {
        return this.minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    @Column(name = "`MAX_SALARY`", nullable = true, scale = 0, precision = 6)
    public Integer getMaxSalary() {
        return this.maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jobs)) return false;
        final Jobs jobs = (Jobs) o;
        return Objects.equals(getJobId(), jobs.getJobId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJobId());
    }
}