INSERT INTO vendors (id, name, contact_name, phone, email, address, city, state, postal_code, country, active, created_at, updated_at)
VALUES
  (1, 'Fresh Farm Supplies', 'Anita Rao', '9876543210', 'anita@freshfarm.example', '12 Market Road', 'Bengaluru', 'Karnataka', '560001', 'India', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (2, 'City Hardware', 'Rahul Mehta', '9123456780', 'rahul@cityhardware.example', '44 Industrial Area', 'Mumbai', 'Maharashtra', '400001', 'India', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (3, 'Coastal Spices', 'Leena Dsouza', '9988776655', 'leena@coastalspices.example', '7 Beach Street', 'Mangaluru', 'Karnataka', '575001', 'India', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Reset sequence/identity if needed (H2 specific). For IDENTITY, H2 auto-handles; explicit set not required.
