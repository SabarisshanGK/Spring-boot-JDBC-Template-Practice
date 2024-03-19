ALTER TABLE customertable
ADD CONSTRAINT customer_unique_email  UNIQUE(email);