//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.06.14 时间 01:57:39 PM CST 
//


package com.hhh.opsservice.wsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>systemInfoModel complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="systemInfoModel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="appname_1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="appname_2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="appname_3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="appname_4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="appname_5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="count_1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="count_2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="count_3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="count_4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="count_5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="datatime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="dp_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="dp_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="exception_num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="operation_1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="operation_2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="operation_3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="operation_4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="operation_5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="port_exception_num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="serversInfoModel" type="{http://webservice.ops.platform.hhh.com/}serversInfoModel" maxOccurs="unbounded" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum_chrome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum_count" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum_firefox" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum_ie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum_mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum_opera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum_other" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum_pc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum_safari" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="sum_user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "systemInfoModel", propOrder = {
    "appname1",
    "appname2",
    "appname3",
    "appname4",
    "appname5",
    "count1",
    "count2",
    "count3",
    "count4",
    "count5",
    "datatime",
    "dpId",
    "dpName",
    "exceptionNum",
    "operation1",
    "operation2",
    "operation3",
    "operation4",
    "operation5",
    "portExceptionNum",
    "serversInfoModel",
    "sum1",
    "sum2",
    "sum3",
    "sum4",
    "sum5",
    "sumChrome",
    "sumCount",
    "sumFirefox",
    "sumIe",
    "sumMobile",
    "sumOpera",
    "sumOther",
    "sumPc",
    "sumSafari",
    "sumUser"
})
public class SystemInfoModel {

    @XmlElement(name = "appname_1")
    protected String appname1;
    @XmlElement(name = "appname_2")
    protected String appname2;
    @XmlElement(name = "appname_3")
    protected String appname3;
    @XmlElement(name = "appname_4")
    protected String appname4;
    @XmlElement(name = "appname_5")
    protected String appname5;
    @XmlElement(name = "count_1")
    protected String count1;
    @XmlElement(name = "count_2")
    protected String count2;
    @XmlElement(name = "count_3")
    protected String count3;
    @XmlElement(name = "count_4")
    protected String count4;
    @XmlElement(name = "count_5")
    protected String count5;
    protected String datatime;
    @XmlElement(name = "dp_id")
    protected String dpId;
    @XmlElement(name = "dp_name")
    protected String dpName;
    @XmlElement(name = "exception_num")
    protected String exceptionNum;
    @XmlElement(name = "operation_1")
    protected String operation1;
    @XmlElement(name = "operation_2")
    protected String operation2;
    @XmlElement(name = "operation_3")
    protected String operation3;
    @XmlElement(name = "operation_4")
    protected String operation4;
    @XmlElement(name = "operation_5")
    protected String operation5;
    @XmlElement(name = "port_exception_num")
    protected String portExceptionNum;
    @XmlElement(nillable = true)
    protected List<ServersInfoModel> serversInfoModel;
    protected String sum1;
    protected String sum2;
    protected String sum3;
    protected String sum4;
    protected String sum5;
    @XmlElement(name = "sum_chrome")
    protected String sumChrome;
    @XmlElement(name = "sum_count")
    protected String sumCount;
    @XmlElement(name = "sum_firefox")
    protected String sumFirefox;
    @XmlElement(name = "sum_ie")
    protected String sumIe;
    @XmlElement(name = "sum_mobile")
    protected String sumMobile;
    @XmlElement(name = "sum_opera")
    protected String sumOpera;
    @XmlElement(name = "sum_other")
    protected String sumOther;
    @XmlElement(name = "sum_pc")
    protected String sumPc;
    @XmlElement(name = "sum_safari")
    protected String sumSafari;
    @XmlElement(name = "sum_user")
    protected String sumUser;

