CREATE TABLE persistent_logins
(
   username varchar(64) NOT NULL,
   series varchar(64) PRIMARY KEY NOT NULL,
   token varchar(64) NOT NULL,
   last_used timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL
)
;
CREATE UNIQUE INDEX PRIMARY ON persistent_logins(series)
;
