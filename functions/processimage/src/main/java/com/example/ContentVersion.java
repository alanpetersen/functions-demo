package com.example;

public class ContentVersion {

    private String id;
    private String contentDocumentId;
    private String title;
    private String pathOnClient;
    private String reasonForChange;
    private String versionData;
    private Boolean isMajorVersion;

    public ContentVersion(String id,
        String contentDocumentId,
        String title,
        String pathOnClient,
        String reasonForChange,
        String versionData,
        Boolean isMajorVersion) {
            this.id = id;
            this.contentDocumentId = contentDocumentId;
            this.pathOnClient = pathOnClient;
            this.reasonForChange = reasonForChange;
            this.versionData = versionData;
            this.isMajorVersion = isMajorVersion;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getContentDocumentId() {
        return this.contentDocumentId;
    }

    public void setContentDocumentId(String contentDocumentId) {
        this.contentDocumentId = contentDocumentId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPathOnClient() {
        return this.pathOnClient;
    }

    public void setPathOnClient(String pathOnClient) {
        this.pathOnClient = pathOnClient;
    }

    public String getReasonForChange() {
        return this.reasonForChange;
    }

    public void setReasonForChange(String reasonForChange) {
        this.reasonForChange = reasonForChange;
    }

    public String getVersionData() {
        return this.versionData;
    }

    public void setVersionData(String versionData) {
        this.versionData = versionData;
    }

    public Boolean isIsMajorVersion() {
        return this.isMajorVersion;
    }

    public Boolean getIsMajorVersion() {
        return this.isMajorVersion;
    }

    public void setIsMajorVersion(Boolean isMajorVersion) {
        this.isMajorVersion = isMajorVersion;
    }

}