package org.vmware.finaltask.networkOfGiving.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vmware.finaltask.networkOfGiving.interfaces.IDonationService;

@Repository
public class DonationRepository implements IDonationService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DonationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getDonatedAmount(int charity_id) {
        Integer donatedAmount = jdbcTemplate.queryForObject("SELECT SUM(donated_amount) FROM donations WHERE charities_id = ?", Integer.class, charity_id);

        if(donatedAmount != null){
            return donatedAmount;
        }
        return 0;
    }

    @Override
    public int getDonationRequired(int charity_id) {
        Integer donationRequired = jdbcTemplate.queryForObject("SELECT donation_required FROM charities WHERE charities_id = ?", Integer.class, charity_id);

        if(donationRequired != null){
            return donationRequired;
        }
        return 0;
    }

    @Override
    public void donate(int charity_id, int donateAmount) {
        if(charity_id <= 0 || donateAmount <= 0){
            throw new IllegalArgumentException("Invalid inputs!");
        }

        int donationRequired = getDonationRequired(charity_id);
        int currentAmount = getDonatedAmount(charity_id);
        int newAmount = currentAmount + donateAmount;

        if(donationRequired < newAmount){
            throw new IllegalArgumentException("Donation amount is too much!");
        }

        jdbcTemplate.update("INSERT INTO donations(charities_id, donated_amount) VALUE (?,?)", charity_id, donateAmount);
    }
}
