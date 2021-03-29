INSERT INTO students (student_name)
VALUES ('Nicholas Hawkins'),
       ('Lewis Carroll'),
       ('Harvey Tucker'),
       ('Patience Thompson'),
       ('Marybeth Shepherd'),
       ('Christopher Smith'),
       ('Andra Waters'),
       ('Hester Rich'),
       ('Marshall Greer'),
       ('Clarissa Dalton');

INSERT INTO companies (company_name, address)
VALUES ('Cascade', 'Moscow, Pridonskoy, Zashchitnikov Rodiny, bld. 18, appt. 23'),
       ('Vega', 'Moscow, Dmitriya Donskogo Ul., bld. 27/1, appt. 40'),
       ('Siberia star', 'Moscow, Energetikov, bld. 26/1, appt. 137'),
       ('Fontanka', 'Moscow, Sirenevyy Proez, bld. 30, appt. 55'),
       ('Orion', 'Moscow, Kopernika, bld. 30, appt. 2');

INSERT INTO courses (company_id, day_of_start, day_of_finish,
                     hours_per_day, course_name)
VALUES ('1', '2021-03-27', '2021-04-27', '2', 'Data Analysis'),
       ('1', '2021-01-10', '2021-01-20', '2', 'Introduction to the C language'),
       ('3', '2021-02-06', '2021-03-29', '1', 'Basics of C++'),
       ('5', '2021-03-01', '2021-03-22', '2', 'Game Theory. Part 1'),
       ('4', '2021-03-20', '2021-03-26', '3', 'Basics of statistics'),
       ('5', '2020-09-01', '2021-03-27', '1', 'Programming language Python');

INSERT INTO teachers (teacher_name, company_id)
VALUES ('Kerry Hill', '1'),
       ('Berenice Bond', '2'),
       ('Abigail Hall', '1'),
       ('Russell Thornton', '3'),
       ('Silvester Fitzgerald', '5'),
       ('Marshall Richardson', '4');

INSERT INTO student_courses (student_id, course_id)
VALUES ('1', '3'),
       ('2', '1'),
       ('2', '5'),
       ('3', '3'),
       ('5', '4'),
       ('5', '2'),
       ('6', '4'),
       ('5', '3'),
       ('7', '4'),
       ('8', '2'),
       ('9', '5'),
       ('7', '2'),
       ('3', '1'),
       ('7', '1');

INSERT INTO lessons (course_id, teacher_id, date_and_time)
VALUES ('1', '1', '2021-03-27 20:00:00'),
       ('1', '3', '2021-03-30 15:00:00'),
       ('1', '1', '2021-04-07 16:00:00'),
       ('1', '1', '2021-04-01 17:00:00'),
       ('1', '1', '2021-04-12 17:00:00'),
       ('2', '1', '2021-01-10 17:00:00'),
       ('2', '1', '2021-01-15 18:00:00'),
       ('2', '1', '2021-01-20 18:00:00'),
       ('3', '4', '2021-02-06 18:00:00'),
       ('3', '4', '2021-02-12 17:00:00'),
       ('3', '4', '2021-02-18 17:00:00'),
       ('3', '4', '2021-02-24 11:00:00'),
       ('4', '5', '2021-03-01 09:00:00'),
       ('4', '5', '2021-03-07 09:00:00'),
       ('5', '6', '2021-03-20 14:00:00'),
       ('5', '6', '2021-03-21 17:00:00'),
       ('5', '6', '2021-03-22 19:00:00'),
       ('5', '6', '2021-03-23 17:00:00'),
       ('5', '6', '2021-03-24 18:00:00'),
       ('6', '5', '2020-09-01 17:00:00'),
       ('6', '5', '2020-09-11 18:00:00'),
       ('6', '5', '2020-09-21 17:00:00');
