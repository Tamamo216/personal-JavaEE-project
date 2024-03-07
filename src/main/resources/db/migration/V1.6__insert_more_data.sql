INSERT INTO departments (name, start_date)
VALUES
  ('Finance', '2023-01-01'),
  ('Human Resources', '2023-02-15'),
  ('Marketing', '2023-03-10'),
  ('Operations', '2023-04-20'),
  ('Research and Development', '2023-05-05');

INSERT INTO employees (date_of_birth, first_name, middle_name, last_name, gender, salary, deptid)
VALUES
  ('1990-05-15', 'John', 'Robert', 'Doe', 'MALE', 60000, 1),
  ('1993-08-20', 'Jane', 'Marie', 'Smith', 'FEMALE', 55000, 2),
  ('1988-03-10', 'Michael', 'David', 'Johnson', 'MALE', 65000, 3),
  ('1995-11-25', 'Emily', 'Anne', 'Williams', 'FEMALE', 58000, 4),
  ('1985-09-03', 'Christopher', 'Lee', 'Brown', 'MALE', 70000, 5),
  ('1991-12-08', 'Jessica', 'Nicole', 'Jones', 'FEMALE', 60000, 1),
  ('1989-07-17', 'William', 'Thomas', 'Miller', 'MALE', 62000, 2),
  ('1994-04-22', 'Amanda', 'Elizabeth', 'Davis', 'FEMALE', 63000, 3),
  ('1987-06-30', 'Daniel', 'Patrick', 'Wilson', 'MALE', 67000, 4),
  ('1992-10-12', 'Sarah', 'Louise', 'Anderson', 'FEMALE', 59000, 5),
  ('1986-02-18', 'Matthew', 'Ryan', 'Taylor', 'MALE', 68000, 1),
  ('1996-01-05', 'Samantha', 'Rose', 'Martinez', 'FEMALE', 61000, 2),
  ('1990-07-21', 'David', 'Allen', 'Hernandez', 'MALE', 66000, 3),
  ('1988-11-14', 'Ashley', 'Marie', 'Garcia', 'FEMALE', 64000, 4),
  ('1993-09-28', 'James', 'Edward', 'Rodriguez', 'MALE', 69000, 5),
  ('1989-04-03', 'Jennifer', 'Michelle', 'Lopez', 'FEMALE', 62000, 1),
  ('1991-12-10', 'Robert', 'Joseph', 'Lee', 'MALE', 67000, 2),
  ('1987-05-27', 'Megan', 'Grace', 'Walker', 'FEMALE', 63000, 3),
  ('1995-08-14', 'Ryan', 'Anthony', 'Young', 'MALE', 70000, 4),
  ('1992-02-09', 'Lauren', 'Ashley', 'King', 'FEMALE', 59000, 5);

INSERT INTO projects (area, name, managed_department)
VALUES
  ('Finance', 'Financial Reporting System', 1),
  ('Human Resources', 'Employee Training Program', 2),
  ('Marketing', 'Product Launch Campaign', 3),
  ('Operations', 'Supply Chain Optimization', 4),
  ('Research and Development', 'New Product Development', 5),
  ('Finance', 'Budget Management System', 1),
  ('Human Resources', 'Recruitment Strategy Implementation', 2),
  ('Marketing', 'Market Research Initiative', 3),
  ('Operations', 'Inventory Management System Upgrade', 4),
  ('Research and Development', 'Innovation Lab Setup', 5),
  ('Finance', 'Expense Tracking Application', 1),
  ('Human Resources', 'Performance Appraisal System Upgrade', 2),
  ('Marketing', 'Social Media Marketing Campaign', 3),
  ('Operations', 'Logistics Optimization Project', 4),
  ('Research and Development', 'Prototype Testing Program', 5),
  ('Finance', 'Financial Forecasting Model Development', 1),
  ('Human Resources', 'Employee Engagement Program', 2),
  ('Marketing', 'Brand Identity Redesign', 3),
  ('Operations', 'Facility Expansion Project', 4),
  ('Research and Development', 'Research Collaboration Initiative', 5);

INSERT INTO assignments (number_of_hour, employee_id, project_id)
VALUES
  (40, 1, 1),
  (30, 2, 2),
  (35, 3, 3),
  (45, 4, 4),
  (25, 5, 5),
  (38, 6, 1),
  (32, 7, 1),
  (37, 8, 2),
  (42, 9, 3),
  (27, 10, 4),
  (39, 11, 1),
  (31, 12, 2),
  (36, 13, 3),
  (43, 14, 4),
  (28, 15, 5),
  (41, 16, 1),
  (33, 17, 2),
  (34, 18, 3),
  (44, 19, 4),
  (29, 20, 5);