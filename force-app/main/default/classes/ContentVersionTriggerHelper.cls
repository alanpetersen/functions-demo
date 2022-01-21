/**
 * @description       : 
 * @author            : AlanPetersen
 * @group             : 
 * @last modified on  : 01-21-2022
 * @last modified by  : AlanPetersen
**/
public with sharing class ContentVersionTriggerHelper {

    private static final String PROCESS_IMAGE_FUNCTION = 'FunctionsDemo.processimage';

    @future
    public static void processJpgImages(List<Id> versionIds) {
        
        List<ContentVersion> versionsToCreate = new List<ContentVersion>();

        for(ContentVersion cv : [SELECT Id,Title,PathOnClient,ContentDocumentId,VersionData,IsMajorVersion FROM ContentVersion WHERE Id IN :versionIds]) {
            // create the payload
            Map<String,Object> payload = new Map<String,Object>();
            payload.put('id', cv.Id);
            payload.put('title', cv.Title);
            payload.put('pathOnClient', cv.PathOnClient);
            payload.put('contentDocumentId', cv.ContentDocumentId);
            payload.put('versionData',EncodingUtil.base64Encode(cv.VersionData));
            payload.put('isMajorVersion', cv.IsMajorVersion);

            // call the function
            functions.Function processimage = functions.Function.get(PROCESS_IMAGE_FUNCTION);
            functions.FunctionInvocation invocation = processimage.invoke(JSON.serialize(payload));
            String jsonResponse = invocation.getResponse();

            Map<String,Object> responseMap = (Map<String,Object>)JSON.deserializeUntyped(jsonResponse);

            // create a new version with the returned data
            ContentVersion cv2 = new ContentVersion(
                ContentDocumentId = (String)responseMap.get('contentDocumentId'),
                Title = (String)responseMap.get('title'),
                PathOnClient = (String)responseMap.get('pathOnClient'),
                ReasonForChange = (String)responseMap.get('reasonForChange'),
                VersionData = EncodingUtil.base64Decode((String)responseMap.get('versionData')),
                IsMajorVersion = (Boolean)responseMap.get('isMajorVersion')
            );
            versionsToCreate.add(cv2);
        }

        if(versionsToCreate.size() > 0) {
            insert versionsToCreate;
        }

    }

}
