CREATE TABLE IF NOT EXISTS tv (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    screen_technology VARCHAR(100) NOT NULL,
    screen_diagonal DOUBLE PRECISION NOT NULL,
    price DOUBLE PRECISION NOT NULL
);

INSERT INTO tv (brand, model, screen_technology, screen_diagonal, price) VALUES
('Samsung', 'QE55Q60A', 'QLED', 55.0, 65000.0),
('LG', 'OLED55C2', 'OLED', 55.0, 115000.0),
('Sony', 'KD-43X81J', 'LED', 43.0, 48000.0)
ON CONFLICT DO NOTHING;