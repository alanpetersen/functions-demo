/**
 * @description       : 
 * @author            : AlanPetersen
 * @group             : 
 * @last modified on  : 01-16-2022
 * @last modified by  : AlanPetersen
**/
trigger ContentVersionTrigger on ContentVersion (before insert, after insert, before update, after update) {

    if(Trigger.isAfter && Trigger.isInsert) {
        ContentVersionTriggerHandler.handleAfterInsert(Trigger.new);
    }

}