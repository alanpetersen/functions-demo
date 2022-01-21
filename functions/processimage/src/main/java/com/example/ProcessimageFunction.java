package com.example;

import com.salesforce.functions.jvm.sdk.Context;
import com.salesforce.functions.jvm.sdk.InvocationEvent;
import com.salesforce.functions.jvm.sdk.SalesforceFunction;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata.GPSInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Base64;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Describe ProcessimageFunction here.
 */
public class ProcessimageFunction implements SalesforceFunction<FunctionInput, FunctionOutput> {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessimageFunction.class);
  private static final String REWRITE_REASON = "Removed EXIF data";

  @Override
  public FunctionOutput apply(InvocationEvent<FunctionInput> event, Context context) throws Exception {

    LOGGER.info("### in ProcessimageFunction.apply()");

    FunctionOutput output = null;

    try {

      String id = event.getData().getId();
      String title = event.getData().getTitle();
      String contentDocumentId = event.getData().getContentDocumentId();
      String pathOnClient = event.getData().getPathOnClient();
      String versionData = event.getData().getVersionData();
      Boolean isMajorVersion = event.getData().getIsMajorVersion();

      System.out.println("DATA:");
      System.out.println("   ID: " + id);
      System.out.println("   TITLE: " + title);
      System.out.println("   CONTENTDOCUMENTID: " + contentDocumentId);
      System.out.println("   PATHONCLIENT: " + pathOnClient);
      System.out.println("   VERSIONDATA: " + versionData.substring(0, 15) + "...");
      System.out.println("   ISMAJORVERSION: " + isMajorVersion);
     
      //byte[] imageBytes = Base64.getDecoder().decode(versionData);
      byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(versionData);
      GPSInfo gpsInfo = getGPSInfo(imageBytes);
      versionData = removeExifAndEncode(imageBytes);
      double latitude = gpsInfo != null ? gpsInfo.getLatitudeAsDegreesNorth() : 0.0;
      double longitude = gpsInfo != null ? gpsInfo.getLongitudeAsDegreesEast() : 0.0;
      output = new FunctionOutput(
        contentDocumentId,
        title,
        pathOnClient,
        REWRITE_REASON,
        versionData,
        isMajorVersion,
        latitude,
        longitude
      );
      
    } catch (Exception ex) {
      LOGGER.error("##### CAUGHT EXCEPTION");
      ex.printStackTrace();
    }

    return output;
  }

  private String removeExifAndEncode(byte[] imageBytes) throws ImageReadException, ImageWriteException, IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    new ExifRewriter().removeExifMetadata(imageBytes, baos);
    return Base64.getEncoder().encodeToString(baos.toByteArray());
  }

  private GPSInfo getGPSInfo(byte[] imageBytes) throws ImageReadException,IOException {
    System.out.println("### in getGPSInfo()");
    GPSInfo gpsInfo = null;
    JpegImageMetadata metadata = (JpegImageMetadata) Imaging.getMetadata(imageBytes);
    if(metadata != null) {
      TiffImageMetadata exifData = metadata.getExif();
      if(exifData != null) {
        gpsInfo = exifData.getGPS();
      }
    }
    System.out.println("--- gpsInfo: " + gpsInfo);
    return gpsInfo;
  }

  private void addMessage(byte[] imageBytes, String message) throws IOException {
    BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
    Graphics g = img.getGraphics();
    Font font = new Font("Arial", Font.BOLD, 18);
    g.setFont(font);
    g.setColor(Color.GREEN);
    g.drawString(message, 0, 20);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(img, "jpg", baos);
    imageBytes = baos.toByteArray();
  }

}
