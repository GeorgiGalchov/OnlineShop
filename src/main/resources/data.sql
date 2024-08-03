INSERT INTO users (id, active, email, first_name, last_name, password)
VALUES
    (1, 1, 'admin@example.com', 'Admin', 'Adminov', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151'),
    (),
    ();


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
    (1),
    (2),
    (3);

INSERT INTO `models` (`id`, `category`, `brand_id`, `name`)
VALUES
    (1),
    (2),
    (3),
    (4),
    (5);

INSERT INTO `offers` (`id`, `description`, `engine`, `image_url`, `mileage`, `price`, `transmission`, `uuid`, `year`, `model_id`, `seller_id`)
VALUES