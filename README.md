# Vehicle Agency Backend System

## Description

This project implements the backend for a used vehicle sales agency, focusing on vehicle test drives and safety regulations. The backend system allows customers to request test drives of vehicles, which are then tracked by the agency’s employees. Each test drive is registered with the customer’s details, the vehicle being tested, and the employee accompanying the customer. 

The vehicles are equipped with a geolocation system that reports their positions regularly. The backend system monitors these positions to ensure that vehicles do not exceed a specified radius or enter dangerous areas. If any violations occur, the system sends notifications to the assigned employee, prompting them to return the vehicle, and adds the customer to a restricted list.

### Key Features:
- **Test Drive Registration:** Allows employees to create test drives, ensuring customer eligibility based on license validity and restrictions.
- **Geolocation Tracking:** Monitors vehicle location and ensures compliance with agency's safety protocols.
- **Notifications:** Sends notifications when vehicles exceed limits or enter restricted zones.
- **Reports:** Generates reports on incidents, vehicle test drives, and other relevant data.
- **Security Measures:** Implements role-based access control to ensure that only authorized personnel can perform certain actions.

## Microservices Architecture

This backend system is designed using a microservices architecture to ensure scalability and maintainability. The system includes multiple microservices, each responsible for handling specific aspects of the application.

### Technologies Used:
- **Spring Boot** for backend services.
- **API Gateway** to manage all incoming requests.
- **RESTful APIs** for communication between services.
- **PostgreSQL / MongoDB** for data storage.
- **Security Measures** such as JWT authentication to ensure that only authorized users can access sensitive endpoints.

## Requirements

1. **API Endpoints:**
   - Register a new test drive.
   - List all ongoing test drives.
   - End test drive and add comments.
   - Receive and validate vehicle positions.
   - Send notifications based on vehicle position violations.
   - Send promotional notifications.
   - Generate various reports (incident reports, vehicle test details, etc.).

2. **External Service Consumption:**
   - Integrate with an external service to retrieve configuration data for the agency's location, maximum radius, and dangerous zones.

3. **Security:**
   - Implement role-based access control to ensure that only employees can register test drives, only authorized users can submit vehicle positions, and only admins can view reports.

4. **Microservice Requirements:**
   - At least two microservices.
   - An API Gateway for handling incoming requests.
   - Integration with the external service for configuration data.

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/vehicle-agency-backend.git
   cd vehicle-agency-backend
   ```
2. Install dependencies:
   ```bash
   ./mvnw clean install
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. The backend should now be running at http://localhost:8080.

## Usage

- The Postman collection is available in the `TpBack.postman_collection.json` file.

## Security

- **Employee Access:** Only employees can register test drives and send notifications.
- **Vehicle Owners:** Only authorized users can submit vehicle positions.
- **Admin Access:** Only admins can view reports.
