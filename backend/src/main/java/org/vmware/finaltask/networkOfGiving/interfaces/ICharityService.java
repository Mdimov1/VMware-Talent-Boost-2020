package org.vmware.finaltask.networkOfGiving.interfaces;

import org.vmware.finaltask.networkOfGiving.model.Charity;

import java.io.IOException;
import java.util.List;

public interface ICharityService {

    List<Charity> getAllCharities();

    Charity getCharity(int id);

    void deleteCharity(int id);

    void addCharity(Charity charity) throws IOException;

    List<Charity> getFiteredCharitiesByTitle(String filter);

    String getCharityAuthorName(int charity_id);
}