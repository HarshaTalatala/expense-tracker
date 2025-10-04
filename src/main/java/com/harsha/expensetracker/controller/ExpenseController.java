package com.harsha.expensetracker.controller;

import com.harsha.expensetracker.modal.Expense;
import com.harsha.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense){
        Expense savedExpense = service.addExpense(expense);
        return ResponseEntity.ok(expense);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(){
        List<Expense> expenses = service.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id){
       Optional<Expense> expense = service.getExpenseById(id);
       if(expense.isPresent()){
           return ResponseEntity.ok(expense.get());
       } else {
           return ResponseEntity.notFound().build();
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense){
        Expense updated = service.updateExpense(id, expense);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id){
        service.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter/category")
    public ResponseEntity<List<Expense>> filterByCategory(@RequestParam String category) {
        List<Expense> expenses = service.filterByCategory(category);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/filter/date")
    public ResponseEntity<List<Expense>> filterByDateRange(@RequestParam String start, @RequestParam String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<Expense> expenses = service.filterByDateRange(startDate, endDate);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/summary/total")
    public ResponseEntity<Double> totalSpent(){
        Double total = service.getTotalSpent();
        return ResponseEntity.ok(total);
    }

    @GetMapping("summary/category")
    public ResponseEntity<List<Object[]>> totalByCategory(){
        List<Object[]> result = service.getTotalSpentByCategory();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/summary/month")
    public ResponseEntity<List<Object[]>> totalByMonth(){
        List<Object[]> result = service.getTotalSpentByMonth();
        return ResponseEntity.ok(result);
    }

}
