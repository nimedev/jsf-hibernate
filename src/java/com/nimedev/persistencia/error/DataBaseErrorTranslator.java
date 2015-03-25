package com.nimedev.persistencia.error;

/**
 *
 * @author niconator
 */
public interface DataBaseErrorTranslator {

    BussinessMessage getBussinessMessage(String message, int errorCode,
            String sqlState);
}
