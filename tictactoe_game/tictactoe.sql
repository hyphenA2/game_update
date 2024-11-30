CREATE DATABASE tic_tac_toe;
USE tic_tac_toe;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    code VARCHAR(20) NOT NULL,
    birth_date DATE,
    games_played INT DEFAULT 0
);

CREATE TABLE games (
    game_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    score INT,
    game_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE VIEW top_scores AS
SELECT name, MAX(score) AS score
FROM users
JOIN games ON users.user_id = games.user_id
GROUP BY users.user_id
ORDER BY score DESC
LIMIT 10;

use tic_tac_toe;
select * from users;


use tic_tac_toe;
ALTER TABLE users
CHANGE COLUMN code password VARCHAR(20) NOT NULL;
