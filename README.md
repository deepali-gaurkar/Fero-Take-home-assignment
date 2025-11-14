# StockFlow

A CLI that processes simple `sell` and `buy` commands for wine and whisky.
Sell orders add stock, and buy orders consume it in FIFO order. After each command, the
program prints the full history of all orders processed.

### How to run

Build:
mvn clean package

Run:
java -jar target/stockflow.jar

Example:
sell wine 1000
buy wine 400

### Notes

- Only "wine" and "whisky" are supported.
- A buy only completes if the full quantity is available.
- Stock is consumed from oldest sells first (FIFO).
- No External library / Framework Used
