function validateCustomer() {
    let firstName = document.newCustomerForm.firstName;
    let lastName = document.newCustomerForm.lastName;
    let email = document.newCustomerForm.email;
    let phone = document.newCustomerForm.phone;
    let address = document.newCustomerForm.address;
    let city = document.newCustomerForm.city;
    let state = document.newCustomerForm.state;
    let zip = document.newCustomerForm.zipCode;

    if (firstName.value == "") {
        alert("Please provide first name.");
        firstName.focus();
        return false;
    } else {
        let firstName = document.getElementById("firstName").value;
    }

    if (lastName.value == "") {
        alert("Please provide last name.");
        lastName.focus();
        return false;
    } else {
        let lastName = document.getElementById("lastName").value;
    }

    if (email.value == "") {
        alert("Please provide email address.");
        email.focus();
        return false;
    } else {
        let emailID = email.value;
        atpos = emailID.indexOf("@");
        dotpos = emailID.lastIndexOf(".");
        if (atpos < 1 || (dotpos - atpos < 2)) {
            alert("Please enter correct email format.")
            email.focus();
            return false;
        } else {
            let email = document.getElementById("email").value;
        }
    }

    if (phone.value == "") {
        alert("Please provide phone number.");
        phone.focus();
        return false;
    } else {
        let phone = document.getElementById("phone").value;
    }

    if (address.value == "") {
        alert("Please provide address.");
        address.focus();
        return false;
    } else {
        let address = document.getElementById("address").value;
    }

    if (city.value == "") {
        alert("Please provide city.");
        city.focus();
        return false;
    } else {
        let city = document.getElementById("city").value;
    }

    if (state.value == "") {
        alert("Please provide state.");
        state.focus();
        return false;
    } else {
        let state = document.getElementById("state").value;
    }

    if (zip.value == "" || isNaN(zip.value) || zip.value != 5) {
        alert("Please provide 5-digit ZIP code.");
        zip.focus();
        return false;
    } else {
        let zip = document.getElementById("zipCode").value;
    }

    return true;
}

function validateOrder() {
    let creationDate = document.newOrderForm.creationDate;
    let customer = document.newOrderForm.customer;
    let employee = document.newOrderForm.employee;
    let product = document.newOrderForm.product;
    let quantity = document.newOrderForm.q;
    let price = document.newOrderForm.p;

    if (creationDate.value == "") {
        document.getElementById("creationDate").classList.add("is-error");
        document.getElementById("creationDateHint").innerHTML = "Please enter a valid date.";
        return false;
    } else {
        creationDate = document.getElementById("creationDate").value;
        document.getElementById("creationDate").classList.add("is-success");
    }

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

    if (product.value == "") {
        document.getElementById("product").classList.add("is-error");
        document.getElementById("productHint").innerHTML = "Please select a product.";
        return false;
    } else {
        product = document.getElementById("product").value;
        document.getElementById("product").classList.add("is-success");
    }

    if (quantity.value == "" || quantity.value == 0 || quantity.value < 0 || quantity.value >= 10) {
        document.getElementById("q").classList.add("is-error");
        document.getElementById("quantityHint").innerHTML = "Please enter a quantity between 1 and 10.";
        return false;
    } else {
        q = document.getElementById("q").value;
        document.getElementById("q").classList.add("is-success");
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