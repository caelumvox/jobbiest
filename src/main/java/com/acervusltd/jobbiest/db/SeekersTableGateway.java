package com.acervusltd.jobbiest.db;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.acervusltd.jobbiest.model.Seeker;

public class SeekersTableGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeekersTableGateway.class);

    @Autowired
    private NamedParameterJdbcTemplate jobbiestNamedParamJDBCTemplate;

    private static final String SEEKER_SELECT_BY_ID_QUERY = "select * from seekers where seeker_id = :seeker_id";

    private static final String SEEKER_SELECT_BY_USERNAME_QUERY = "select * from seekers where username = :username";

    private static final String SEEKER_UPDATE_QUERY = "update seekers set username = :username, password=:password, email=:email, address=:address, city=:city, state=:state, firstname=:firstname, lastname=:lastname where seeker_id = :seekerId";

    public Seeker getSeekerById(int seekerId) {
        LOGGER.trace("Fetching seeker with id %d", seekerId);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("seeker_id", seekerId);

        Seeker seeker = (Seeker) jobbiestNamedParamJDBCTemplate.queryForObject(SEEKER_SELECT_BY_ID_QUERY, parameterMap,
                new BeanPropertyRowMapper<Seeker>(Seeker.class));

        return seeker;
    }
    
    public Seeker getSeekerByUsername(String username) {
        LOGGER.trace("Fetching seeker with username %s", username);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("username", username);

        Seeker seeker = (Seeker) jobbiestNamedParamJDBCTemplate.queryForObject(SEEKER_SELECT_BY_USERNAME_QUERY, parameterMap,
                new BeanPropertyRowMapper<Seeker>(Seeker.class));

        return seeker;
    }

    public void updateSeeker(Seeker seeker) {
        try {
            int result = jobbiestNamedParamJDBCTemplate.update(SEEKER_UPDATE_QUERY, new BeanPropertySqlParameterSource(seeker));
            LOGGER.trace("Result of insert: %d", result);
        } catch (DataAccessException dae) {
            LOGGER.trace("Error attempting to update seeker with id [%d]: %s", seeker.getSeekerId(), dae);
        }
        
    }

}
