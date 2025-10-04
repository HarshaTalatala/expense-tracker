package com.harsha.expensetracker.service;

import com.harsha.expensetracker.modal.Expense;
import com.harsha.expensetracker.repository.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// Business logic for expenses
@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepo repo;

    // Save new expense
    public Expense addExpense(Expense expense){
        return repo.save(expense);
    }

    // Fetch all expenses
    public List<Expense> getAllExpenses(){
        return repo.findAll();
    }

    // Find expense by ID
    public Optional<Expense> getExpenseById(Long id){
        return repo.findById(id);
    }

    // Update existing expense
    public Expense updateExpense(Long id, Expense updatedExpense){
        Optional<Expense> optionalExpense = repo.findById(id);
        if (optionalExpense.isPresent()){
            Expense expense = optionalExpense.get();

            // Update all fields
            expense.setAmount(updatedExpense.getAmount());
            expense.setDate(updatedExpense.getDate());
            expense.setNote(updatedExpense.getNote());
            expense.setCategory(updatedExpense.getCategory());
            return repo.save(expense);
        }else {
            throw new RuntimeException("Expense not found with id: " + id);
        }
    }

    // Remove expense
    public void deleteExpense(Long id){
        repo.deleteById(id);
    }

    // Filter by category
    public List<Expense> filterByCategory(String category){
        return repo.findByCategory(category);
    }

    // Filter by date range
    public List<Expense> filterByDateRange(LocalDate start, LocalDate end){
        return repo.findByDateBetween(start, end);
    }

    // Calculate total spent
    public Double getTotalSpent(){
        Double total = repo.totalSpent();
        return total != null ? total : 0.0; // Handle null case
    }

    // Group by category
    public List<Object[]> getTotalSpentByCategory(){
        return repo.totalSpentByCategory();
    }

    // Group by month
    public List<Object[]> getTotalSpentByMonth(){
        return repo.totalSpentByMonth();
    }

}
