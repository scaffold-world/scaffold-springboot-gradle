CREATE DATABASE scaffold_db DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

GRANT ALL ON scaffold_db.* TO 'scaffold_rw'@'%';
