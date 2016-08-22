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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>serversInfoModel complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="serversInfoModel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="add_date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="cpu_model" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="hdd_gb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="os_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="p_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="ram_gb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="rest_store" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="server_model" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="site" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="use_percent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serversInfoModel", propOrder = {
    "addDate",
    "cpuModel",
    "hddGb",
    "osType",
    "pType",
    "ramGb",
    "restStore",
    "serverModel",
    "site",
    "type",
    "usePercent"
})
public class ServersInfoModel {

    @XmlElement(name = "add_date")
    protected String addDate;
    @XmlElement(name = "cpu_model")
    protected String cpuModel;
    @XmlElement(name = "hdd_gb")
    protected String hddGb;
    @XmlElement(name = "os_type")
    protected String osType;
    @XmlElement(name = "p_type")
    protected String pType;
    @XmlElement(name = "ram_gb")
    protected String ramGb;
    @XmlElement(name = "rest_store")
    protected String restStore;
    @XmlElement(name = "server_model")
    protected String serverModel;
    protected String site;
    protected String type;
    @XmlElement(name = "use_percent")
    protected String usePercent;

    /**
     * 获取addDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddDate() {
        return addDate;
    }

    /**
     * 设置addDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddDate(String value) {
        this.addDate = value;
    }

    /**
     * 获取cpuModel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpuModel() {
        return cpuModel;
    }

    /**
     * 设置cpuModel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpuModel(String value) {
        this.cpuModel = value;
    }

    /**
     * 获取hddGb属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHddGb() {
        return hddGb;
    }

    /**
     * 设置hddGb属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHddGb(String value) {
        this.hddGb = value;
    }

    /**
     * 获取osType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOsType() {
        return osType;
    }

    /**
     * 设置osType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOsType(String value) {
        this.osType = value;
    }

    /**
     * 获取pType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPType() {
        return pType;
    }

    /**
     * 设置pType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPType(String value) {
        this.pType = value;
    }

    /**
     * 获取ramGb属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRamGb() {
        return ramGb;
    }

    /**
     * 设置ramGb属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRamGb(String value) {
        this.ramGb = value;
    }

    /**
     * 获取restStore属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestStore() {
        return restStore;
    }

    /**
     * 设置restStore属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestStore(String value) {
        this.restStore = value;
    }

    /**
     * 获取serverModel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerModel() {
        return serverModel;
    }

    /**
     * 设置serverModel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerModel(String value) {
        this.serverModel = value;
    }

    /**
     * 获取site属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSite() {
        return site;
    }

    /**
     * 设置site属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSite(String value) {
        this.site = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * 获取usePercent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsePercent() {
        return usePercent;
    }

    /**
     * 设置usePercent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsePercent(String value) {
        this.usePercent = value;
    }

}
