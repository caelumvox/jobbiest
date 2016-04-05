package com.acervusltd.jobbiest.db;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.acervusltd.jobbiest.model.Seeker;

public class SeekerTableGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeekerTableGateway.class);

    @Autowired
    private NamedParameterJdbcTemplate jobbiestNamedParamJDBCTemplate;

    private static final String SEEKER_QUERY = "select * from seeker where seeker_id = :seeker_id";

    private static final String SEEKER_UPDATE_QUERY = "update seeker set username = :username, password=:password, email=:email, address=:address, city=:city, state=:state, firstname=:firstname, lastname=:lastname where seeker_id = :seeker_id";

    public Seeker getSeeker(int seekerId) {
        LOGGER.trace("Fetching seeker with id %d", seekerId);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("seeker_id", seekerId);

        Seeker seeker = (Seeker) jobbiestNamedParamJDBCTemplate.queryForObject(SEEKER_QUERY, parameterMap,
                new BeanPropertyRowMapper<Seeker>(Seeker.class));

        return seeker;
    }

    public void updateSeeker(Seeker seeker) {

    }
}
