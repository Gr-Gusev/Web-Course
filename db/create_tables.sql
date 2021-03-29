CREATE TABLE students
(
    student_id   serial PRIMARY KEY,
    student_name varchar(100) NOT NULL
);

CREATE TABLE companies
(
    company_id   serial PRIMARY KEY UNIQUE,
    company_name varchar(50)  NOT NULL UNIQUE,
    address      varchar(100)
);

CREATE TABLE courses
(
    course_id     serial PRIMARY KEY,
    company_id    integer REFERENCES companies (company_id) NOT NULL,
    day_of_start  date                                      NOT NULL,
    day_of_finish date                                      NOT NULL,
    hours_per_day smallint,
    course_name   varchar(50)                               NOT NULL UNIQUE
);

CREATE TABLE teachers
(
    teacher_id   serial PRIMARY KEY,
    teacher_name varchar(100) NOT NULL,
    company_id   integer REFERENCES companies (company_id)
);

CREATE TABLE lessons
(
    lesson_id     serial PRIMARY KEY,
    course_id     integer REFERENCES courses (course_id)   NOT NULL,
    teacher_id    integer REFERENCES teachers (teacher_id) NOT NULL,
    date_and_time timestamp                                NOT NULL
);

CREATE TABLE student_courses
(
    student_id integer REFERENCES students (student_id) NOT NULL,
    course_id  integer REFERENCES courses (course_id)   NOT NULL,
    PRIMARY KEY (student_id, course_id)
);
