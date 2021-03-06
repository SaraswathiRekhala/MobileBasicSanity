/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.mobilebasicsanity.wmstudio;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

public class SampleTableId implements Serializable {

    private String column2;
    private String column3;

    public String getColumn2() {
        return this.column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return this.column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SampleTable)) return false;
        final SampleTable sampleTable = (SampleTable) o;
        return Objects.equals(getColumn2(), sampleTable.getColumn2()) &&
                Objects.equals(getColumn3(), sampleTable.getColumn3());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn2(),
                getColumn3());
    }
}