package com.everis;

import com.everis.expense.boundary.ExpenseService;
import com.everis.expense.entity.Expense;
import com.everis.expenseType.boundary.ExpenseTypeService;
import com.everis.expenseType.entity.ExpenseType;
import com.everis.home.boundary.HomeService;
import com.everis.home.entity.Home;
import com.everis.login.boundary.LoginService;
import com.everis.login.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class StartupConfigurer {

    @Inject
    private LoginService loginService;

    @Inject
    private HomeService homeService;

    @Inject
    private ExpenseTypeService expenseTypeService;

    @Inject
    private ExpenseService expenseService;

    private Home[] homes = {
            new Home("Casa", "Rua Dr. Jaime Cortesão", "11", "5D")
    };
    private ExpenseType[] expensesType = {
            new ExpenseType("FOOD"),
            new ExpenseType("HOUSE BILLS"),
            new ExpenseType("CAR")
    };

    @PostConstruct
    public void init() {
        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@admin.pt");
        user.setPassword("admin");
        loginService.createUser(user);

        for (Home home : homes)
            this.homeService.createHome(home);

        for (ExpenseType et : expensesType)
            this.expenseTypeService.createExpenseType(et);

        Expense expense = new Expense();
        expense.setHome(homes[0]);
        expense.setUser(user);
        expense.setDescription("Continente");
        expense.setValue(Float.parseFloat("1.24"));
        expense.setExpenseType(expensesType[0]);
        this.expenseService.createExpense(expense);
    }
}
