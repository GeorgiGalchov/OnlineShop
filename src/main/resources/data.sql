INSERT INTO users (id, active, email, first_name, last_name, password)
VALUES
    (2, 1, 'thexades@gmail.com', 'Georgi', 'Galchov', '809e1cb87c9f916c445b583e271514c890ec48e92312c7a7c31b282b18c51fa88b295ed41a831fd2a381e83d4af6ac33'),
    (3, 2, 'rabi123@abv.bg', 'Rabi', 'Lovchalieva', '5da6a5fd237ef255b6c6d6d02281b5722290e070d7851988ecca29035b21da103db80fbeb22cc0f799eeb8c0b9f6b7c4');



INSERT INTO roles (`id`, `role`)
VALUES
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users_roles(`user_id`, `role_id`)
VALUES
    (2, 1),
    (3, 2);

#
# INSERT INTO `brands` (`id`, `name`)
# VALUES
#     (1, 'Uaz452'),
#     (2, 'UazPatriot'),
#     (3, 'Uaz469');
#
# INSERT INTO `models` (`id`, `category`, `brand_id`, `name`)
# VALUES
#     (1, 'JEEP', 1, 'Uaz469'),
#     (2, 'CAR', 2, 'UazPatriot'),
#     (3, 'TRUCK', 2, 'Uaz452'),
#     (4, 'CAR', 2, 'UazPatriot'),
#     (5, 'CAR', 3, 'Uaz469');
#
# INSERT INTO `offers` (`id`, `description`, `engine`, `image_url`, `mileage`, `price`, `transmission`, `uuid`, `year`, `model_id`, `seller_id`)
# VALUES
#     (1, 'Uaz469 1!', 'GASOLINE', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/ParkPatriot2015part4-12.jpg/800px-ParkPatriot2015part4-12.jpg', 18000, 3600, 'MANUAL', 'e3df1b05-4fb7-42ff-b375-ec0f3f1f34c7', 1978, 5, 1),
#     (2, 'Uaz452 2!', 'DIESEL', 'https://cdn3.focus.bg/autodata/i/uaz/469/469/large/5c38f5abed574f5de36680eb95a42e6d.jpg', 26000, 5000, 'MANUAL', 'bcc7a448-c246-40ab-b4ca-cb6bf3a0a1fe', 1995, 5, 1),
#     (3, 'UazPatriot 2!', 'GAS', 'https://cdn3.focus.bg/autodata/i/uaz/3163-patriot/3163-patriot/large/72777959e1c498dd45d9f747167ad94d.jpg', 12000, 8500, 'MANUAL', '8db43426-9e86-4951-bb1c-8e590b9d5505', 2012, 5, 2),
#     (4, 'Uaz452 2!', 'DIESEL', 'https://www.shutterstock.com/shutterstock/photos/1253719936/display_1500/stock-photo-moscow-aug-uaz-presented-at-mias-moscow-international-automobile-salon-on-august-1253719936.jpg', 24000, 5500, 'MANUAL', '6e952060-e05c-4114-958a-b50bcf7c3b0e', 1991, 5, 2),
#     (5, 'Uaz469 1!', 'GAS', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/ParkPatriot2015part4-12.jpg/800px-ParkPatriot2015part4-12.jpg', 35000, 4500, 'MANUAL', '8a7ecae8-9726-471a-9bd1-a0b429db1e2a', 1999, 5, 2);