
CREATE DATABASE playground;
CREATE USER devtools WITH PASSWORD 'DEVELOPMENT';

GRANT ALL PRIVILEGES ON DATABASE playground TO devtools;
GRANT ALL ON SCHEMA public TO devtools;
GRANT USAGE ON SCHEMA public TO devtools;