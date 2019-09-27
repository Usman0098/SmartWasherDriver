
package com.smartwashr.driver.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("email")
  @Expose
  private String email;
  @SerializedName("activation_code")
  @Expose
  private Object activationCode;
  @SerializedName("active")
  @Expose
  private String active;
  @SerializedName("phone_number")
  @Expose
  private String phoneNumber;
  @SerializedName("profile_pic")
  @Expose
  private String profilePic;
  @SerializedName("is_admin")
  @Expose
  private Integer isAdmin;
  @SerializedName("st1")
  @Expose
  private Object st1;
  @SerializedName("st2")
  @Expose
  private Object st2;
  @SerializedName("city")
  @Expose
  private Object city;
  @SerializedName("state/province")
  @Expose
  private Object stateProvince;
  @SerializedName("zip")
  @Expose
  private Object zip;
  @SerializedName("country")
  @Expose
  private Object country;
  @SerializedName("latitude")
  @Expose
  private Object latitude;
  @SerializedName("longitude")
  @Expose
  private Object longitude;
  @SerializedName("created_at")
  @Expose
  private Object createdAt;
  @SerializedName("updated_at")
  @Expose
  private String updatedAt;
  @SerializedName("deleted_at")
  @Expose
  private Object deletedAt;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Object getActivationCode() {
    return activationCode;
  }

  public void setActivationCode(Object activationCode) {
    this.activationCode = activationCode;
  }

  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getProfilePic() {
    return profilePic;
  }

  public void setProfilePic(String profilePic) {
    this.profilePic = profilePic;
  }

  public Integer getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Integer isAdmin) {
    this.isAdmin = isAdmin;
  }

  public Object getSt1() {
    return st1;
  }

  public void setSt1(Object st1) {
    this.st1 = st1;
  }

  public Object getSt2() {
    return st2;
  }

  public void setSt2(Object st2) {
    this.st2 = st2;
  }

  public Object getCity() {
    return city;
  }

  public void setCity(Object city) {
    this.city = city;
  }

  public Object getStateProvince() {
    return stateProvince;
  }

  public void setStateProvince(Object stateProvince) {
    this.stateProvince = stateProvince;
  }

  public Object getZip() {
    return zip;
  }

  public void setZip(Object zip) {
    this.zip = zip;
  }

  public Object getCountry() {
    return country;
  }

  public void setCountry(Object country) {
    this.country = country;
  }

  public Object getLatitude() {
    return latitude;
  }

  public void setLatitude(Object latitude) {
    this.latitude = latitude;
  }

  public Object getLongitude() {
    return longitude;
  }

  public void setLongitude(Object longitude) {
    this.longitude = longitude;
  }

  public Object getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Object createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Object getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Object deletedAt) {
    this.deletedAt = deletedAt;
  }

}
