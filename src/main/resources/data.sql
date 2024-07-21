INSERT INTO users (id, active, email, first_name, last_name, password)
VALUES



INSERT INTO roles (`id`, `role`)
VALUES
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users_roles(`user_id`, `role_id`)
VALUES
    (1, 1),
    (1, 2),
    (2, 2);


INSERT INTO `brands` (`id`, `name`)
VALUES


INSERT INTO `models` (`id`, `category`, `brand_id`, `name`)
VALUES


INSERT INTO `offers` (`id`, `description`, `engine`, `image_url`, `mileage`, `price`, `transmission`, `uuid`, `year`, `model_id`, `seller_id`)
VALUES
