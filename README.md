# Dealer Management API

A Spring Boot REST API for managing dealers, vehicles, and payment processing with JWT authentication.

## Features

### Task 1: Dealer & Vehicle Management
- **Dealer Management**: CRUD operations for dealers with subscription types (BASIC/PREMIUM)
- **Vehicle Management**: CRUD operations for vehicles linked to dealers
- **Premium Dealer Vehicles**: Special endpoint to fetch vehicles from PREMIUM dealers only
- **Database**: PostgreSQL with JPA/Hibernate

### Task 2: Payment Gateway Simulation
- **Payment Initiation**: Accept dealer payments with different methods (UPI/Card/NetBanking)
- **Async Processing**: Simulate 5-second payment processing with status updates
- **JWT Security**: Secure all endpoints with JWT authentication

## Technology Stack

- **Framework**: Spring Boot
- **Database**: PostgreSQL
- **Security**: Spring Security with JWT
- **ORM**: JPA/Hibernate
- **Build Tool**: Maven
- **Technology**: Java

## Database Schema

### Tables Created:
- `users` - Authentication users
- `dealers` - Dealer information with subscription types
- `vehicles` - Vehicle inventory linked to dealers
- `payments` - Payment transaction records