package com.mininglamp.uums.db.model;

public class TenantProductRelationDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant_product_relation.tenant_product_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    private Integer tenantProductId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant_product_relation.tenant_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    private Integer tenantId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tenant_product_relation.product_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    private Integer productId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant_product_relation.tenant_product_id
     *
     * @return the value of tenant_product_relation.tenant_product_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public Integer getTenantProductId() {
        return tenantProductId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant_product_relation.tenant_product_id
     *
     * @param tenantProductId the value for tenant_product_relation.tenant_product_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public void setTenantProductId(Integer tenantProductId) {
        this.tenantProductId = tenantProductId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant_product_relation.tenant_id
     *
     * @return the value of tenant_product_relation.tenant_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public Integer getTenantId() {
        return tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant_product_relation.tenant_id
     *
     * @param tenantId the value for tenant_product_relation.tenant_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tenant_product_relation.product_id
     *
     * @return the value of tenant_product_relation.product_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tenant_product_relation.product_id
     *
     * @param productId the value for tenant_product_relation.product_id
     *
     * @mbg.generated Wed Nov 11 10:48:14 CST 2020
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}