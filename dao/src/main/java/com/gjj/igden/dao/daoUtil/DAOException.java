package com.gjj.igden.dao.daoUtil;

@SuppressWarnings("serial")
public class DAOException extends Exception {
    public DAOException(String message) {
        super(message);
    }

    public DAOException(String msg, Throwable cause) {
        super(msg, cause);
    }
}