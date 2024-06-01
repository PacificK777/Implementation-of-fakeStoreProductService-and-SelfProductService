# Product Management System

This is a product management system built with Java, Spring Boot, and Maven. It allows for the creation, updating, and management of products and their associated categories.

## Features

- Product Creation: Allows for the creation of new products with attributes such as title, description, price, image, and category.
- Product Updating: Provides the ability to update existing products.
- Category Management: Enables the creation and management of product categories.
- Automatic Timestamping: Automatically adds timestamps for product creation and updates.
- Soft Deletion: Products are marked as 'isDeleted=false' initially, allowing for soft deletion of products.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven
- Spring Boot
- MySQL

### Installation

1. Clone the repository
2. Navigate to the project directory
3. Run `mvn install` to install dependencies
4. Run `mvn spring-boot:run` to start the server

## Usage

After starting the server, you can interact with the API endpoints to create, update, and manage products and categories.

## Contributing

Contributions are welcome. Please fork the repository and create a pull request with your changes.
