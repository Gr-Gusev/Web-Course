CREATE TABLE students
(
    student_id   serial PRIMARY KEY,
    student_name varchar(100) NOT NULL
);

CREATE TABLE companies
(
    company_id   serial PRIMARY KEY UNIQUE,
    company_name varchar(100)  NOT NULL UNIQUE,
    address      varchar(150)
);

CREATE TABLE courses
(
    course_id     serial  PRIMARY KEY,
    company_id    integer REFERENCES companies (company_id) ON DELETE CASCADE NOT NULL,
    day_of_start  date                                      NOT NULL,
    day_of_finish date                                      NOT NULL,
    hours_per_day smallint,
    course_name   varchar(100)                               NOT NULL
);

CREATE TABLE teachers
(
    teacher_id   serial                                     PRIMARY KEY,
    teacher_name varchar(100)                               NOT NULL,
    company_id   integer REFERENCES companies (company_id)  ON DELETE SET NULL
);

CREATE TABLE lessons
(
    lesson_id     serial  PRIMARY KEY,
    course_id     integer REFERENCES courses (course_id)   ON DELETE CASCADE NOT NULL,
    teacher_id    integer REFERENCES teachers (teacher_id) ON DELETE SET NULL,
    date_and_time timestamp                                NOT NULL
);

CREATE TABLE student_courses
(
    student_id integer REFERENCES students (student_id)    ON DELETE CASCADE NOT NULL,
    course_id  integer REFERENCES courses (course_id)      ON DELETE CASCADE NOT NULL,
    PRIMARY KEY (student_id, course_id)
);
