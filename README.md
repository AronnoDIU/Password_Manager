# Password Manager

This is a simple password manager implemented in Java. It allows users to store and retrieve passwords securely. The
passwords are hashed using SHA-256 before being stored.

## Table of Contents

- [Features](#features)
- [How to Run](#how-to-run)
- [Usage](#usage)
- [Note](#note)
- [License](#license)

## Features

- Store Password: Allows the user to store a password for a specific account. The password is hashed before being
  stored.
- Retrieve Password: Allows the user to retrieve a stored password for a specific account. The user must provide the
  correct password for the account.
- Exit: Exit the program. The stored passwords are not saved to a file.

## How to Run

1. Compile the `PasswordManager.java` file.
2. Run the `PasswordManager` class.
3. Follow the on-screen instructions.

## Usage

The program presents a menu with the following options:

1. Store Password: Enter the account name and password to store.
2. Retrieve Password: Enter the account name and password to retrieve the stored password.
3. Exit: Exit the program.

## Note

This is a simple password manager and should not be used for storing sensitive information in a production environment.

## License

This project is licensed under the MIT License.