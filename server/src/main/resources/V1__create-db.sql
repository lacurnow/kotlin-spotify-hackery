DROP TABLE IF EXISTS tracks;

CREATE TABLE tracks (
  id VARCHAR(255) NOT NULL,
  track_name VARCHAR(255) NOT NULL,
  artist_name VARCHAR(255) NOT NULL,
  streams INT NOT NULL,
  PRIMARY KEY (id)
);