# ExpensoMate

**ExpensoMate** is a comprehensive expense tracking backend application built with Spring Boot. This RESTful API provides robust functionality for managing personal finances, including expense tracking, budget management, savings goals, and user authentication.

## 🚀 Features

- **User Management**: Secure user registration and authentication
- **Expense Tracking**: Add, update, delete, and categorize expenses
- **Budget Management**: Set and monitor monthly budgets
- **Savings Goals**: Track savings and financial goals
- **Category Management**: Organize expenses with custom categories
- **Relationship Management**: Link users with their expenses, budgets, and savings
- **Data Persistence**: H2 in-memory database for development and testing
- **Global Exception Handling**: Centralized error management
- **Data Validation**: Input validation using Spring Boot Validation

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.5.3
- **Language**: Java 17
- **Database**: H2 (In-memory for development)
- **ORM**: Hibernate/JPA
- **Build Tool**: Maven
- **Additional Libraries**:
  - Spring Data JPA
  - Spring Web
  - Spring Boot DevTools
  - Lombok
  - Spring Boot Validation
  - H2 Database

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use the included Maven wrapper)
- IDE with Spring Boot support (IntelliJ IDEA, Eclipse, VS Code)

## 🏃‍♂️ Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd files
```

### 2. Build the Project
```bash
./mvnw clean compile
```

### 3. Run the Application
```bash
./mvnw spring-boot:run
```

The application will start on **http://localhost:8081**

### 4. Access H2 Database Console
- URL: **http://localhost:8081/h2-console**
- JDBC URL: `jdbc:h2:mem:expensomate_db`
- Username: `sa`
- Password: (leave empty)

## 📚 API Endpoints

### User Management
- `POST /users/signup` - Register a new user
- `POST /users/login` - User authentication

### Expense Management
- `GET /expenses` - Get all expenses
- `POST /expenses` - Create a new expense
- `PUT /expenses/{id}` - Update an expense
- `DELETE /expenses/{id}` - Delete an expense

### Budget Management
- `GET /budgets` - Get all budgets
- `POST /budgets` - Create a new budget
- `PUT /budgets/{id}` - Update a budget
- `DELETE /budgets/{id}` - Delete a budget

### Category Management
- `GET /categories` - Get all categories
- `POST /categories` - Create a new category
- `PUT /categories/{id}` - Update a category
- `DELETE /categories/{id}` - Delete a category

### Savings Management
- `GET /savings` - Get all savings
- `POST /savings` - Create a new saving goal
- `PUT /savings/{id}` - Update a saving goal
- `DELETE /savings/{id}` - Delete a saving goal

## 🗃️ Database Schema

The application uses the following main entities:

- **UserCredentials**: User authentication and profile information
- **Expenses**: Individual expense records
- **Budget**: Monthly budget allocations
- **Category**: Expense categories
- **Savings**: Savings goals and amounts
- **Relationship**: Links between users, expenses, budgets, and savings

## ⚙️ Configuration

### Application Properties
The application configuration is located in `src/main/resources/application.properties`:

```properties
spring.application.name=ExpensoMate
server.port=8081

# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:expensomate_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.open-in-view=false
```

## 🧪 Testing

Run the test suite using:
```bash
./mvnw test
```

## 📦 Build for Production

Create a production-ready JAR file:
```bash
./mvnw clean package
```

The JAR file will be created in the `target/` directory.

## 🔧 Development

### Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── expensomate/
│   │           ├── controller/     # REST Controllers
│   │           ├── dto/           # Data Transfer Objects
│   │           ├── entity/        # JPA Entities
│   │           ├── exception/     # Custom Exceptions
│   │           ├── mapper/        # Entity-DTO Mappers
│   │           ├── repository/    # JPA Repositories
│   │           ├── service/       # Business Logic
│   │           ├── template/      # Response Templates
│   │           └── ExpensoMateApplication.java
│   └── resources/
│       └── application.properties
└── test/
```

### Adding New Features
1. Create/modify entities in the `entity` package
2. Add repository interfaces in the `repository` package
3. Implement business logic in the `service` package
4. Create DTOs for data transfer in the `dto` package
5. Add REST endpoints in the `controller` package

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📄 License

This project is licensed under the Apache License 2.0.

## 🆘 Support

For support or questions, please create an issue in the project repository.

---

**ExpensoMate** - Making expense tracking simple and efficient! 💰✨
