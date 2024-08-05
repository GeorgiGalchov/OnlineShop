INSERT INTO users (id, active, email, first_name, last_name, password)
VALUES
    (1, 1, 'thexades@gmail.com', 'Georgi', 'Galchov', '7958f519e2040ec60c6051585f9c73ac528adbbfb2f1fc4d806ca6bca260216fd961b02a47adb7b5ec4581277c91f834'),
    (2, 2, 'rabi123@abv.bg', 'Rabi', 'Lovchalieva', '''698008da2a29af1c47a131d5461b375bfdfee823d02f63d1ff1b7b1283d446b79d606965b10884cbd1a8479cad41205d''');



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
    (1, 'Uaz-452'),
    (2, 'UazPatriot'),
    (3, 'Uaz-469');

INSERT INTO `models` (`id`, `category`, `brand_id`, `name`)
VALUES
    (1, 'JEEP', 1, 'Uaz'),
    (2, 'CAR', 1, 'UazPatriot'),
    (3, 'TRUCK', 2, 'Uaz-452'),
    (4, 'CAR', 2, 'UazPatriot'),
    (5, 'CAR', 3, 'Uaz-469');

INSERT INTO `offers` (`id`, `description`, `engine`, `image_url`, `mileage`, `price`, `transmission`, `uuid`, `year`, `model_id`, `seller_id`)
VALUES
    (1, 'Uaz-469 1!', 'GASOLINE', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/ParkPatriot2015part4-12.jpg/800px-ParkPatriot2015part4-12.jpg', 18000, 3600, 'MANUAL', 'e3df1b05-4fb7-42ff-b375-ec0f3f1f34c7', 1978, 5, 1),
    (2, 'Uaz-469 1!', 'DIESEL', 'https://cdn3.focus.bg/autodata/i/uaz/469/469/large/5c38f5abed574f5de36680eb95a42e6d.jpg', 26000, 5000, 'MANUAL', 'bcc7a448-c246-40ab-b4ca-cb6bf3a0a1fe', 1995, 5, 1),
    (3, 'Uaz-3163 2!', 'GAS', 'https://cdn3.focus.bg/autodata/i/uaz/3163-patriot/3163-patriot/large/72777959e1c498dd45d9f747167ad94d.jpg', 12000, 8500, 'MANUAL', '8db43426-9e86-4951-bb1c-8e590b9d5505', 2012, 5, 2),
    (4, 'Uaz-452 2!', 'DIESEL', 'https://www.shutterstock.com/shutterstock/photos/1253719936/display_1500/stock-photo-moscow-aug-uaz-presented-at-mias-moscow-international-automobile-salon-on-august-1253719936.jpg', 24000, 5500, 'MANUAL', '6e952060-e05c-4114-958a-b50bcf7c3b0e', 1991, 5, 2),
    (5, 'Uaz-461 2!', 'GAS', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/ParkPatriot2015part4-12.jpg/800px-ParkPatriot2015part4-12.jpg', 35000, 4500, 'MANUAL', '8a7ecae8-9726-471a-9bd1-a0b429db1e2a', 1999, 5, 2);