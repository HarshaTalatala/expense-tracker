package com.harsha.expensetracker.service;

import com.harsha.expensetracker.modal.Expense;
import com.harsha.expensetracker.repository.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepo repo;

    public Expense addExpense(Expense expense){
        return repo.save(expense);
    }

    public List<Expense> getAllExpenses(){
        return repo.findAll();
    }

    public Optional<Expense> getExpenseById(Long id){
        return repo.findById(id);
    }

    public Expense updateExpense(Long id, Expense updatedExpense){
        Optional<Expense> optionalExpense = repo.findById(id);
        if (optionalExpense.isPresent()){
            Expense expense = optionalExpense.get();

            expense.setAmount(updatedExpense.getAmount());
            expense.setDate(updatedExpense.getDate());
            expense.setNote(updatedExpense.getNote());
            expense.setCategory(updatedExpense.getCategory());
            return repo.save(expense);
        }else {
            throw new RuntimeException("Expense not found with id: " + id);
        }
    }

    public void deleteExpense(Long id){
        repo.deleteById(id);
    }

    public List<Expense> filterByCategory(String category){
        return repo.findByCategory(category);
    }

    public List<Expense> filterByDateRange(LocalDate start, LocalDate end){
        return repo.findByDateBetween(start, end);
    }

    public Double getTotalSpent(){
        Double total = repo.totalSpent();
        return total != null ? total : 0.0;
    }

    public List<Object[]> getTotalSpentByCategory(){
        return repo.totalSpentByCategory();
    }

    public List<Object[]> getTotalSpentByMonth(){
        return repo.totalSpentByMonth();
    }

}
