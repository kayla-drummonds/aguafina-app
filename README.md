# Aguafina App
<b><em>Description</em></b></br>

Aguafina App is a web application used by customers and employees of a mineral water store, Aguafina. Customers use Aguafina App to view their order history and update their personal information. Employees use the app to view their order history as well as add new orders, view all past orders of the store, and look up customers. Employees with an admin role can delete orders.

<b><em>Tech Stack:</em></b>

Front End:</br>
<ul>
  <li>HTML</li>
  <li>CSS</li>
  <li>Javascript</li>
 </ul>

Back End:</br>
<ul>
  <li>Java</li>
  <li>MySQL</li>
</ul>

Frameworks:</br>
<ul>
  <li>Spectre.css</li>
  <li>Spring MVC</li>
</ul>

Template Engine:</br>
<ul>
  <li>Thymeleaf</li>
</ul>

<b><em>Demo</em></b></br>


<figure>
  <figcaption><b>Database Schema</b></figcaption></br>
  <img alt="Schema for Aguafina Database" src="screenshots/database_schema.png">
</figure>
Many to Many Relationship:</br>
<ul>
  <li>Users and Roles</li>
</ul>

Many to One Relationships:</br>
<ul>
  <li>Customers to Users</li>
  <li>Employees to Users</li>
  <li>Orders to Customers</li>
  <li>Orders to Employees</li>
</ul>


<b>All Users</b></br>

Login page:</br>
<figure>
  <figcaption>Login page</figcaption>
  <img alt="Login page" src="screenshots/login_page.png">
  </figure>
</br>
Both customers and employees use this login page to enter the app. If they are a new user, the customer/employee can register an account using either the customer user account registration page or the employee user account registration page.
Register as a customer or employee user:</br>

<b>Customer User:</b></br>
Home Page:</br>
View my Orders:</br>
Update My Information:</br>

<b>Employee User:</b></br>
Home Page:</br>
View all orders:</br>
Create new order with a new customer:</br>
Create new order with an existing customer:</br>
View my orders:</br>

<b><em>Blockers:</em></b>
<b><em>Installation and Project Set Up Instructions:</em></br>


