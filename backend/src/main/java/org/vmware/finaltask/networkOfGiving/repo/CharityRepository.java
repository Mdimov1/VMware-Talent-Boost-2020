package org.vmware.finaltask.networkOfGiving.repo;

import org.vmware.finaltask.networkOfGiving.mapper.CharityRowMapper;
import org.vmware.finaltask.networkOfGiving.model.Charity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vmware.finaltask.networkOfGiving.interfaces.ICharityService;

import java.util.List;

@Repository
public class CharityRepository implements ICharityService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CharityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Charity> getAllCharities() {
        return jdbcTemplate.query("SELECT * FROM Charities", new CharityRowMapper());
    }

    @Override
    public Charity getCharity(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Charities WHERE charities_id = ?", new CharityRowMapper(), id);
    }

    @Override
    public void deleteCharity(int id) {
        jdbcTemplate.update("DELETE FROM Charities WHERE charities_id = ?", id);
    }

    @Override
    public void addCharity(Charity charity) {
        jdbcTemplate.update("INSERT INTO Charities (name, thumbnail_name, description, donation_required, participants_required, author_id) VALUES (?,?,?,?,?,?)",
                charity.getName(), charity.getThumbnail_name(), charity.getDescription(), charity.getDonation_required(), charity.getParticipants_required(), charity.getAuthor_id());
    }

    @Override
    public List<Charity> getFiteredCharitiesByTitle(String filter) {
        return null;
    }

    @Override
    public String getCharityAuthorName(int charity_id) {
        if (charity_id <= 0) {
            throw new IllegalArgumentException("Invalid charity_id!");
        }
        return jdbcTemplate.queryForObject("SELECT u.name\n" +
                "FROM users as u\n" +
                "INNER JOIN charities as c\n" +
                "ON c.author_id = u.users_id\n" +
                "where c.charities_id = ?",  String.class, charity_id);
    }
}