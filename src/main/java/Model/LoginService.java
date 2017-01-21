package Model;

import Exceptions.ExceptionForUser;

import java.util.Map;

/**
 * Created by INNO on 21.01.2017.
 */
public interface LoginService {
    public boolean addUser(Map<String, String> userDatas) throws ExceptionForUser;

    public boolean haveAccountDatas(String username, String email) throws ExceptionForUser;

    public boolean haveUser(String username, String password) throws ExceptionForUser;

    public void setDb(DataBaseSQL db);
}
