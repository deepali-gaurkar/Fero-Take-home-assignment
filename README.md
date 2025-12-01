# StockFlow

StockFlow is a small command-line inventory application that processes `sell` and `buy` operations.
Stock is managed using a **FIFO (First-In-First-Out)** approach. After every command, the program prints
the complete order history, including updated remaining quantities.

The solution is implemented in plain Java without any external frameworks, as required by the assignment.

---

## Overview

The system maintains two core concepts:

### 1. Sell Orders
These add stock to the system.  
Each sell order has a `remaining` value that decreases when stock is consumed.

### 2. Buy Orders
These remove stock using FIFO logic.  
A buy order:
- is fulfilled from the oldest sell orders first
- may be partially fulfilled if stock is insufficient
- is only recorded if some quantity is actually fulfilled

The program ignores invalid input and never terminates with an error.

---

## Data Model

### Order
Each Order contains:
- `type` — "sell" or "buy"
- `sku` — product identifier (e.g., wine, whisky)
- `quantity` — original requested quantity
- `remaining` — updated value for sells; always zero for buys

### Inventory
The Inventory class stores:
- a full history of all orders
- a FIFO queue of sell orders per SKU

A queue is used to ensure correct FIFO behaviour when fulfilling buy requests.

---

## Assumptions

- Invalid or malformed input should be ignored without stopping the program.
- SKUs are not hardcoded; new SKUs should work without code changes.
- A buy order is only added to the history if any stock was consumed.
- Quantities are expected to be positive integers.
- Output format must match the sample provided in the assignment.

---

## Extensibility

The design allows the system to be extended in several ways:

- Tracking pricing and cost information
- Adding expiry-based consumption rules
- Persisting history to a database or file
- Adding support for additional command types
- Wrapping Inventory in an API (REST, gRPC, etc.)

The core FIFO consumption logic remains unchanged.

---

## Building the Application

Use Maven to compile the project:

```
mvn clean package
```

This produces the executable JAR:

```
target/stockflow.jar
```

---

## Running the Application

Run the JAR using:

```
java -jar target/stockflow.jar
```

You will see a prompt:

```
WELCOME TO FEROVINIUM STOCKFLOW APPLICATION
Enter your command (e.g. sell wine 1000):
```

Then you may enter commands such as:

```
sell wine 1000
buy wine 500
sell whisky 200
buy whisky 120
```

---

## Example Session

Input:
```
sell wine 1000
sell whisky 100
buy wine 500
buy wine 1000
sell whisky 100
buy whisky 120
```

Output matches the example from the assignment specification:

```
sell wine 1000 remaining:1000
sell whisky 100 remaining:100
sell wine 1000 remaining:500
buy wine 500 closed
sell wine 1000 closed
buy wine 500 closed
buy wine 500 closed
sell whisky 100 remaining:100
sell whisky 100 remaining:80
buy whisky 120 closed
```

---

## Running with Docker

Build the Docker image:

```
docker build -t stockflow .
```

Run the application:

```
docker run -it stockflow
```

The program will start exactly as it does locally.

---

## Unit Tests

A set of JUnit tests is included to cover:

- FIFO stock consumption
- Partial buys
- Zero-stock scenarios
- Dynamic SKU behaviour
- Correct order history tracking

Run tests with:

```
mvn test
```

---

## Project Structure

```
src/
 ├── main/
 │   └── java/com/ferovinium/stockflow/
 │       ├── Main.java
 │       ├── Inventory.java
 │       └── Order.java
 └── test/
     └── java/com/ferovinium/stockflow/
         └── InventoryTest.java
```

---

This implementation focuses on clarity, correctness, and adherence to the problem requirements.
