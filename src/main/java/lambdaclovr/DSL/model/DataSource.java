/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lambdaclovr.dsl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anwar Abir
 */

@XmlRootElement(name = "ds")
@XmlAccessorType(XmlAccessType.FIELD)

public class DataSource {

    private int dsId = 0;
    private int userId = 0;
    private int vendorModelId = 0;
    private int dsTypeId = 0;
    private String dsName = "";
    private String description = "";
    private String cameraUserName = "";
    private String cameraPassword = "";
    private String cameraPort = "";
    private String cameraLink = "";
    private String cameraSnapshot = "";
    private String creationDate = "";

    public int getDsId() {
        return dsId;
    }

    public DataSource setDsId(String dsId) {
        this.dsId = Integer.parseInt(dsId);
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public DataSource setUserId(String userId) {
        this.userId = Integer.parseInt(userId);
        return this;
    }

    public int getVendorModelId() {
        return vendorModelId;
    }

    public DataSource setVendorModelId(String vendorModelId) {
        this.vendorModelId = Integer.parseInt(vendorModelId);
        return this;
    }

    public int getDsTypeId() {
        return dsTypeId;
    }

    public DataSource setDsTypeId(String dsTypeId) {
        this.dsTypeId = Integer.parseInt(dsTypeId);
        return this;
    }

    public String getDsName() {
        return dsName;
    }

    public DataSource setDsName(String dsName) {
        this.dsName = dsName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DataSource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCameraUserName() {
        return cameraUserName;
    }

    public DataSource setCameraUserName(String cameraUserName) {
        this.cameraUserName = cameraUserName;
        return this;
    }

    public String getCameraPassword() {
        return cameraPassword;
    }

    public DataSource setCameraPassword(String cameraPassword) {
        this.cameraPassword = cameraPassword;
        return this;
    }

    public String getCameraPort() {
        return cameraPort;
    }

    public DataSource setCameraPort(String cameraPort) {
        this.cameraPort = cameraPort;
        return this;
    }

    public String getCameraLink() {
        return cameraLink;
    }

    public DataSource setCameraLink(String cameraLink) {
        this.cameraLink = cameraLink;
        return this;
    }

    public String getCameraSnapshot() {
        return cameraSnapshot;
    }

    public DataSource setCameraSnapshot(String cameraSnapshot) {
        this.cameraSnapshot = cameraSnapshot;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public DataSource setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

}