    /**
     * 获取appname1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppname1() {
        return appname1;
    }

    /**
     * 设置appname1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppname1(String value) {
        this.appname1 = value;
    }

    /**
     * 获取appname2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppname2() {
        return appname2;
    }

    /**
     * 设置appname2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppname2(String value) {
        this.appname2 = value;
    }

    /**
     * 获取appname3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppname3() {
        return appname3;
    }

    /**
     * 设置appname3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppname3(String value) {
        this.appname3 = value;
    }

    /**
     * 获取appname4属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppname4() {
        return appname4;
    }

    /**
     * 设置appname4属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppname4(String value) {
        this.appname4 = value;
    }

    /**
     * 获取appname5属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppname5() {
        return appname5;
    }

    /**
     * 设置appname5属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppname5(String value) {
        this.appname5 = value;
    }

    /**
     * 获取count1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCount1() {
        return count1;
    }

    /**
     * 设置count1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCount1(String value) {
        this.count1 = value;
    }

    /**
     * 获取count2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCount2() {
        return count2;
    }

    /**
     * 设置count2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCount2(String value) {
        this.count2 = value;
    }

    /**
     * 获取count3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCount3() {
        return count3;
    }

    /**
     * 设置count3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCount3(String value) {
        this.count3 = value;
    }

    /**
     * 获取count4属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCount4() {
        return count4;
    }

    /**
     * 设置count4属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCount4(String value) {
        this.count4 = value;
    }

    /**
     * 获取count5属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCount5() {
        return count5;
    }

    /**
     * 设置count5属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCount5(String value) {
        this.count5 = value;
    }

    /**
     * 获取datatime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatatime() {
        return datatime;
    }

    /**
     * 设置datatime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatatime(String value) {
        this.datatime = value;
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
     * 获取exceptionNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionNum() {
        return exceptionNum;
    }

    /**
     * 设置exceptionNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionNum(String value) {
        this.exceptionNum = value;
    }

    /**
     * 获取operation1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation1() {
        return operation1;
    }

    /**
     * 设置operation1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation1(String value) {
        this.operation1 = value;
    }

    /**
     * 获取operation2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation2() {
        return operation2;
    }

    /**
     * 设置operation2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation2(String value) {
        this.operation2 = value;
    }

    /**
     * 获取operation3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation3() {
        return operation3;
    }

    /**
     * 设置operation3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation3(String value) {
        this.operation3 = value;
    }

    /**
     * 获取operation4属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation4() {
        return operation4;
    }

    /**
     * 设置operation4属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation4(String value) {
        this.operation4 = value;
    }

    /**
     * 获取operation5属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation5() {
        return operation5;
    }

    /**
     * 设置operation5属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation5(String value) {
        this.operation5 = value;
    }

    /**
     * 获取portExceptionNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortExceptionNum() {
        return portExceptionNum;
    }

    /**
     * 设置portExceptionNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortExceptionNum(String value) {
        this.portExceptionNum = value;
    }

    /**
     * Gets the value of the serversInfoModel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serversInfoModel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServersInfoModel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServersInfoModel }
     * 
     * 
     */
    public List<ServersInfoModel> getServersInfoModel() {
        if (serversInfoModel == null) {
            serversInfoModel = new ArrayList<ServersInfoModel>();
        }
        return this.serversInfoModel;
    }

    /**
     * 获取sum1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSum1() {
        return sum1;
    }

    /**
     * 设置sum1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSum1(String value) {
        this.sum1 = value;
    }

    /**
     * 获取sum2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSum2() {
        return sum2;
    }

    /**
     * 设置sum2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSum2(String value) {
        this.sum2 = value;
    }

    /**
     * 获取sum3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSum3() {
        return sum3;
    }

    /**
     * 设置sum3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSum3(String value) {
        this.sum3 = value;
    }

    /**
     * 获取sum4属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSum4() {
        return sum4;
    }

    /**
     * 设置sum4属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSum4(String value) {
        this.sum4 = value;
    }

    /**
     * 获取sum5属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSum5() {
        return sum5;
    }

    /**
     * 设置sum5属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSum5(String value) {
        this.sum5 = value;
    }

    /**
     * 获取sumChrome属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSumChrome() {
        return sumChrome;
    }

    /**
     * 设置sumChrome属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSumChrome(String value) {
        this.sumChrome = value;
    }

    /**
     * 获取sumCount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSumCount() {
        return sumCount;
    }

    /**
     * 设置sumCount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSumCount(String value) {
        this.sumCount = value;
    }

    /**
     * 获取sumFirefox属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSumFirefox() {
        return sumFirefox;
    }

    /**
     * 设置sumFirefox属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSumFirefox(String value) {
        this.sumFirefox = value;
    }

    /**
     * 获取sumIe属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSumIe() {
        return sumIe;
    }

    /**
     * 设置sumIe属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSumIe(String value) {
        this.sumIe = value;
    }

    /**
     * 获取sumMobile属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSumMobile() {
        return sumMobile;
    }

    /**
     * 设置sumMobile属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSumMobile(String value) {
        this.sumMobile = value;
    }

    /**
     * 获取sumOpera属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSumOpera() {
        return sumOpera;
    }

    /**
     * 设置sumOpera属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSumOpera(String value) {
        this.sumOpera = value;
    }

    /**
     * 获取sumOther属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSumOther() {
        return sumOther;
    }

    /**
     * 设置sumOther属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSumOther(String value) {
        this.sumOther = value;
    }

    /**
     * 获取sumPc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSumPc() {
        return sumPc;
    }

    /**
     * 设置sumPc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSumPc(String value) {
        this.sumPc = value;
    }

    /**
     * 获取sumSafari属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSumSafari() {
        return sumSafari;
    }

    /**
     * 设置sumSafari属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSumSafari(String value) {
        this.sumSafari = value;
    }

    /**
     * 获取sumUser属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSumUser() {
        return sumUser;
    }

    /**
     * 设置sumUser属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSumUser(String value) {
        this.sumUser = value;
    }

}
