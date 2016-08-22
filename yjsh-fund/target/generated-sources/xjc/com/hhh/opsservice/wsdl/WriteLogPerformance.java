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
 * <p>writeLogPerformance complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="writeLogPerformance"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dp_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="login_user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="module" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cost_time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "writeLogPerformance", propOrder = {
    "dpCode",
    "loginUser",
    "module",
    "costTime",
    "content"
})
@XmlRootElement(name="writeLogPerformance")
public class WriteLogPerformance {

    @XmlElement(name = "dp_code", namespace = "http://webservice.ops.platform.hhh.com/")
    protected String dpCode;
    @XmlElement(name = "login_user", namespace = "http://webservice.ops.platform.hhh.com/")
    protected String loginUser;
    @XmlElement(namespace = "http://webservice.ops.platform.hhh.com/")
    protected String module;
    @XmlElement(name = "cost_time", namespace = "http://webservice.ops.platform.hhh.com/")
    protected String costTime;
    @XmlElement(namespace = "http://webservice.ops.platform.hhh.com/")
    protected String content;

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
     * 获取loginUser属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginUser() {
        return loginUser;
    }

    /**
     * 设置loginUser属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginUser(String value) {
        this.loginUser = value;
    }

    /**
     * 获取module属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModule() {
        return module;
    }

    /**
     * 设置module属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModule(String value) {
        this.module = value;
    }

    /**
     * 获取costTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostTime() {
        return costTime;
    }

    /**
     * 设置costTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostTime(String value) {
        this.costTime = value;
    }

    /**
     * 获取content属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置content属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

}
