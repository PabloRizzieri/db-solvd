use solvd_task;
/*
-- 10 INSERTS --
INSERT INTO supermarkets(supermarket_name) values("Walmart");
INSERT INTO equipments(name, supermarket_id) values ("Cash Register", 1);
INSERT INTO departments(task, name, supermarket_id) values ("Warehouse Cleaning", "Deposit Department", 1);
INSERT INTO supermarket_providers(supermarket_id, provider_id) values(1, 1);
INSERT INTO providers(provider_name, provider_branch) values("Meat Supplier", "Meat");
INSERT INTO products(supermarket_id, provider_id, product_name, category, price) values(1, 1, "Beef", "Meats", 25);
INSERT INTO products(supermarket_id, provider_id, product_name, category, price) values(1, 1, "Apple", "Fruits", 9);
INSERT INTO products(supermarket_id, provider_id, product_name, category, price) values(1, 1, "Soap", "Cleanings", 8);
INSERT INTO products(supermarket_id, provider_id, product_name, category, price) values(1, 1, "Chocolate Cereal", "Cereals", 9);
INSERT INTO meats_sector(type, expiration_date, product_id, product_supermarket_id) values("Beef", "22-05-2024", 1, 1);
INSERT INTO fruits_sector(type, product_id, product_supermarket_id) values("Apple", 2, 1);
INSERT INTO cereal_sector(flavour, product_id, products_supermarket_id) values("Chocolate", 4, 1);
INSERT INTO cleanings_sector(type, product_id, product_supermarket_id) values("Body Soap", 3, 1);
INSERT INTO employees(first_name, last_name, age, supermarket_id, department_id) values("John", "Smith", 25, 1, 1);
INSERT INTO supervisors(first_name, last_name, employee_id, department_id, employee_supermarket_id) values("John", "Smith", 1, 1, 1);

*/




/*
-- UPDATE STATEMENTS --

UPDATE products SET price = 29 WHERE product_name = "Beef";
UPDATE supermarkets SET supermarket_name = "VEA" WHERE supermarket_name = "Jumbo";
UPDATE supermarkets SET supermarket_name = "ChangoMas" WHERE supermarket_name = "Walmart";
UPDATE supermarkets SET supermarket_name = "Dia" WHERE supermarket_name = "Coto";
UPDATE meats_sector SET expiration_date = "28-06-2024" WHERE type = "Beef";
UPDATE employees SET supermarket_id = 3 WHERE first_name = "Teresa";
UPDATE employees SET supermarket_id = 1 WHERE first_name = "Mark";
UPDATE employees SET age = 23 WHERE first_name = "John";
UPDATE departments SET task = "Cleaning" WHERE task = "Warehouse Cleaning";
UPDATE departments SET task = "Shipment Express" WHERE task = "Shipment";


*/


/*
-- DELETE STATEMENTS --

DELETE from products WHERE price = 29;
DELETE from supermarkets WHERE supermarket_name = "VEA";
DELETE from supermarkets WHERE supermarket_name = "ChangoMas";
DELETE from supermarkets WHERE supermarket_name = "Dia";
DELETE from meats_sector WHERE type = "Beef";
DELETE from employees WHERE first_name = "Teresa";
DELETE from employees WHERE supermarket_id = 1;
DELETE from employees WHERE last_name = "Smith";
DELETE from departments WHERE task = "Cleaning";
DELETE from departments WHERE task = "Shipment Express";

*/

/*
ALTER TABLES

ALTER TABLE `solvd_task`.`cereals_sector`
RENAME TO  `solvd_task`.`cereal_sector` ;

ALTER TABLE `solvd_task`.`cereal_sector`
RENAME TO  `solvd_task`.`cereals_sector` ;

ALTER TABLE `solvd_task`.`cereals_sector`
DROP FOREIGN KEY `fk_cereal_sector_products`;

ALTER TABLE `solvd_task`.`cereals_sector`
CHANGE COLUMN `products_supermarket_id` `product_supermarket_id` BIGINT UNSIGNED NOT NULL ;

ALTER TABLE `solvd_task`.`cereals_sector`
ADD CONSTRAINT `fk_cereal_sector_products`
  FOREIGN KEY (`product_id` , `product_supermarket_id`)
  REFERENCES `solvd_task`.`products` (`id` , `supermarket_id`)
  ON DELETE CASCADE;

*/




