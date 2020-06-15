package org.vmware.finaltask.networkOfGiving.mapper;

import org.vmware.finaltask.networkOfGiving.model.Charity;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharityRowMapper implements RowMapper<Charity> {

    @Override
    public Charity mapRow(ResultSet rs, int rowNum) throws SQLException {

        Charity result = new Charity();

        result.setCharities_id(rs.getInt("Charities_ID"));
        result.setName(rs.getString("Name"));
        result.setThumbnail_name(rs.getString("Thumbnail_Name"));
        result.setDescription(rs.getString("Description"));
        result.setDonation_required(rs.getInt("Donation_Required"));
        result.setParticipants_required(rs.getInt("Participants_Required"));
        result.setAuthor_id(rs.getInt("Author_ID"));

        return (Charity) result;
    }

}
