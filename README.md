# sql_musicmarketplaceFactoryPattern

Single Responsibility Principle (SRP): The IUserDAO interface and IUserProfileDAO interface and the rest of the IDAO interfaces define specific methods related to user data access and user profile data access, respectively. Each interface has a clear responsibility, focusing on a single aspect of the system's functionality.

Open/Closed Principle (OCP): The AbstractFactory class is open for extension by providing abstract factory methods for each DAO interface. It is closed for modification since adding new types of DAOs can be done by creating new implementations of the corresponding interfaces without modifying the existing code.

Dependency Inversion Principle (DIP): The code satisfies the DIP by depending on abstractions (interfaces) rather than concrete classes. The AbstractFactory class declares abstract factory methods that return interface types (e.g., IUserDAO, IUserProfileDAO). The DatabaseDAOFactory class implements these methods by returning concrete implementations (UserDAO, UserProfileDAO), which allows for loose coupling and easier substitution of different implementations.

Interface Segregation Principle (ISP): The interfaces IUserDAO and IUserProfileDAO and the rest of the IDAO interfaces follow the ISP by defining methods that are specific to their respective responsibilities. Each interface contains only the methods required for its purpose, avoiding the need to implement unnecessary methods in implementing classes.

Overall this project adheres to the SOLID principles, specifically the SRP, OCP, DIP, and ISP. 

It promotes better separation of concerns, modularity, and flexibility in terms of adding new DAO types and substituting implementations