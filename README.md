# Accomatch Application
## Introduction
The "Accomatch" system is a web and mobile-based application connecting individuals seeking accommodations (HouseSeekers) with those offering properties for rent (Leaseholders). The system includes features for user customization, filtering, and matching to streamline the accommodation search process globally.

## Technology Stack

### Programming Languages
Java, JavaScript, HTML, CSS

## Frameworks and Libraries

### Front-end - React
A popular JavaScript library for building user interfaces. It allows you to create reusable UI components and manage the application's state efficiently.

### Back-end - Spring Boot
A framework for building Java-based web applications quickly and with minimal configuration. It provides a set of features, including dependency injection, security, and web services, making it easier to develop robust back-end applications.

### Database - MySQL
An open-source relational database management system (RDBMS) widely used for storing and managing structured data.

### Tools & Technology
- UI/UX: User Interface and User Experience design
- Testing: The process of validating and verifying that your application functions as expected.
- Bootstrap: A front-end CSS framework
- CSS: Cascading Style Sheets
- JavaScript: A programming language used for client-side scripting
- JUnit5: A popular testing framework for Java applications
- Mockito: A Java-based mocking framework
- Postman: A popular API testing tool used to test and interact with APIs
  
## Stakeholder Identification
Stakeholders include users, development teams, product managers, customer support, marketing, data security, compliance teams, financial teams, and third-party service providers. Addressing their needs is essential for a successful Accomatch platform.

## Architecture and Component Design
### High-Level Architecture
The system facilitates accommodation search between Leaseholders and HouseSeekers. It includes modules for user management, reviews, ratings, dashboards, chat, and administrative actions.

### User Module
Handles user-related endpoints for signup, login, user information retrieval, and password reset. Interacts with UserService and MailSenderClass.

### Review Module
Manages endpoints for posting and retrieving reviews for user applications. Interacts with ReviewService.

### Post Ratings View Module
Provides endpoints to retrieve reviews and average ratings for a specific application post. Interacts with ReviewServiceImplementation.

### Lease Posts Logged In Module
Manages endpoints to retrieve posts for a logged-in leaseholder applicant. Interacts with LeasePostsLoggedinService.

### LeaseHolder Dashboard Controller
Handles endpoints for the leaseholder dashboard, retrieving posts, post details, images, food preferences, and gender preferences. Interacts with LeaseHolderDashboardInterface.

### HouseSeeker Controller
Provides an endpoint to retrieve a list of all applicant posts. Interacts with HouseSeekerService.

### Create Application Controller
Handles endpoints for creating applications for house seekers. Interacts with CreateApplicationFactory.

### Chat Room Controller
Manages endpoints related to chat rooms, retrieving room IDs based on user and application IDs. Interacts with ChatRoomService.

### ChatController
Handles endpoints for sending and retrieving chat messages in a chat room. Interacts with ChatService.

### ApplyonPostController
Manages endpoints for leaseholder applicants to apply on posts and check application status. Interacts with ApplyonPostService.

### ApplicantPostFiltering
Provides an endpoint for filtering posts based on applicant preferences. Interacts with ApplicantPostFilterService.

### AdminController
Handles endpoints for administrative actions, verifying individual ads, and fetching posts for admin based on status. Interacts with AdminInterface and LeaseHolderDashboardInterface.

## Interface Design

### Internal Interfaces

#### User Registration and Authentication

When a user registers on the website, their information, such as email, name, password, age, gender, etc., is stored in the "User" table in the database using an SQL INSERT statement. During the authentication process, the backend server checks the provided credentials against the "User" table to verify the user's identity and grant access to specific features based on flags like "is_admin" or "is_leaseholder."

#### Leaseholder and HouseSeeker Ads Creation

Leaseholders and house seekers create advertisements on the website. When they submit an ad form, the backend server processes the information and inserts the ad details into the "Leaseholder Ads" or "HouseSeeker Ads" table, respectively. The user_id associated with the ad is fetched from the user's session, which was established during the authentication process, to link the ad with the corresponding user.

#### Preference and Image Uploading

Leaseholders and house seekers can upload preferences, images, and other details related to their ads. This information is stored in tables like "Leaseholder Food Preferences," "Leaseholder Images," "Leaseholder Gender Preferences," "HouseSeeker Food Preferences," and "HouseSeeker Gender Preferences." The backend server receives the uploaded data, validates it, and inserts the information into the appropriate tables, linking them to the corresponding leaseholder or house seeker ad using application_id.

#### Applicant Management

When a user applies for a leaseholder or house seeker ad, the backend server creates a new entry in either the "Leaseholder Applicant" or "HouseSeeker Applicant" table, associating the user_id and application_id accordingly. The status of the application (e.g., pending, approved, rejected) is updated in the respective table entry based on the leaseholder or house seeker's response.

