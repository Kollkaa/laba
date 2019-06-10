create table students(
                         id INTEGER PRIMARY KEY AUTO_INCREMENT,
                         group_id INTEGER NOT NULL,
                         student_name VARCHAR(255) not  null,
                         FOREIGN KEY (group_id) references groups(id)
);