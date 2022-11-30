<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			#customer-register-container {
				background-color: #135c9e;
				color: #e3e3e3;
			}

			#customer-register-container h3 {
				color: #e3e3e3;
			}

			label {
				color: #135c9e;
			}
		</style>
		<title>Customer Account Registration</title>
	</head>

	<body>
		<div class="container py-2 px-2">
			<div
				class="card p-centered col-6 col-sm-12 px-2 py-2"
				id="customer-register-container">
				<div class="card-header">
					<h3 class="card-title text-center">Customer Account Registration</h3>
				</div>
				<div class="card-body">
					<form
						class="form-horizontal px-2 s-rounded"
						action="/registration/customer"
						method="post">
						<div class="columns">
							<div class="column col-12 py-2">
								<div class="form-group">
									<div class="column col-4">
										<label class="form-label form-label-inline" for="email">
											Email:
										</label>
									</div>
									<div class="column col-8">
										<input
											id="email"
											class="form-input"
											name="email"
											required />
									</div>
								</div>
							</div>
							<div class="column col-12 py-2">
								<div class="form-group">
									<div class="column col-4">
										<label class="form-label form-label-inline" for="password">
											Password:
										</label>
									</div>
									<div class="column col-8">
										<input
											id="password"
											class="form-input"
											type="password"
											name="password"
											required />
									</div>
								</div>
							</div>
						</div>
						<button type="submit" class="btn btn-success p-centered">
							Register
						</button>
					</form>
				</div>
			</div>
		</div>
		<div th:insert="~{footer :: copy}"></div>
	</body>
</html>