#### Chat Room and Messaging

Users can communicate with each other through the website's chat feature. When a user sends a message, the backend server stores the message in the "Chat" table along with the user_id and the corresponding room_id. The backend server manages the creation and retrieval of chat rooms, associating them with user_1_id and user_2_id in the "Room" table.

#### Rating and Reviews

Users can rate and review leaseholder ads. When a user submits a rating and review, the backend server inserts the data into the "Rating" table, associating the user_id and application_id accordingly.

#### Leaseholder Current Residents

The "Leaseholder Current Residents" table maintains the list of current residents for each leaseholder property. When a user becomes a resident of a leaseholder property, the backend server adds a new entry in the table, linking the user_id with the application_id.

### External Interfaces

#### User Interactions with the Website

##### User Registration and Authentication

Users interact with the website by registering an account and logging in. The website provides forms for user registration and login. When a user submits the registration form, the backend server processes the data, and if successful, the user is granted access to the website's features. During login, the system verifies the user's credentials against the data stored in the "User" table to authenticate them.

##### Creating and Managing Ads

Leaseholders and house seekers interact with the website to create ads for their respective requirements. They fill out forms with ad details, preferences, and images. Upon submission, the backend server processes the data and inserts it into the appropriate tables. Users can also manage their existing ads, update details, or remove ads.

##### Applying for Ads

House seekers can apply for leaseholder ads through the website. When a user submits an application, the backend server creates an entry in the "HouseSeeker Applicant" table, linking the user_id and the application_id.

##### Chat and Messaging

Users can communicate with each other through the website's chat feature. When a user sends a message, the frontend sends the message data to the backend server via APIs. The server processes the message and stores it in the "Chat" table, associating it with the user_id and the room_id.

##### Rating and Reviews

Users can provide ratings and reviews for leaseholder ads. When a user submits a rating and review through the frontend, the backend server processes the data and inserts it into the "Rating" table, associating the user_id and the application_id.

##### Viewing Current Residents

Leaseholders can view the current residents of their property through the website. The backend retrieves the data from the "Leaseholder Current Residents" table and displays it to the leaseholder.

#### Interactions with Other Systems

##### User Authentication

The website is integrated with external authentication systems, such as OAuth or Single Sign-On (SSO), to provide users with various options for authentication.

##### Notifications

The system can interact with external notification services (e.g., email) to send users notifications about important events, such as application approvals and changes in ad status.

##### Analytics and Monitoring

The system may be integrated with analytics and monitoring tools to track website usage, user behavior, and performance metrics. This data can help improve the website's functionality and user experience.

## Testing Strategy

## Unit Testing

### Understanding Requirements
We begin by thoroughly understanding the requirements and specifications for each component we are going to develop.

### Writing Test Cases
With the requirements in hand, we write test cases for each component, considering different scenarios, edge cases, and potential error conditions.

### Test-Driven Development (TDD)
Sometimes, we adopt the Test-Driven Development approach, where we write the test cases first and then develop the component to make those tests pass.

### Mocking and Stubbing Dependencies
During testing, we isolate the component from its dependencies using mocking and stubbing techniques.

### Running Tests Frequently
We regularly run the test suite while developing the component to catch issues early and provide immediate feedback.

### Debugging and Fixing Issues
When a test case fails, we immediately investigate the cause of the failure using debugging tools and logs.

### Regression Testing
Whenever we make changes to the component or introduce new features, we rerun the entire test suite, including all previously passing test cases.

### Continuous Integration (CI)
In a CI environment, our test suite is automatically run whenever code changes are committed.

### Maintaining Test Suite
As the application evolves, we update the test suite to accommodate new features and changes.

## Integration Testing

Integration testing ensures that different components of a software application work together correctly.


## System Testing

System testing involves evaluating the entire software application to ensure that all individual components work together as expected and meet the overall requirements.

## User Acceptance Testing

During user testing, the system's complete application flow would be evaluated to ensure it meets the users' needs.


## Conclusion:

The Accomatch project is a robust and innovative accommodation-finding solution. With a focus on user guidance, collaboration, and customization, the platform offers a seamless experience for both leaseholders and house seekers.

### Key Achievements:
- **User-Centric Approach:** Prioritized user experience and accessibility.
- **Technological Advancements:** Integrated real-time availability, analytics, and multi-language support.
- **Future-Ready Enhancements:** Proposed features like AR, machine learning, and blockchain for ongoing innovation.

### Next Steps:
Continued evolution through mobile app development, UI/UX overhaul, and advanced search options. Accomatch is poised for sustained success by adapting to industry trends and embracing emerging technologies.

*Explore Accomatch for a modern, secure, and user-friendly accommodation search experience!*

