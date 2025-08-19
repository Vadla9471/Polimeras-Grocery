Project Type: E-commerce platform for grocery shopping with vendor and admin roles.

Tech Stack: Java, Spring Boot, MySQL, Thymeleaf, Spring Security, PayPal SDK, Lombok, iTextPDF

Architecture: The application follows a layered architecture with controllers, services, repositories, entities, and DTOs. Spring Boot handles dependency injection and auto-configuration. Spring Security is used for authentication and authorization. Thymeleaf is the template engine for rendering dynamic web pages. MySQL is used as the database. PayPal SDK is integrated for payment processing. The application interacts with the database through JPA repositories.

Key Features:
- User registration and authentication
- Product catalog with categories
- Shopping cart functionality
- Checkout process with online payments (PayPal)
- Admin and vendor dashboards
- Order management
- User profile management
- Email notifications (registration, order confirmation, password reset)


1. User Authentication and Authorization:
   - IMPLEMENT: Basic user registration, login, logout using Spring Security with in-memory user details or a simplified database user table. Implement role-based access control (customer, vendor, admin).
   - OMIT: Social login, multi-factor authentication, and password complexity rules.
   - INSTRUCTIONS: Use Spring Security's default configuration for authentication and authorization. Create simple user roles (customer, vendor, admin) and configure access to different parts of the application based on these roles.

2. Product Catalog:
   - IMPLEMENT: A simplified product entity with fields like name, description, price, and category using JPA. Implement basic CRUD operations for products with minimal attributes. Display a list of products with pagination.
   - OMIT: Product reviews, ratings, detailed specifications, and complex filtering/sorting options.
   - INSTRUCTIONS: Create a Product entity and a ProductRepository. Implement simple REST endpoints for creating, reading, updating, and deleting products.

3. Shopping Cart and Checkout:
   - IMPLEMENT: A simple shopping cart functionality using session storage. Implement a basic checkout process with only one payment option (e.g., cash on delivery).
   - OMIT: Persistent cart storage (database), multiple payment options (PayPal, credit card), shipping calculations, and discount codes.
   - INSTRUCTIONS: Use Spring Session to store the cart data. Implement a simple checkout form that captures the user's address and payment method (cash on delivery).

4. Order Management:
   - IMPLEMENT: A basic Order entity with fields like order date, customer, products, total amount, and status using JPA. Implement basic order listing for customers and admins.
   - OMIT: Detailed order tracking, shipment management, and integration with external logistics providers.
   - INSTRUCTIONS: Create an Order entity and an OrderRepository. Implement endpoints for listing orders and viewing order details.

5. Vendor and Admin Dashboards:
   - IMPLEMENT: Simple dashboards with key metrics like total sales, number of products, and recent orders. Implement basic product management (CRUD) for vendors and user management for admins.
   - OMIT: Advanced analytics, reporting, and fine-grained access control.
   - INSTRUCTIONS: Create separate views for vendor and admin dashboards.

6. Email Notifications:
   - IMPLEMENT: Send basic email notifications for user registration and order confirmation using Spring Mail.
   - OMIT: Complex email templates, personalized content, and integration with email marketing services.
   - INSTRUCTIONS: Configure Spring Mail with your email provider's SMTP settings. Use simple email templates for registration and order confirmation.


- Focus on core functionality first and add features iteratively.
- Use simple and clean UI to ensure a good user experience.
- Implement basic security measures to protect user data.
- Use logging to track application events and errors.
