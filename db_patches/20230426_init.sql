CREATE TABLE IF NOT EXISTS users(
	"id" bigserial PRIMARY KEY,
	"inserted_at" timestamp NOT NULL DEFAULT NOW(),
	"first_name" text,
	"last_name" text,
	"username" text UNIQUE,
	"email_address" text UNIQUE,
	"password" text,
	"authenticated" boolean DEFAULT FALSE,
	"is_deleted" boolean DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS verifications_email(
	"id" bigserial PRIMARY KEY,
	"inserted_at" timestamp NOT NULL DEFAULT NOW(),
	"user_id" bigserial,
	"token" text
);

CREATE TABLE IF NOT EXISTS tasks(
	"id" bigserial PRIMARY KEY,
	"inserted_at" timestamp NOT NULL DEFAULT NOW(),
	"user_id" bigserial,
	"name" text NOT NULL,
	"description" text,
	"start_date" timestamp NOT NULL,
	"end_date" timestamp NOT NULL,
	"is_deleted" boolean DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS tags(
	"id" bigserial PRIMARY KEY,
	"name" text NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks_tags(
	"task_id" bigserial NOT NULL,
	"tag_id" bigserial NOT NULL
);


ALTER TABLE tasks ADD FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE verifications_email ADD FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE tasks_tags ADD CONSTRAINT pk_tasks_tags PRIMARY KEY (task_id, tag_id);
ALTER TABLE tasks_tags ADD FOREIGN KEY (task_id) REFERENCES tasks (id);
ALTER TABLE tasks_tags ADD FOREIGN KEY (tag_id) REFERENCES tags (id);
