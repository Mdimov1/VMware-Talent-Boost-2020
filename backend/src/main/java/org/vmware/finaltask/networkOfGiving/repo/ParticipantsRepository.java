package org.vmware.finaltask.networkOfGiving.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vmware.finaltask.networkOfGiving.interfaces.IParticipantsService;

@Repository
public class ParticipantsRepository implements IParticipantsService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParticipantsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addParticipantInCharity(int charity_id, int user_id) {
        jdbcTemplate.update("INSERT INTO userscharities (charities_id, users_id) VALUES (?,?)", charity_id, user_id);
    }

    @Override
    public int getCountOfParticipantsInCharity(int charity_id) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(users_id)\n" +
                "FROM userscharities\n" +
                "WHERE Charities_ID = ?", Integer.class, charity_id);

        if(count != null){
            return count;
        }
        return 0;
    }

    @Override
    public int getCountParticipant(int charity_id, int user_id) {
        Integer countParticipant = jdbcTemplate.queryForObject("SELECT COUNT(users_id)\n" +
                "FROM userscharities\n" +
                "WHERE charities_id = ? \n" +
                "AND users_id = ?", Integer.class, charity_id, user_id);

        if(countParticipant != null){
            return countParticipant;
        }
        return 0;
    }
}
