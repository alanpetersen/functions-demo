/**
 * @description       : 
 * @author            : AlanPetersen
 * @group             : 
 * @last modified on  : 01-16-2022
 * @last modified by  : AlanPetersen
**/
public with sharing class ContentVersionTriggerHandler {

    private static final String SKIP_REASON_FOR_CHANGE = 'Removed EXIF data';

    public static void handleAfterInsert(List<ContentVersion> versions) {

        List<Id> insertedJpgIds = new List<Id>();

        for(ContentVersion cv : versions) {
            if(cv.ReasonForChange != SKIP_REASON_FOR_CHANGE && cv.FileType.toUppercase() == 'JPG') {
                insertedJpgIds.add(cv.Id);
            }
        }

        if(insertedJpgIds.size() > 0) {
            ContentVersionTriggerHelper.processJpgImages(insertedJpgIds);
        }
        
    }

}
