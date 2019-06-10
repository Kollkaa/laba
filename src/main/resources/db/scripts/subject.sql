create table subjects(
                         id INTEGER primary key AUTO_INCREMENT,
                         subject_group INTEGER NOT NULL,
                         subject_name VARCHAR(255),
                         subject_description VARCHAR(255),
                         mark INTEGER
)