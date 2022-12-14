function validateOrder() {
    let customer = document.newOrderForm.customer;
    let employee = document.newOrderForm.employee;
    let price = document.newOrderForm.p;

    if (customer.value == "") {
        document.getElementById("customer").classList.add("is-error");
        document.getElementById("customerHint").innerHTML = "Please select a customer ID.";
        return false;
    } else {
        customer = document.getElementById("customer").value;
        document.getElementById("customer").classList.add("is-success");
    }

    if (employee.value == "") {
        document.getElementById("employee").classList.add("is-error");
        document.getElementById("employeeHint").innerHTML = "Please select an employee ID.";
        return false;
    } else {
        employee = document.getElementById("employee").value;
        document.getElementById("employee").classList.add("is-success");
    }

    if (price.value == "" || price.value == 0 || price.value < 2 || price.value > 4 || isNaN(price.value)) {

        if (price.value == "" || price.value == 0 || isNaN(price.value)) {
            document.getElementById("p").classList.add("is-error");
            document.getElementById("priceHint").innerHTML = "Please enter a positive nonzero dollar amount.";
        } else if (price.value < 2 || price.value > 4) {
            document.getElementById("p").classList.add("is-error");
            document.getElementById("priceHint").innerHTML = "Enter either 2 for 20 oz. or 4 for 32 oz.";
        }
        return false;
    } else {
        price = document.getElementById("p").value;
        document.getElementById("p").classList.add("is-success");
    }
}

function validateNewCustomer() {
    let firstName = document.newCustomerForm.firstName;
    let lastName = document.newCustomerForm.lastName;
    let email = document.newCustomerForm.email;
    let phone = document.newCustomerForm.phone;

    if (firstName.value == "") {
        document.getElementById("firstName").classList.add("is-error");
        document.getElementById("firstNameHint").innerHTML = "Please enter first name.";
        return false;
    } else {
        firstName = document.getElementById("firstName").value;
        document.getElementById("firstName").classList.add("is-success");
    }

    if (lastName.value == "") {
        document.getElementById("lastName").classList.add("is-error");
        document.getElementById("lastNameHint").innerHTML = "Please enter last name.";
        return false;
    } else {
        lastName = document.getElementById("lastName").value;
        document.getElementById("lastName").classList.add("is-success");
    }

    if (email.value == "") {
        document.getElementById("email").classList.add("is-error");
        document.getElementById("emailHint").innerHTML = "Please enter email.";
        return false;
    } else {
        atpos = email.value.indexOf("@");
        dotpos = email.value.lastIndexOf(".");
        if (atpos < 1 || (dotpos - atpos < 2)) {
            document.getElementById("email").classList.add("is-error");
            document.getElementById("emailHint").innerHTML = "Please enter email in correct format.";
            return false;
        } else {
            email = document.getElementById("email").value;
            document.getElementById("email").classList.add("is-success");
        }
    }

    if (phone.value == "") {
        document.getElementById("phone").classList.add("is-error");
        document.getElementById("phoneHint").innerHTML = "Please enter phone number.";
        return false;
    } else {
        lastName = document.getElementById("phone").value;
        document.getElementById("phone").classList.add("is-success");
    }


}