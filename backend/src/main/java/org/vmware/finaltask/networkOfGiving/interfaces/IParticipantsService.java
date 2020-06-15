package org.vmware.finaltask.networkOfGiving.interfaces;

public interface IParticipantsService {

    void addParticipantInCharity(int charity_id, int user_id);

    int getCountOfParticipantsInCharity(int charity_id);

    int getCountParticipant(int charity_id, int user_id);
}
