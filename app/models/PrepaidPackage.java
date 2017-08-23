package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dao.PrepaidPackageDAO;
import serializers.MSToRoundHoursSerializer;

import javax.persistence.*;
import java.util.Date;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-23
 */
@Entity
@Table(name = "pacotes")
@NamedQueries(
        @NamedQuery(
                name = PrepaidPackageDAO.FIND_ALL_PACKAGES,
                query = "FROM PrepaidPackage p"
        )
)
public class PrepaidPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(precision = 10, scale = 2)
    private Double price;
    @JsonSerialize(using = MSToRoundHoursSerializer.class)
    private Integer hours;
    @Transient
    private String paypalPaymentURL;
    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
    private Date createdAt;
    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPaypalPaymentURL() {
        return paypalPaymentURL;
    }

    public void setPaypalPaymentURL(String paypalPaymentURL) {
        this.paypalPaymentURL = paypalPaymentURL;
    }
}
