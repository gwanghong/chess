package service;

import dataaccess.DataAccessException;
import dataaccess.ClearDAO;

public class ClearService {

    public void clear() throws DataAccessException {
        ClearDAO clearDao = new ClearDAO();
        clearDao.clear();
    }
}
