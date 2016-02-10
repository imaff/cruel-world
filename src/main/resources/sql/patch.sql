CREATE TABLE `users` (
  `id`       BIGINT(100)  NOT NULL,
  `login`    VARCHAR(255) NOT NULL,
  `email`    VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL
);
ALTER TABLE `users` ADD PRIMARY KEY (`id`);
CREATE TABLE `rooms` (
  `id`   BIGINT(100)  NOT NULL,
  `name` VARCHAR(255) NOT NULL
);
ALTER TABLE `rooms` ADD PRIMARY KEY (`id`);

CREATE TABLE `history` (
  `id`      BIGINT(100) NOT NULL,
  `room_id` BIGINT(100) NOT NULL,
  `login` VARCHAR(255) NOT NULL,
  `message` TEXT        NOT NULL,
  `date`    TIMESTAMP   NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP
);
ALTER TABLE `history` ADD PRIMARY KEY (`id`);


INSERT INTO rooms SET id = 0, name = 'Main';
