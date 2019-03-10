create table agents(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
agency_id INTEGER NOT NULL,
agent_name VARCHAR(255) not  null,
FOREIGN KEY (agency_id) references agencies(id)
);