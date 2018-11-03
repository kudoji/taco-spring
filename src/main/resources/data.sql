delete from tacos_ingredients;
delete from orders_tacos;

delete from ingredients;
delete from tacos;
delete from orders;

insert into ingredients (id, name, type) values
    ('FLTO', 'Flour Tortilla', 'WRAP'),
    ('COTO', 'Corn Tortilla', 'WRAP'),
    ('GRBF', 'Ground Beef', 'PROTEIN'),
    ('CARN', 'Carnitas', 'PROTEIN'),
    ('TMTO', 'Diced Tomatoes', 'VEGGIES'),
    ('LETC', 'Lettuce', 'VEGGIES'),
    ('CHED', 'Cheddar', 'CHEESE'),
    ('JACK', 'Monterrey Jack', 'CHEESE'),
    ('SLSA', 'Salsa', 'SAUCE'),
    ('SRCR', 'Sour Cream', 'SAUCE');
