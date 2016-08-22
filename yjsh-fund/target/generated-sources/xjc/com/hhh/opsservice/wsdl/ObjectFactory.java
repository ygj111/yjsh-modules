//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.06.14 时间 01:57:39 PM CST 
//


package com.hhh.opsservice.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hhh.opsservice.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetDeploymentInfo_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "getDeploymentInfo");
    private final static QName _GetDeploymentInfoResponse_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "getDeploymentInfoResponse");
    private final static QName _GetProductInfo_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "getProductInfo");
    private final static QName _GetProductInfoResponse_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "getProductInfoResponse");
    private final static QName _GetSystemException_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "getSystemException");
    private final static QName _GetSystemExceptionForWeek_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "getSystemExceptionForWeek");
    private final static QName _GetSystemExceptionForWeekResponse_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "getSystemExceptionForWeekResponse");
    private final static QName _GetSystemExceptionResponse_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "getSystemExceptionResponse");
    private final static QName _WriteLogException_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "writeLogException");
    private final static QName _WriteLogExceptionResponse_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "writeLogExceptionResponse");
    private final static QName _WriteLogLogin_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "writeLogLogin");
    private final static QName _WriteLogLoginResponse_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "writeLogLoginResponse");
    private final static QName _WriteLogOperation_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "writeLogOperation");
    private final static QName _WriteLogOperationResponse_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "writeLogOperationResponse");
    private final static QName _WriteLogPerformance_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "writeLogPerformance");
    private final static QName _WriteLogPerformanceResponse_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "writeLogPerformanceResponse");
    private final static QName _WriteLogRun_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "writeLogRun");
    private final static QName _WriteLogRunResponse_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "writeLogRunResponse");
    private final static QName _WriteServerRun_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "writeServerRun");
    private final static QName _WriteServerRunResponse_QNAME = new QName("http://webservice.ops.platform.hhh.com/", "writeServerRunResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hhh.opsservice.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDeploymentInfo }
     * 
     */
    public GetDeploymentInfo createGetDeploymentInfo() {
        return new GetDeploymentInfo();
    }

    /**
     * Create an instance of {@link GetDeploymentInfoResponse }
     * 
     */
    public GetDeploymentInfoResponse createGetDeploymentInfoResponse() {
        return new GetDeploymentInfoResponse();
    }

    /**
     * Create an instance of {@link GetProductInfo }
     * 
     */
    public GetProductInfo createGetProductInfo() {
        return new GetProductInfo();
    }

    /**
     * Create an instance of {@link GetProductInfoResponse }
     * 
     */
    public GetProductInfoResponse createGetProductInfoResponse() {
        return new GetProductInfoResponse();
    }

    /**
     * Create an instance of {@link GetSystemException }
     * 
     */
    public GetSystemException createGetSystemException() {
        return new GetSystemException();
    }

    /**
     * Create an instance of {@link GetSystemExceptionForWeek }
     * 
     */
    public GetSystemExceptionForWeek createGetSystemExceptionForWeek() {
        return new GetSystemExceptionForWeek();
    }

    /**
     * Create an instance of {@link GetSystemExceptionForWeekResponse }
     * 
     */
    public GetSystemExceptionForWeekResponse createGetSystemExceptionForWeekResponse() {
        return new GetSystemExceptionForWeekResponse();
    }

    /**
     * Create an instance of {@link GetSystemExceptionResponse }
     * 
     */
    public GetSystemExceptionResponse createGetSystemExceptionResponse() {
        return new GetSystemExceptionResponse();
    }

    /**
     * Create an instance of {@link WriteLogException }
     * 
     */
    public WriteLogException createWriteLogException() {
        return new WriteLogException();
    }

    /**
     * Create an instance of {@link WriteLogExceptionResponse }
     * 
     */
    public WriteLogExceptionResponse createWriteLogExceptionResponse() {
        return new WriteLogExceptionResponse();
    }

    /**
     * Create an instance of {@link WriteLogLogin }
     * 
     */
    public WriteLogLogin createWriteLogLogin() {
        return new WriteLogLogin();
    }

    /**
     * Create an instance of {@link WriteLogLoginResponse }
     * 
     */
    public WriteLogLoginResponse createWriteLogLoginResponse() {
        return new WriteLogLoginResponse();
    }

    /**
     * Create an instance of {@link WriteLogOperation }
     * 
     */
    public WriteLogOperation createWriteLogOperation() {
        return new WriteLogOperation();
    }

    /**
     * Create an instance of {@link WriteLogOperationResponse }
     * 
     */
    public WriteLogOperationResponse createWriteLogOperationResponse() {
        return new WriteLogOperationResponse();
    }

    /**
     * Create an instance of {@link WriteLogPerformance }
     * 
     */
    public WriteLogPerformance createWriteLogPerformance() {
        return new WriteLogPerformance();
    }

    /**
     * Create an instance of {@link WriteLogPerformanceResponse }
     * 
     */
    public WriteLogPerformanceResponse createWriteLogPerformanceResponse() {
        return new WriteLogPerformanceResponse();
    }

    /**
     * Create an instance of {@link WriteLogRun }
     * 
     */
    public WriteLogRun createWriteLogRun() {
        return new WriteLogRun();
    }

    /**
     * Create an instance of {@link WriteLogRunResponse }
     * 
     */
    public WriteLogRunResponse createWriteLogRunResponse() {
        return new WriteLogRunResponse();
    }

    /**
     * Create an instance of {@link WriteServerRun }
     * 
     */
    public WriteServerRun createWriteServerRun() {
        return new WriteServerRun();
    }

    /**
     * Create an instance of {@link WriteServerRunResponse }
     * 
     */
    public WriteServerRunResponse createWriteServerRunResponse() {
        return new WriteServerRunResponse();
    }

    /**
     * Create an instance of {@link DeploymentInfoModel }
     * 
     */
    public DeploymentInfoModel createDeploymentInfoModel() {
        return new DeploymentInfoModel();
    }

    /**
     * Create an instance of {@link SystemInfoModel }
     * 
     */
    public SystemInfoModel createSystemInfoModel() {
        return new SystemInfoModel();
    }

    /**
     * Create an instance of {@link ServersInfoModel }
     * 
     */
    public ServersInfoModel createServersInfoModel() {
        return new ServersInfoModel();
    }

    /**
     * Create an instance of {@link ProductInfoModel }
     * 
     */
    public ProductInfoModel createProductInfoModel() {
        return new ProductInfoModel();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDeploymentInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "getDeploymentInfo")
    public JAXBElement<GetDeploymentInfo> createGetDeploymentInfo(GetDeploymentInfo value) {
        return new JAXBElement<GetDeploymentInfo>(_GetDeploymentInfo_QNAME, GetDeploymentInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDeploymentInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "getDeploymentInfoResponse")
    public JAXBElement<GetDeploymentInfoResponse> createGetDeploymentInfoResponse(GetDeploymentInfoResponse value) {
        return new JAXBElement<GetDeploymentInfoResponse>(_GetDeploymentInfoResponse_QNAME, GetDeploymentInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "getProductInfo")
    public JAXBElement<GetProductInfo> createGetProductInfo(GetProductInfo value) {
        return new JAXBElement<GetProductInfo>(_GetProductInfo_QNAME, GetProductInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "getProductInfoResponse")
    public JAXBElement<GetProductInfoResponse> createGetProductInfoResponse(GetProductInfoResponse value) {
        return new JAXBElement<GetProductInfoResponse>(_GetProductInfoResponse_QNAME, GetProductInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSystemException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "getSystemException")
    public JAXBElement<GetSystemException> createGetSystemException(GetSystemException value) {
        return new JAXBElement<GetSystemException>(_GetSystemException_QNAME, GetSystemException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSystemExceptionForWeek }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "getSystemExceptionForWeek")
    public JAXBElement<GetSystemExceptionForWeek> createGetSystemExceptionForWeek(GetSystemExceptionForWeek value) {
        return new JAXBElement<GetSystemExceptionForWeek>(_GetSystemExceptionForWeek_QNAME, GetSystemExceptionForWeek.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSystemExceptionForWeekResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "getSystemExceptionForWeekResponse")
    public JAXBElement<GetSystemExceptionForWeekResponse> createGetSystemExceptionForWeekResponse(GetSystemExceptionForWeekResponse value) {
        return new JAXBElement<GetSystemExceptionForWeekResponse>(_GetSystemExceptionForWeekResponse_QNAME, GetSystemExceptionForWeekResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSystemExceptionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "getSystemExceptionResponse")
    public JAXBElement<GetSystemExceptionResponse> createGetSystemExceptionResponse(GetSystemExceptionResponse value) {
        return new JAXBElement<GetSystemExceptionResponse>(_GetSystemExceptionResponse_QNAME, GetSystemExceptionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteLogException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "writeLogException")
    public JAXBElement<WriteLogException> createWriteLogException(WriteLogException value) {
        return new JAXBElement<WriteLogException>(_WriteLogException_QNAME, WriteLogException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteLogExceptionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "writeLogExceptionResponse")
    public JAXBElement<WriteLogExceptionResponse> createWriteLogExceptionResponse(WriteLogExceptionResponse value) {
        return new JAXBElement<WriteLogExceptionResponse>(_WriteLogExceptionResponse_QNAME, WriteLogExceptionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteLogLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "writeLogLogin")
    public JAXBElement<WriteLogLogin> createWriteLogLogin(WriteLogLogin value) {
        return new JAXBElement<WriteLogLogin>(_WriteLogLogin_QNAME, WriteLogLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteLogLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "writeLogLoginResponse")
    public JAXBElement<WriteLogLoginResponse> createWriteLogLoginResponse(WriteLogLoginResponse value) {
        return new JAXBElement<WriteLogLoginResponse>(_WriteLogLoginResponse_QNAME, WriteLogLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteLogOperation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "writeLogOperation")
    public JAXBElement<WriteLogOperation> createWriteLogOperation(WriteLogOperation value) {
        return new JAXBElement<WriteLogOperation>(_WriteLogOperation_QNAME, WriteLogOperation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteLogOperationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "writeLogOperationResponse")
    public JAXBElement<WriteLogOperationResponse> createWriteLogOperationResponse(WriteLogOperationResponse value) {
        return new JAXBElement<WriteLogOperationResponse>(_WriteLogOperationResponse_QNAME, WriteLogOperationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteLogPerformance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "writeLogPerformance")
    public JAXBElement<WriteLogPerformance> createWriteLogPerformance(WriteLogPerformance value) {
        return new JAXBElement<WriteLogPerformance>(_WriteLogPerformance_QNAME, WriteLogPerformance.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteLogPerformanceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "writeLogPerformanceResponse")
    public JAXBElement<WriteLogPerformanceResponse> createWriteLogPerformanceResponse(WriteLogPerformanceResponse value) {
        return new JAXBElement<WriteLogPerformanceResponse>(_WriteLogPerformanceResponse_QNAME, WriteLogPerformanceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteLogRun }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "writeLogRun")
    public JAXBElement<WriteLogRun> createWriteLogRun(WriteLogRun value) {
        return new JAXBElement<WriteLogRun>(_WriteLogRun_QNAME, WriteLogRun.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteLogRunResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "writeLogRunResponse")
    public JAXBElement<WriteLogRunResponse> createWriteLogRunResponse(WriteLogRunResponse value) {
        return new JAXBElement<WriteLogRunResponse>(_WriteLogRunResponse_QNAME, WriteLogRunResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteServerRun }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "writeServerRun")
    public JAXBElement<WriteServerRun> createWriteServerRun(WriteServerRun value) {
        return new JAXBElement<WriteServerRun>(_WriteServerRun_QNAME, WriteServerRun.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteServerRunResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ops.platform.hhh.com/", name = "writeServerRunResponse")
    public JAXBElement<WriteServerRunResponse> createWriteServerRunResponse(WriteServerRunResponse value) {
        return new JAXBElement<WriteServerRunResponse>(_WriteServerRunResponse_QNAME, WriteServerRunResponse.class, null, value);
    }

}
