package com.harsha.expensetracker.repository;

import com.harsha.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// Database operations for expenses
@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory(String category);
    List<Expense> findByDateBetween(LocalDate dateAfter, LocalDate dateBefore);

    // Calculate total spending
    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double totalSpent();

    // Group spending by category
    @Query("SELECT e.category, SUM(e.amount) FROM Expense e GROUP BY e.category")
    List<Object[]> totalSpentByCategory();

    // Group spending by month
    @Query(value = "SELECT EXTRACT(MONTH FROM date) AS month, SUM(amount) FROM expense GROUP BY month", nativeQuery = true)
    List<Object[]> totalSpentByMonth();
}
