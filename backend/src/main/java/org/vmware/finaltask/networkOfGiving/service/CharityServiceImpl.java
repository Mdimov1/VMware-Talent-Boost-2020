package org.vmware.finaltask.networkOfGiving.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vmware.finaltask.networkOfGiving.model.Charity;
import org.vmware.finaltask.networkOfGiving.interfaces.ICharityService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CharityServiceImpl implements ICharityService{
    private static final String CHARITY_IMAGE_PATH = "http://localhost:8080/charityImages/";

    ICharityService iCharityService;

    @Autowired
    public CharityServiceImpl(ICharityService iCharityService) {
        this.iCharityService = iCharityService;
    }

    @Override
    public List<Charity> getAllCharities() {
        List<Charity> allCharities = this.iCharityService.getAllCharities();
        Charity current = null;

        for (int i = 0; i < allCharities.size(); i++) {
            current = allCharities.get(i);
            if(!current.getThumbnail_name().startsWith("http")){
                current.setThumbnail_name(CHARITY_IMAGE_PATH + current.getThumbnail_name());
                allCharities.set(i,current);
            }
        }
        return allCharities;
    }

    @Override
    public Charity getCharity(int id) {

        Charity charity = iCharityService.getCharity(id);

        if(!charity.getThumbnail_name().startsWith("http")) {
            charity.setThumbnail_name(CHARITY_IMAGE_PATH + charity.getThumbnail_name());
        }
        return charity;
    }

    @Override
    public void deleteCharity(int id) {
        iCharityService.deleteCharity(id);
    }

    @Override
    public void addCharity(Charity charity) throws IOException {
        iCharityService.addCharity(charity);
    }

    @Override
    public List<Charity> getFiteredCharitiesByTitle(String filter) {
        return getAllCharities().stream().filter(
                charity -> {
                    return charity.getName().toLowerCase().contains(filter.toLowerCase());
                }).collect(Collectors.toList());
    }

    @Override
    public String getCharityAuthorName(int charity_id) {
        return iCharityService.getCharityAuthorName(charity_id);
    }

}