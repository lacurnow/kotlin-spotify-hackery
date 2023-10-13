# Kotlin starter project #

There are two projects in this repository:

- `server` - The back-end server
- `client` - The front-end client

## Building and running the distribution ##

- `make run` 

This builds the client, copies the files into the server, builds the server, and then runs the application. Browse to
http://localhost:8080/ to view the UI.

## Running the server ##

To run while developing, run the `Application.kt` file in IntelliJ.

To run from the command-line:

- `cd server`
- `make run`

The server will run on `http://localhost:8080`.

## Running the client ##

To run while developing:

- `cd client`
- `make run`

The UI will open on `http://localhost:3000` and will auto-reload when you edit the code.