/*
BIG JOIN

SELECT *
FROM supermarkets
JOIN employees ON supermarkets.id = employees.supermarket_id
JOIN supervisors ON supermarkets.id = supervisors.employee_supermarket_id
JOIN departments ON supermarkets.id = departments.supermarket_id
JOIN products ON supermarkets.id = products.supermarket_id
JOIN equipments ON supermarkets.id = equipments.supermarket_id
JOIN meats_sector ON supermarkets.id = meats_sector.product_supermarket_id
JOIN fruits_sector ON supermarkets.id = fruits_sector.product_supermarket_id
JOIN cereals_sector ON supermarkets.id = cereals_sector.products_supermarket_id
JOIN cleanings_sector ON supermarkets.id = cleanings_sector.product_supermarket_id
JOIN supermarket_providers ON supermarkets.id = supermarket_providers.supermarket_id
JOIN providers ON supermarket_providers.provider_id = providers.id


*/

/*
Left, Right, Inner, Outer joins.

SELECT *
FROM solvd_task.employees AS emp
INNER JOIN solvd_task.departments AS dep ON emp.department_id = dep.id;

SELECT *
FROM solvd_task.employees AS emp
LEFT JOIN solvd_task.departments AS dep ON emp.department_id = dep.id;

SELECT *
FROM solvd_task.employees AS emp
RIGHT JOIN solvd_task.departments AS dep ON emp.department_id = dep.id;

SELECT *
FROM solvd_task.employees AS emp
CROSS JOIN solvd_task.departments AS dep;


*/

/*

Statements with aggregate functions and group by and without having.

SELECT category, SUM(price) AS total_price
FROM solvd_task.products
GROUP BY category;

SELECT department_id, AVG(age) AS avg_age
FROM solvd_task.employees
GROUP BY department_id;

SELECT supermarket_id, COUNT(*) AS product_count
FROM solvd_task.products
GROUP BY supermarket_id;

SELECT provider_id, MAX(price) AS max_price
FROM solvd_task.products
GROUP BY provider_id;

SELECT supermarket_id, department_id, COUNT(*) AS employee_count
FROM solvd_task.employees
GROUP BY supermarket_id, department_id;

SELECT supermarket_id, MIN(age) AS min_age
FROM solvd_task.employees
GROUP BY supermarket_id;

SELECT supermarket_id, category, SUM(price) AS total_price, COUNT(*) AS product_count
FROM solvd_task.products
GROUP BY supermarket_id, category;

*/




/*
Statements with aggregate functions and group by and with having.

SELECT supermarket_id, COUNT(*) AS employee_count
FROM solvd_task.employees
GROUP BY supermarket_id
HAVING employee_count > 0;

SELECT provider_id, MAX(price) AS max_price
FROM solvd_task.products
GROUP BY provider_id
HAVING max_price > 24;

SELECT department_id, AVG(age) AS avg_age
FROM solvd_task.employees
GROUP BY department_id
HAVING avg_age > 24;

SELECT category, COUNT(*) AS product_count
FROM solvd_task.products
GROUP BY category
HAVING product_count > 0;

SELECT s.id AS supermarket_id, s.supermarket_name, COUNT(e.id) AS equipment_count
FROM solvd_task.supermarkets s
LEFT JOIN solvd_task.equipments e ON s.id = e.supermarket_id
GROUP BY s.id, s.supermarket_name
HAVING equipment_count > 0;

SELECT supermarket_id, COUNT(DISTINCT provider_id) AS distinct_provider_count
FROM solvd_task.products
GROUP BY supermarket_id
HAVING distinct_provider_count > 1;


*/