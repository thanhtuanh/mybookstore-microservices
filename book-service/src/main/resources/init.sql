DROP TABLE IF EXISTS books;

CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(2000),
    publication_date DATE,
    price DOUBLE PRECISION,
    pages INTEGER,
    genre VARCHAR(255),
    in_stock BOOLEAN
);

-- Beispiel-Bücher einfügen
-- Testdaten für Bücher
INSERT INTO books (title, author, isbn, description, publication_date, price, pages, genre, in_stock) VALUES
('Der Prozess', 'Franz Kafka', '9780000000001', 'Ein Mann wird grundlos verhaftet.', '1925-04-26', 9.95, 237, 'Klassische Literatur', FALSE),
('Clean Code', 'Robert C. Martin', '9780000000002', 'Ein Handbuch für sauberen Code.', '2008-08-01', 39.99, 464, 'Informatik', TRUE),
('Spring in Action', 'Craig Walls', '9780000000003', 'Spring Framework in der Praxis.', '2018-11-27', 44.90, 520, 'Informatik', TRUE),
('1984', 'George Orwell', '9780000000004', 'Dystopischer Roman über Überwachung.', '1949-06-08', 8.99, 328, 'Roman', TRUE),
('Die Verwandlung', 'Franz Kafka', '9780000000005', 'Ein Mann wacht als Käfer auf.', '1915-01-01', 7.99, 100, 'Klassische Literatur', TRUE),
('Angular für Einsteiger', 'Max Mustermann', '9780000000006', 'Einführung in Angular.', '2022-05-15', 29.99, 350, 'Informatik', TRUE),
('Java für Profis', 'Erika Beispiel', '9780000000007', 'Fortgeschrittene Java-Konzepte.', '2021-10-01', 49.90, 600, 'Informatik', TRUE),
('Der Alchimist', 'Paulo Coelho', '9780000000008', 'Philosophischer Roman über Träume.', '1988-04-15', 10.99, 190, 'Roman', TRUE),
('Siddhartha', 'Hermann Hesse', '9780000000009', 'Eine indische Dichtung.', '1922-01-01', 9.50, 152, 'Spiritualität', TRUE),
('Effektives Arbeiten', 'Lisa Produktiv', '9780000000010', 'Tipps für produktives Arbeiten.', '2020-01-01', 19.95, 210, 'Ratgeber', TRUE),
('Einführung in Python', 'Autor A', '9780000000011', 'Beschreibung für Einführung in Python.', '2016-04-18', 51.34, 376, 'Informatik', TRUE),
('Datenanalyse mit Pandas', 'Autor B', '9780000000012', 'Beschreibung für Datenanalyse mit Pandas.', '2016-06-30', 53.11, 760, 'Informatik', TRUE),
('Machine Learning Basics', 'Autor C', '9780000000013', 'Beschreibung für Machine Learning Basics.', '2014-12-30', 35.35, 499, 'Informatik', FALSE),
('Deep Learning mit TensorFlow', 'Autor D', '9780000000014', 'Beschreibung für Deep Learning mit TensorFlow.', '2020-05-02', 14.95, 616, 'Informatik', TRUE),
('Agile Softwareentwicklung', 'Autor E', '9780000000015', 'Beschreibung für Agile Softwareentwicklung.', '2014-07-21', 47.77, 399, 'Informatik', TRUE),
('Design Patterns', 'Autor F', '9780000000016', 'Beschreibung für Design Patterns.', '2020-09-17', 37.09, 777, 'Informatik', FALSE),
('Künstliche Intelligenz', 'Autor G', '9780000000017', 'Beschreibung für Künstliche Intelligenz.', '2016-05-15', 14.69, 624, 'Informatik', TRUE),
('Programmieren mit Kotlin', 'Autor H', '9780000000018', 'Beschreibung für Programmieren mit Kotlin.', '2019-12-25', 27.97, 267, 'Informatik', TRUE),
('Docker für Entwickler', 'Autor I', '9780000000019', 'Beschreibung für Docker für Entwickler.', '2017-12-24', 55.84, 594, 'Informatik', FALSE),
('Microservices mit Spring Boot', 'Autor J', '9780000000020', 'Beschreibung für Microservices mit Spring Boot.', '2020-08-18', 53.32, 324, 'Informatik', TRUE),
('Embedded Systems', 'Autor Y', '9780000000049', 'Beschreibung für Embedded Systems.', '2013-10-04', 33.62, 371, 'Informatik', TRUE),
('Compilerbau', 'Autor Z', '9780000000050', 'Beschreibung für Compilerbau.', '2013-02-10', 59.44, 731, 'Informatik', TRUE);
