# Personal Expense Tracker

A comprehensive REST API for managing personal expenses built with Spring Boot. This application provides CRUD operations, advanced filtering, category management, and insightful summary reporting with persistent data storage.

## 🚀 Project Overview

The Personal Expense Tracker is a robust backend application designed to help users manage their financial expenses efficiently. It offers a complete set of REST endpoints for tracking, categorizing, and analyzing personal spending patterns with automatic data persistence.

### Key Capabilities
- **Full CRUD Operations**: Create, read, update, and delete expense records
- **Smart Filtering**: Filter expenses by date ranges and categories
- **Comprehensive Reporting**: Generate detailed summaries and spending analytics
- **Data Persistence**: Reliable storage with H2 database (file-based)
- **Validation**: Built-in input validation and error handling

## ✨ Features

- ✅ **Expense Management**: Add, update, delete, and view individual expenses
- 📊 **Categorization**: Organize expenses by categories (Food, Travel, Bills, Shopping, etc.)
- 🔍 **Advanced Filtering**: Filter expenses by date range or specific categories
- 📈 **Summary Reports**: 
  - Total spending calculations
  - Category-wise spending breakdown
  - Monthly spending trends
- 💾 **Database Persistence**: Automatic data storage with H2 database
- ✔️ **Input Validation**: Server-side validation for all expense data
- 🚀 **RESTful API**: Clean JSON-based API following REST principles

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| **Spring Boot 3.5.6** | Main application framework |
| **Spring Data JPA** | Database operations and ORM |
| **Spring Validation** | Input validation and constraints |
| **H2 Database** | Default embedded database (persistent) |
| **PostgreSQL** | Optional production database |
| **Lombok** | Code generation and boilerplate reduction |
| **Maven** | Dependency management and build tool |
| **Java 21** | Programming language |

## 🏗️ Setup Instructions

### Prerequisites
- **Java 17+** (Java 21 recommended)
- **Maven 3.6+**
- **Git** (for cloning)

### Installation Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/HarshaTalatala/expense-tracker.git
   cd ExpenseTracker
   ```

2. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```
   
   The application will start on `http://localhost:8080`

3. **Database Access** (Optional)
   - Access H2 Console: `http://localhost:8080/h2-console`
   - **JDBC URL**: `jdbc:h2:file:./data/expensetrackerdb`
   - **Username**: `sa`
   - **Password**: *(leave empty)*

### 🐘 PostgreSQL Setup (Optional)

To use PostgreSQL instead of H2:

1. **Install PostgreSQL** and create a database named `ExpenseTracker`

2. **Update Configuration**: Uncomment PostgreSQL properties in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.driver-class-name=org.postgresql.Driver
   spring.datasource.url=jdbc:postgresql://localhost:5432/ExpenseTracker
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Comment out H2 configuration** and restart the application

## 📚 API Documentation

### Base URL: `http://localhost:8080/api/expenses`

### 📝 Expense Model
```json
{
  "id": 1,
  "amount": 1200.0,
  "date": "2025-10-04",
  "note": "Dinner with friends",
  "category": "Food"
}
```

### 🔄 CRUD Operations

#### ➕ Add New Expense
```http
POST /api/expenses
Content-Type: application/json

{
  "amount": 1200,
  "date": "2025-10-04",
  "note": "Dinner with friends",
  "category": "Food"
}
```

#### 📋 Get All Expenses
```http
GET /api/expenses
```

#### 🔍 Get Expense by ID
```http
GET /api/expenses/1
```

#### ✏️ Update Expense
```http
PUT /api/expenses/1
Content-Type: application/json

{
  "amount": 1500,
  "date": "2025-10-04",
  "note": "Updated dinner expense",
  "category": "Food"
}
```

#### 🗑️ Delete Expense
```http
DELETE /api/expenses/1
```

### 🔍 Filtering Operations

#### Filter by Category
```http
GET /api/expenses/filter/category?category=Food
```

#### Filter by Date Range
```http
GET /api/expenses/filter/date?start=2025-10-01&end=2025-10-04
```

### 📊 Summary & Analytics

#### Total Spending
```http
GET /api/expenses/summary/total
```
**Response**: `2500.0`

#### Spending by Category
```http
GET /api/expenses/summary/category
```
**Response**:
```json
[
  ["Food", 1500.0],
  ["Travel", 800.0],
  ["Shopping", 200.0]
]
```

#### Monthly Spending Summary
```http
GET /api/expenses/summary/month
```
**Response**:
```json
[
  [10, 2025, 3200.0],
  [9, 2025, 2800.0]
]
```

## 🗄️ Sample Data

The application comes pre-loaded with sample expenses via `import.sql`:

```sql
INSERT INTO EXPENSE (AMOUNT, DATE, NOTE, CATEGORY) VALUES (500, '2025-10-04', 'Lunch with friends', 'Food');
INSERT INTO EXPENSE (AMOUNT, DATE, NOTE, CATEGORY) VALUES (1200, '2025-10-03', 'Groceries', 'Shopping');
INSERT INTO EXPENSE (AMOUNT, DATE, NOTE, CATEGORY) VALUES (300, '2025-10-02', 'Bus ticket', 'Travel');
```

## 🗂️ Project Structure

```
ExpenseTracker/
├── src/main/java/com/harsha/expensetracker/
│   ├── ExpenseTrackerApplication.java    # Main application class
│   ├── controller/ExpenseController.java # REST API endpoints
│   ├── modal/Expense.java               # JPA entity model
│   ├── repository/ExpenseRepo.java      # Data access layer
│   └── service/ExpenseService.java      # Business logic layer
├── src/main/resources/
│   ├── application.properties           # Database & app configuration
│   └── import.sql                      # Sample data initialization
└── data/                               # H2 database files (auto-generated)
```

## 🎯 Key Design Decisions

- **H2 Database**: Chosen for zero-setup development with persistent file storage
- **RESTful Design**: Following REST principles for predictable API behavior
- **Validation**: Server-side validation ensures data integrity
- **Layered Architecture**: Separation of concerns with controller, service, and repository layers
- **Date Handling**: Using `LocalDate` for proper date operations without timezone complexity

## 🚀 Deliverables

- ✅ **Complete Source Code**: All implementation files with proper structure
- ✅ **README.md**: Comprehensive documentation (this file)
- ✅ **API Documentation**: Complete endpoint reference with examples
- ✅ **Sample Data**: Pre-loaded test data for immediate testing
- ✅ **Database Configuration**: Both H2 (default) and PostgreSQL options

## 📝 Development Notes

### Validation Rules
- **Amount**: Must be positive (minimum value: 1)
- **Date**: Required, must be valid date
- **Note**: Required, cannot be blank
- **Category**: Optional field for expense categorization

### Error Handling
The API returns appropriate HTTP status codes:
- `200 OK`: Successful operations
- `201 Created`: Resource created successfully
- `204 No Content`: Successful deletion
- `400 Bad Request`: Invalid input data
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server-side errors

---

**Ready to track your expenses!** 🎉

For questions or contributions, please refer to the project repository or contact the development team.
