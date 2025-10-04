package com.harsha.expensetracker.repository;

import com.harsha.expensetracker.modal.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory(String category);
    List<Expense> findByDate(LocalDate date);
    List<Expense> findByDateBetween(LocalDate dateAfter, LocalDate dateBefore);

    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double totalSpent();

    @Query("SELECT e.category, SUM(e.amount) FROM Expense e GROUP BY e.category")
    List<Object[]> totalSpentByCategory();

    @Query(value = "SELECT EXTRACT(MONTH FROM date) AS month, SUM(amount) FROM expense GROUP BY month", nativeQuery = true)
    List<Object[]> totalSpentByMonth();
}
