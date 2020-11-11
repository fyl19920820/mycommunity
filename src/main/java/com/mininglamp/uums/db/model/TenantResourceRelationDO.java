package com.mininglamp.uums.db.model;

import java.util.Date;

public class TenantResourceRelationDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant_resource_relation.tenant_resource_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    private Integer tenantResourceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant_resource_relation.tenant_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    private Integer tenantId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant_resource_relation.resource_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    private String resourceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant_resource_relation.resource_type
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    private Byte resourceType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant_resource_relation.sub_type
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    private String subType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant_resource_relation.file_number
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    private Integer fileNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant_resource_relation.store_number
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    private Integer storeNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant_resource_relation.db_name
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    private String dbName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant_resource_relation.description
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant_resource_relation.create_time
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant_resource_relation.tenant_resource_id
     *
     * @return the value of tenant_resource_relation.tenant_resource_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public Integer getTenantResourceId() {
        return tenantResourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant_resource_relation.tenant_resource_id
     *
     * @param tenantResourceId the value for tenant_resource_relation.tenant_resource_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public void setTenantResourceId(Integer tenantResourceId) {
        this.tenantResourceId = tenantResourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant_resource_relation.tenant_id
     *
     * @return the value of tenant_resource_relation.tenant_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public Integer getTenantId() {
        return tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant_resource_relation.tenant_id
     *
     * @param tenantId the value for tenant_resource_relation.tenant_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant_resource_relation.resource_id
     *
     * @return the value of tenant_resource_relation.resource_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant_resource_relation.resource_id
     *
     * @param resourceId the value for tenant_resource_relation.resource_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant_resource_relation.resource_type
     *
     * @return the value of tenant_resource_relation.resource_type
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public Byte getResourceType() {
        return resourceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant_resource_relation.resource_type
     *
     * @param resourceType the value for tenant_resource_relation.resource_type
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public void setResourceType(Byte resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant_resource_relation.sub_type
     *
     * @return the value of tenant_resource_relation.sub_type
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public String getSubType() {
        return subType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant_resource_relation.sub_type
     *
     * @param subType the value for tenant_resource_relation.sub_type
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public void setSubType(String subType) {
        this.subType = subType == null ? null : subType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant_resource_relation.file_number
     *
     * @return the value of tenant_resource_relation.file_number
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public Integer getFileNumber() {
        return fileNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant_resource_relation.file_number
     *
     * @param fileNumber the value for tenant_resource_relation.file_number
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public void setFileNumber(Integer fileNumber) {
        this.fileNumber = fileNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant_resource_relation.store_number
     *
     * @return the value of tenant_resource_relation.store_number
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public Integer getStoreNumber() {
        return storeNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant_resource_relation.store_number
     *
     * @param storeNumber the value for tenant_resource_relation.store_number
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public void setStoreNumber(Integer storeNumber) {
        this.storeNumber = storeNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant_resource_relation.db_name
     *
     * @return the value of tenant_resource_relation.db_name
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant_resource_relation.db_name
     *
     * @param dbName the value for tenant_resource_relation.db_name
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public void setDbName(String dbName) {
        this.dbName = dbName == null ? null : dbName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant_resource_relation.description
     *
     * @return the value of tenant_resource_relation.description
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant_resource_relation.description
     *
     * @param description the value for tenant_resource_relation.description
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant_resource_relation.create_time
     *
     * @return the value of tenant_resource_relation.create_time
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant_resource_relation.create_time
     *
     * @param createTime the value for tenant_resource_relation.create_time
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}