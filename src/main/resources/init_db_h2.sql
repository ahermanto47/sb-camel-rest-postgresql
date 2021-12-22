CREATE TABLE IF NOT EXISTS member (
  id SERIAL,
  firstname VARCHAR(30),
  lastname VARCHAR(30),
  CONSTRAINT pk_vets PRIMARY KEY (id)
);
