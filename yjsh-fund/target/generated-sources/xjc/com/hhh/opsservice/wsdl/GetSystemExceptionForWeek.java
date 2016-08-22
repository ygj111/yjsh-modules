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
 * <p>getSystemExceptionForWeek complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getSystemExceptionForWeek"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dp_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="interval" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSystemExceptionForWeek", propOrder = {
    "dpCode",
    "interval"
})
@XmlRootElement(name="getSystemExceptionForWeek")
public class GetSystemExceptionForWeek {

    @XmlElement(name = "dp_code", namespace = "http://webservice.ops.platform.hhh.com/")
    protected String dpCode;
    @XmlElement(namespace = "http://webservice.ops.platform.hhh.com/")
    protected int interval;

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
     * 获取interval属性的值。
     * 
     */
    public int getInterval() {
        return interval;
    }

    /**
     * 设置interval属性的值。
     * 
     */
    public void setInterval(int value) {
        this.interval = value;
    }

}
