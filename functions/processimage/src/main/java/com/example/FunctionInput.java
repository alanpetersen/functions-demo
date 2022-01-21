package com.example;

public class FunctionInput {

    private String id;
    private String contentDocumentId;
    private String title;
    private String pathOnClient;
    private String versionData;
    private Boolean isMajorVersion;

    public FunctionInput(String id,
        String contentDocumentId,
        String title,
        String pathOnClient,
        String versionData,
        Boolean isMajorVersion) {
        
        System.out.println("### IN FunctionInput()");
        this.id = id;
        this.contentDocumentId = contentDocumentId;
        this.title = title;
        this.pathOnClient = pathOnClient;
        this.versionData = versionData;
        this.isMajorVersion = isMajorVersion;
    }

    public String getId() {
        return this.id;
    }

    public String getContentDocumentId() {
        return this.contentDocumentId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPathOnClient() {
        return this.pathOnClient;
    }

    public String getVersionData() {
        return this.versionData;
    }

    public Boolean getIsMajorVersion() {
        return this.isMajorVersion;
    }
}
