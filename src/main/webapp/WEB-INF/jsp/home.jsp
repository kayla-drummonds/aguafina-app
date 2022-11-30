<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib
prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width" />
		<link
			rel="stylesheet"
			href="https://unpkg.com/spectre.css/dist/spectre.min.css" />
		<link
			rel="stylesheet"
			href="https://unpkg.com/spectre.css/dist/spectre-exp.min.css" />
		<link
			rel="stylesheet"
			href="https://unpkg.com/spectre.css/dist/spectre-icons.min.css" />
		<link rel="stylesheet" href="../static/css/styles.css" />
		<link rel="preconnect" href="https://fonts.googleapis.com" />
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
		<link
			href="https://fonts.googleapis.com/css2?family=Noto+Sans+Ethiopic&display=swap"
			rel="stylesheet" />
		<style>
			.empty {
				background-color: #135c9e;
				color: white;
			}

			.menu-item {
				color: #135c9e;
				background-color: white;
			}
		</style>
		<title>Home</title>
	</head>

	<body>
		<div class="container pt-2">
			<div class="empty">
				<p class="empty-title h5" th:text="'Welcome, ' + ${username} + '!'"></p>
				<p class="empty-subtitle">
					Please choose one of the following options:
				</p>
				<div class="empty-action">
					<ul class="menu">
						<security:authorize access="hasAnyAuthority('CUSTOMER')">
							<li
								class="divider"
								data-content="CUSTOMER"
								sec:authorize="hasAnyAuthority('CUSTOMER')"></li>
						</security:authorize>
						<security:authorize access="hasAnyAuthority('CUSTOMER')">
							<li class="menu-item">
								<a
									th:href="@{/orders/customer/{customer}(customer = ${customer.id})}"
									>View My Orders</a
								>
							</li>
						</security:authorize>
						<security:authorize access="hasAnyAuthority('CUSTOMER')">
							<li class="menu-item">
								<a th:href="@{/customers/edit/{id}(id = ${customer.id})}"
									>Edit My Information</a
								>
							</li>
						</security:authorize>
						<security:authorize access="hasAnyAuthority('ADMIN', 'EMPLOYEE')">
							<li class="divider" data-content="EMPLOYEE"></li>
						</security:authorize>
						<security:authorize access="hasAnyAuthority('ADMIN', 'EMPLOYEE')">
							<li class="menu-item">
								<a
									th:href="@{/orders/employee/{employee}(employee = ${employee.id})}"
									>View My Orders</a
								>
							</li>
						</security:authorize>
						<security:authorize access="hasAnyAuthority('ADMIN', 'EMPLOYEE')">
							<li class="menu-item">
								<a th:href="@{/customers}">View All Customers</a>
							</li>
						</security:authorize>
						<security:authorize access="hasAnyAuthority('ADMIN', 'EMPLOYEE')">
							<li class="menu-item">
								<a th:href="@{/orders}">View All Orders</a>
							</li>
						</security:authorize>
						<security:authorize access="hasAnyAuthority('ADMIN')">
							<li class="divider" data-content="ADMIN"></li>
						</security:authorize>
						<security:authorize access="hasAnyAuthority('ADMIN')">
							<li class="menu-item">
								<a th:href="@{/employees}">View All Employees</a>
							</li>
						</security:authorize>
						<li class="divider"></li>
						<security:authorize access="isAuthenticated()">
							<li class="menu-item">
								<a th:href="@{/logout}">Log out</a>
							</li>
						</security:authorize>
					</ul>
				</div>
			</div>
		</div>
		<div th:insert="~{footer :: copy}"></div>
	</body>
</html>
