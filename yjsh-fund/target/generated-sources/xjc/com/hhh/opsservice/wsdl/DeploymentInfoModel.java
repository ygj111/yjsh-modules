//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.06.14 时间 01:57:39 PM CST 
//


package com.hhh.opsservice.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>deploymentInfoModel complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="deploymentInfoModel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dp_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="dp_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="dp_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="dp_user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="organization" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="pd_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="re_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deploymentInfoModel", propOrder = {
    "dpCode",
    "dpId",
    "dpName",
    "dpUser",
    "organization",
    "pdCode",
    "reId",
    "url"
})
public class DeploymentInfoModel {

    @XmlElement(name = "dp_code")
    protected String dpCode;
    @XmlElement(name = "dp_id")
    protected String dpId;
    @XmlElement(name = "dp_name")
    protected String dpName;
    @XmlElement(name = "dp_user")
    protected String dpUser;
    protected String organization;
    @XmlElement(name = "pd_code")
    protected String pdCode;
    @XmlElement(name = "re_id")
    protected String reId;
    protected String url;

    /**
     * 获取dpCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDpCode() {
        return dpCode;
    }

    /**
     * 设置dpCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDpCode(String value) {
        this.dpCode = value;
    }

    /**
     * 获取dpId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDpId() {
        return dpId;
    }

    /**
     * 设置dpId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDpId(String value) {
        this.dpId = value;
    }

    /**
     * 获取dpName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDpName() {
        return dpName;
    }

    /**
     * 设置dpName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDpName(String value) {
        this.dpName = value;
    }

    /**
     * 获取dpUser属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDpUser() {
        return dpUser;
    }

    /**
     * 设置dpUser属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDpUser(String value) {
        this.dpUser = value;
    }

    /**
     * 获取organization属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * 设置organization属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganization(String value) {
        this.organization = value;
    }

    /**
     * 获取pdCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * 设置pdCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdCode(String value) {
        this.pdCode = value;
    }

    /**
     * 获取reId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReId() {
        return reId;
    }

    /**
     * 设置reId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReId(String value) {
        this.reId = value;
    }

    /**
     * 获取url属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置url属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

}
