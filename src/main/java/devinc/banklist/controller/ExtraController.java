package devinc.banklist.controller;

import devinc.banklist.dto.UserAccountDTO;
import devinc.banklist.model.User;
import devinc.banklist.model.UserAccount;
import devinc.banklist.servise.UserAccountService;
import devinc.banklist.servise.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ExtraController {
    private UserService userService;
    private UserAccountService accountService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAccountService(UserAccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/extra", method = RequestMethod.GET)
    public ModelAndView extraInformation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("extra");
        User richestUser = accountService.findRichestUser();
        modelAndView.addObject("richestUser", richestUser.getName() + " " + richestUser.getSurName());

        List <UserAccount> richestAccounts = accountService.findAccountsForUser(richestUser.getId());
        int richestUserBalance = richestAccounts.stream().
                map(a -> a.getAccount())
                .reduce(0, Integer::sum);;
        modelAndView.addObject("richestUserBalance",richestUserBalance);

        int totalBalance = accountService.allAccountsSum();
        modelAndView.addObject("totalBalance",totalBalance);

        return modelAndView;
    }
}
