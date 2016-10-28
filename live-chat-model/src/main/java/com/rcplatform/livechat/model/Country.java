package com.rcplatform.livechat.model;

import com.bj58.spat.gaea.serializer.component.annotation.GaeaMember;
import com.bj58.spat.gaea.serializer.component.annotation.GaeaSerializable;

import java.io.Serializable;

@Table(name = "rc_country")

public class Country implements Serializable {
    @Id

    private Integer id;

    @Column(name = "country_code")

    private Integer countryCode;

    @Column(name = "country_name_en")

    private String countryNameEn;

    @Column(name = "country_name_cn")

    private String countryNameCn;


    private String ab;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return country_code
     */
    public Integer getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode
     */
    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return country_name_en
     */
    public String getCountryNameEn() {
        return countryNameEn;
    }

    /**
     * @param countryNameEn
     */
    public void setCountryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    /**
     * @return country_name_cn
     */
    public String getCountryNameCn() {
        return countryNameCn;
    }

    /**
     * @param countryNameCn
     */
    public void setCountryNameCn(String countryNameCn) {
        this.countryNameCn = countryNameCn;
    }

    /**
     * @return ab
     */
    public String getAb() {
        return ab;
    }

    /**
     * @param ab
     */
    public void setAb(String ab) {
        this.ab = ab;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", countryNameEn=").append(countryNameEn);
        sb.append(", countryNameCn=").append(countryNameCn);
        sb.append(", ab=").append(ab);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}