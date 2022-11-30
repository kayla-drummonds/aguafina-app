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
		<title>Customers</title>
		<style>
			form {
				margin: auto;
				padding: none;
				border: none;
				background-color: #afc1d2;
			}

			#create-customer-container,
			#customers-header-container {
				display: flex;
				justify-content: center;
				align-items: center;
			}

			#search-button {
				background-color: #477eaf;
				color: white;
				border: none;
			}
		</style>
	</head>

	<body>
		<header class="navbar">
			<section class="navbar-section">
				<a class="btn btn-link" href="home.jsp">Home</a>
			</section>
			<section class="navbar-section">
				<a
					th:href="@{/orders/customer/{customer}(customer = ${customer.id})}"
					class="btn btn-link"
					>My Orders</a
				>
			</section>
			<section>
				<a
					th:href="@{/customers/edit/{id}(id = ${customer.id})}"
					class="btn btn-link"
					>Edit My Information</a
				>
			</section>
			<section class="navbar-section">
				<a class="btn btn-link active" href="customers.jsp">Customers</a>
			</section>
			<section class="navbar-section">
				<a class="btn btn-link" href="employees.jsp">Employees</a>
			</section>
			<section class="navbar-section">
				<a class="btn btn-link" href="orders.jsp">All Orders</a>
			</section>
			<section class="navbar-section">
				<a
					class="btn btn-link"
					th:href="@{/orders/employee/{employee}(employee = ${employee.id})}"
					>My Orders</a
				>
			</section>
			<section class="navbar-section">
				<a class="btn btn-link" th:href="@{/logout}">Log Out</a>
			</section>
		</header>
		<div class="container pt-2 pb-2">
			<div class="columns">
				<div class="column col-4" id="create-customer-container">
					<a href="create_customer.jsp" class="btn btn-lg" id="create-button"
						>Create New Customer</a
					>
				</div>
				<div class="column col-4" id="customers-header-container">
					<h2 class="text-center">Customers</h2>
				</div>
				<div class="column col-4">
					<form method="get" th:action="@{/customers}">
						<div class="input-group input-inline">
							<input
								class="form-input"
								type="text"
								placeholder="Search by Email or Phone"
								value="${keyword}" />
							<button
								type="submit"
								class="btn input-group-btn"
								title="Search By Keyword"
								id="keyword-search-button">
								Search
							</button>
						</div>
					</form>
				</div>
			</div>
			<div class="container">
				<div class="columns">
					<div class="panel column col-4 col-sm-6 col-md-6" id="customer-tile">
						<c:forEach items="${customers}" var="customer">
							<div class="panel-header">
								<div class="panel-title h4">
									${customer.firstName} ${customer.lastName}
								</div>
								<div class="h5">${customer.id}</div>
							</div>
							<div class="divider"></div>
							<div class="panel-body">
								<div class="columns">
									<div class="column col-4">Email:</div>
									<div class="column col-8">${customer.email}</div>
									<div class="column col-4">Phone:</div>
									<div class="column col-8">${customer.phone}</div>
									<div class="column col-4">Address:</div>
									<div class="column col-8">${customer.address}</div>
									<div class="column col-4 d-invisible">Placeholder</div>
									<div class="column col-8">
										${customer.city}, ${customer.state} ${customer.zipCode}
									</div>
									<div class="column col-4">Orders:</div>
									<div class="column col-8">${customer.orders.size()}</div>
								</div>
							</div>
							<div class="panel-footer">
								<a
									class="btn btn-link"
									id="update-button"
									th:href="@{/customers/edit/{id}(id=${customer.id})}"
									>Update <span class="icon icon-edit"></span>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div th:insert="~{footer :: copy}"></div>
	</body>
</html>
