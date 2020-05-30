INSERT INTO parking_place_entity VALUES
(1, 'A-1', 'AVAILABLE'),
(2, 'A-2', 'BUSY'),
(3, 'A-3', 'AVAILABLE')
ON CONFLICT DO NOTHING;