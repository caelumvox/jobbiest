package com.acervusltd.jobbiest.db;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.acervusltd.jobbiest.model.State;

public class StatesTableGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatesTableGateway.class);

    @Autowired
    private NamedParameterJdbcTemplate jobbiestNamedParamJDBCTemplate;

    private static final String STATE_LIST_SELECT_QUERY = "select abbreviation from states order by abbreviation";

    public List<State> getStatesList() {
        LOGGER.trace("Fetching all states");

        List<State> statesList = null;
        try {
            statesList = jobbiestNamedParamJDBCTemplate.query(STATE_LIST_SELECT_QUERY, new HashMap<String, Object>(), 
                    new BeanPropertyRowMapper<State>(State.class));
        } catch (DataAccessException dae) {
            LOGGER.warn("Error attempting to fetch all states", dae);
        }

        return statesList;
    }

}
