package com.example.myapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMER_T")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;  // PK - UUID

    @Column(name= "FIRST_NAME")
    String firstName;
    @Column(name= "MIDDLE_NAME")
    String middleName;  // Can be nullable
    @Column(name= "LAST_NAME")
    String lastName;
    @Column(name="EMAIL_ADDRESS", nullable = false, unique = true)
    String emailAddress; // Unique
    @Column(name= "COUNTRY_CODE")
    private String countryCode;
    @Column(name= "AREA_CODE")
    private Integer areaCode;
    @Column(name= "PREFIX")
    private Integer prefix;
    @Column(name= "LINE_NUMBER")
    private Integer lineNumber;

    public Customer() {
    }

    public Customer(Long id, String firstName, String middleName, String lastName, String emailAddress, String countryCode, Integer areaCode, Integer prefix, Integer lineNumber) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNumber = lineNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getPrefix() {
        return prefix;
    }

    public void setPrefix(Integer prefix) {
        this.prefix = prefix;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }
}
