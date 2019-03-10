create table user_order(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
user_id INTEGER NOT NULL,
order_id INTEGER NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (order_id) REFERENCES orders(id)
)
