ALTER TABLE tasks RENAME COLUMN start_date TO start_time;
ALTER TABLE tasks RENAME COLUMN end_date TO end_time;

ALTER TABLE tasks ALTER COLUMN start_time TYPE TIME;
ALTER TABLE tasks ALTER COLUMN end_time TYPE TIME;

ALTER TABLE tasks ADD COLUMN task_date DATE;