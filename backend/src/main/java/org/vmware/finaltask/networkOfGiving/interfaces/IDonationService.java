package org.vmware.finaltask.networkOfGiving.interfaces;

public interface IDonationService {
    void donate(int charity_id, int donateAmount);

    int getDonatedAmount(int charity_id);

    int getDonationRequired(int charity_id);
}
