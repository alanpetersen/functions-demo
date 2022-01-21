package com.example;
public class FunctionOutput {
  private String contentDocumentId;
  private String title;
  private String pathOnClient;
  private String reasonForChange;
  private String versionData;
  private Boolean isMajorVersion;
  private double latitude;
  private double longitude;

  public FunctionOutput(
    String contentDocumentId,
    String title,
    String pathOnClient,
    String reasonForChange,
    String versionData,
    Boolean isMajorVersion,
    double latitude,
    double longitude) {
        this.contentDocumentId = contentDocumentId;
        this.pathOnClient = pathOnClient;
        this.reasonForChange = reasonForChange;
        this.versionData = versionData;
        this.isMajorVersion = isMajorVersion;
        this.latitude = latitude;
        this.longitude = longitude;
  }

  public FunctionOutput(
    String contentDocumentId,
    String title,
    String pathOnClient,
    String reasonForChange,
    String versionData,
    Boolean isMajorVersion) {
        this.contentDocumentId = contentDocumentId;
        this.pathOnClient = pathOnClient;
        this.reasonForChange = reasonForChange;
        this.versionData = versionData;
        this.isMajorVersion = isMajorVersion;
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

  public String getReasonForChange() {
      return this.reasonForChange;
  }

  public String getVersionData() {
      return this.versionData;
  }

  public Boolean getIsMajorVersion() {
      return this.isMajorVersion;
  }

  public double getLatitude() {
    return this.latitude;
  }

  public double getLongitude() {
    return this.longitude;
  }
}
