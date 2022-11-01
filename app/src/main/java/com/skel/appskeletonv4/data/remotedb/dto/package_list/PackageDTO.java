
package com.skel.appskeletonv4.data.remotedb.dto.package_list;

import com.skel.appskeletonv4.data.remotedb.dto.servicelist.ServiceItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PackageDTO {

    @SerializedName("package_id")
    @Expose
    private Integer packageId;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("package_rate")
    @Expose
    private Integer packageRate;
    @SerializedName("package_expiry")
    @Expose
    private Integer packageExpiry;
    @SerializedName("package_status")
    @Expose
    private String packageStatus;
    @SerializedName("service_details")
    @Expose
    private List<ServiceItem> serviceDetails = null;

    private Boolean isSelected;

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getPackageRate() {
        return packageRate;
    }

    public void setPackageRate(Integer packageRate) {
        this.packageRate = packageRate;
    }

    public Integer getPackageExpiry() {
        return packageExpiry;
    }

    public void setPackageExpiry(Integer packageExpiry) {
        this.packageExpiry = packageExpiry;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
    }

    public List<ServiceItem> getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(List<ServiceItem> serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
