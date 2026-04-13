Task Manager API
A production-ready Spring Boot REST API for managing tasks with advanced features like pagination, filtering, enum-based state management, and global exception handling.
This project follows clean architecture and backend best practices, making it suitable for real-world applications and resume showcasing.
🌟 Highlights
Scalable REST API design
Pagination & sorting support
Enum-based status & priority handling
Clean layered architecture
Centralized exception handling
Audit fields (createdAt, updatedAt)
Flexible filtering APIs
🧠 Problem Statement
In real-world systems, task management requires:
Efficient handling of large datasets
Filtering based on multiple conditions
Maintainable and scalable backend architecture
This project addresses these challenges using Spring Boot and JPA best practices.
🏗️ Architecture
Controller → Service → Repository → Database
Controller: Handles HTTP requests and responses
Service: Contains business logic
Repository: Interacts with database
Entity/DTO: Data representation
🛠️ Tech Stack
Backend: Spring Boot
Database: MySQL
ORM: Hibernate (JPA)
Build Tool: Maven
Language: Java
📂 Project Structure

src/main/java/com/project/
│── controller/
│── service/
│── repository/
│── entity/
│── dto/
│── enums/
│── exception/
│── config/
📊 Core Features
Task Management
Create, update, delete tasks
Track task lifecycle
Filtering
By Status → PENDING, IN_PROGRESS, COMPLETED
By Priority → LOW, MEDIUM, HIGH
By Deadline
Pagination
Efficient handling of large datasets:

GET /tasks?page=0&size=10
Exception Handling
Global exception handler
Clean error responses
🔌 API Endpoints
Create Task

POST /tasks
Get All Tasks (Paginated)

GET /tasks?page=0&size=10
Filter by Status

GET /tasks/status?status=PENDING
Filter by Priority

GET /tasks/priority?priority=HIGH
Update Task

PUT /tasks/{id}
Delete Task

DELETE /tasks/{id}
📥 Sample Request
JSON
{
  "title": "Complete Task Manager",
  "description": "Implement pagination and filtering",
  "status": "IN_PROGRESS",
  "priority": "HIGH",
  "deadline": "2026-04-20"
}
📤 Sample Response
JSON
{
  "id": 1,
  "title": "Complete Task Manager",
  "status": "IN_PROGRESS",
  "priority": "HIGH",
  "createdAt": "2026-04-10T10:00:00"
}
📈 Pagination Response
JSON
{
  "content": [...],
  "totalPages": 5,
  "totalElements": 50,
  "size": 10,
  "number": 0
}
⚙️ Setup & Installation
Clone Repository

git clone https://github.com/your-username/task-manager.git
Configure Database
Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/taskdb
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
Run Application

mvn spring-boot:run
🧪 Testing the API
You can use:
Postman
cURL
Example:

curl http://localhost:8080/tasks?page=0&size=5
🚀 Future Enhancements
JWT Authentication & Authorization
Multi-user support
Task reminders & notifications
Analytics dashboard
Frontend integration (React)
Async processing (Kafka / RabbitMQ)
🧑‍💻 Author
Nikhil Soni
💡 Project Value
This project demonstrates:
Real-world backend development
REST API design principles
Clean architecture
Efficient data handling using pagination
Production-level exception handling
⭐ Support
If you like this project:
Star the repository
Fork it
Contribute
📜 License
This project is licensed under the MIT License.
