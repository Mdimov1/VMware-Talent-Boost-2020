package org.vmware.finaltask.networkOfGiving.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vmware.finaltask.networkOfGiving.repo.CharityRepository;
import org.vmware.finaltask.networkOfGiving.repo.ParticipantsRepository;
import org.vmware.finaltask.networkOfGiving.interfaces.IParticipantsService;

@Service
public class ParticipantsService implements IParticipantsService {

    ParticipantsRepository participantsRepository;
    CharityRepository charityRepository;

    @Autowired
    public ParticipantsService(ParticipantsRepository participantsRepository, CharityRepository charityRepository) {
        this.participantsRepository = participantsRepository;
        this.charityRepository = charityRepository;
    }

    @Override
    public void addParticipantInCharity(int charity_id, int user_id) {
        if (charity_id <= 0  || user_id <= 0) {
            throw new IllegalArgumentException("Invalid inputs!");
        }

        int count = getCountParticipant(charity_id, user_id);

        if(count > 0){
            throw new IllegalArgumentException("Participant already exists in this charity!");
        }
        participantsRepository.addParticipantInCharity(charity_id, user_id);
    }

    @Override
    public int getCountOfParticipantsInCharity(int charity_id) {
        return participantsRepository.getCountOfParticipantsInCharity(charity_id);
    }

    @Override
    public int getCountParticipant(int charity_id, int user_id) {
        return participantsRepository.getCountParticipant(charity_id, user_id);
    }
}
