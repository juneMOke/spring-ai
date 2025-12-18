-- data.sql
INSERT INTO app_user (name)
VALUES ('Alice');

INSERT INTO employee_leave (leave_date, user_id)
VALUES (DATE '2025-12-24',
           SELECT id FROM app_user WHERE name = 'Alice');

