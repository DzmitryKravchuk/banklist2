package devinc.banklist.servise;

import devinc.banklist.dao.UserAccountDAO;
import devinc.banklist.dao.UserAccountDAOImpl;
import devinc.banklist.model.User;
import devinc.banklist.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private UserAccountDAO userAccountDAO;
    private UserService userService;

    @Autowired
    public void setUserAccountDAO(UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public List<UserAccount> allAccounts() {
        return userAccountDAO.allAccounts();
    }

    @Override
    @Transactional
    public void add(UserAccount account) {
        userAccountDAO.add(account);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userAccountDAO.delete(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        userAccountDAO.deleteAll();
    }

    @Override
    @Transactional
    public void edit(UserAccount account) {
        userAccountDAO.edit(account);
    }

    @Override
    @Transactional
    public UserAccount getById(int id) {
        return userAccountDAO.getById(id);
    }

    @Override
    @Transactional
    public List<UserAccount> findAccountsForUser(Integer userId) {
        return userAccountDAO.findAccountsForUser(userId);
    }

    @Override
    @Transactional
    public User findRichestUser() {

        List<UserAccount> accounts = userAccountDAO.allAccounts();
        List<User> users = userService.allUsers();
        Map<Integer, Integer> balanceMap = new HashMap<>();
        for (User user : users) {
            balanceMap.put(user.getId(), 0);
            for (UserAccount account : accounts) {
                if (user.getId() == account.getUser().getId()) {
                    int temp = balanceMap.get(user.getId());
                    balanceMap.put(user.getId(), temp + account.getAccount());
                }
            }
        }
        Map.Entry<Integer, Integer> maxEntry = Collections.max(balanceMap.entrySet(), (Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) -> e1.getValue()
                .compareTo(e2.getValue()));

        return userService.getById(maxEntry.getKey());

    }

    @Override
    @Transactional
    public int allAccountsSum() {
        List<UserAccount> accounts = userAccountDAO.allAccounts();
        Integer sum = accounts.stream().
                map(a -> a.getAccount())
                .reduce(0, Integer::sum);
        return sum;
    }


}
