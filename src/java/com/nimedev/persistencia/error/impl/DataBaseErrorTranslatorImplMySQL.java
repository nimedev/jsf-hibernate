package com.nimedev.persistencia.error.impl;

import com.nimedev.persistencia.error.BussinessMessage;
import com.nimedev.persistencia.error.DataBaseErrorTranslator;

/**
 *
 * @author niconator
 */
public class DataBaseErrorTranslatorImplMySQL implements DataBaseErrorTranslator {
    
    @Override
    public BussinessMessage getBussinessMessage(String message, int errorCode, String sqlState) {
        return new BussinessMessage(null,
                message + " - Error: " + errorCode
                + " SQLSTATE: " + sqlState + "(" + getSqlStateMsg(sqlState) + ")\n"
                + getErrorCodeMsg(errorCode));
    }
    
    private String getErrorCodeMsg(int errorCode) {
        String message = "";
        return message;
    }
    
    private String getSqlStateMsg(String sqlState) {
        String message = "";
        return message;
    }
    
}
