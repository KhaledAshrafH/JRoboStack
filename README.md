# JRoboStack

It's a comprehensive testing project aimed at thoroughly testing a Todo application. It includes testing for both the backend and frontend components. The project is built using a Spring Boot backend server and an HTML/JavaScript frontend.

## Project Overview

The Todo application is designed to help users manage their tasks by providing a user-friendly interface to add, delete, and update tasks. The goal of this testing project is to ensure that the application functions correctly, handles user inputs accurately, and maintains data integrity.

The testing project covers both the backend and frontend aspects to provide full coverage and ensure a robust and reliable application. The backend server is responsible for handling data storage, retrieval, and processing, while the frontend provides the user interface and interacts with the backend API.

## Project Structure

The project is organized into the following components:

- **Software-Testing-Backend**: Contains the Spring Boot application that serves as the backend for the Todo application.
- **Software-Testing-Frontend**: Contains the HTML and JavaScript files for the frontend of the Todo application.
- **Software-Testing-PostmanCollection**: Contains a Postman collection with all the necessary requests for testing the backend API.

## Backend Testing

The backend testing focuses on verifying the correctness and stability of the Spring Boot application that powers the Todo application. It includes unit testing using JUnit that achieved a test coverage of **85%** for the codebase.

### Coverage Techniques

To ensure effective coverage, the following techniques will be employed:

- **Graph Coverage**: The test cases will cover all possible paths and states within the application, ensuring that no code paths are left untested.
- **Data Flow Coverage**: Different data flow scenarios will be tested to ensure that the application processes and handles data correctly.

### Running Backend Tests

To run the backend tests, follow these steps:

1. Clone the repository: `git clone https://github.com/KhaledAshrafH/JRoboStack.git`
2. Navigate to the `SoftwareTesting` directory: `cd Software-Testing-Backend\src\test\java\com\fcai\SoftwareTesting`
3. Run the SoftwareTestingApplicationTests java file.

## Frontend Testing

The frontend testing focuses on validating the user interface and functionality of the Todo application from the end-user perspective. Robot Framework, a powerful and extensible test automation framework, will be utilized for writing and executing the frontend test cases.

### Functionality to be Tested

The following functionality of the application will be tested:

- **Add new TODO**: Verifying that a user can add a new TODO and, after submission, it is added to the table, and all text inputs are cleared.
- **Delete**: Verifying that a user can delete a TODO, and it is successfully removed from the table.
- **Update completion**: Verifying that a user can update the completion status of a TODO by clicking the checkbox, and the table displays it correctly.
- **Get all TODOs**: Verifying that all TODOs are displayed correctly in the table.
- **Get completed TODOs**: Verifying that the table only displays completed TODOs.

### Running Frontend Tests

To run the frontend tests, follow these steps:

1. Ensure you have the necessary dependencies installed (Python < 3.8, Robot Framework, SeleniumLibrary, etc.).
2. Clone the repository: `git clone https://github.com/KhaledAshrafH/JRoboStack.git`
3. Navigate to the `Software-Testing-Frontend` directory: `cd Software-Testing-Frontend`
4. Run the tests: `robot Todo.robot`

## Contributing

Contributions to this project are welcome. If you find any issues or have suggestions for improvements, please feel free to open an issue or submit a pull request.

## Team

This Project was created by a team of four computer science students at Faculty of Computers and Artificial Intelligence Cairo University. The team members are:

- [Khaled Ashraf Hanafy Mahmoud - 20190186](https://github.com/KhaledAshrafH).
- [Kareem Galal Zaki Sayed - 20190383](https://github.com/kareemgalall).
- [Samah Moustafa Hussien Mahmoud - 20190248](https://github.com/Samah-20190248).
- [Ayatullah Esam El-din Mohamed - 20190123](https://github.com/oshaesam1).

## Acknowledgment

This Project is based on Software Testing and Quality Assurance Course at Faculty of Computers and Artificial Intelligence Cairo University. We would like to thank Dr. Desouky Abd ElQawy for his guidance and support throughout this course.

## License

The Project is released under the [MIT License](LICENSE.md). You are free to use, modify, and distribute the toolbox in accordance with the terms of this license.
