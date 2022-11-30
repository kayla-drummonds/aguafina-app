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
        document.getElementById("firstName").classList.add("is-error");
        document.getElementById("firstNameHint").innerHTML = "Please provide first name.";
        return false;
    } else {
        let firstName = document.getElementById("firstName").value;
        document.getElementById("firstName").classList.add("is-success");
    }

    if (lastName.value == "") {
        document.getElementById("lastName").classList.add("is-error");
        document.getElementById("lastNameHint").innerHTML = "Please provide last name.";
        return false;
    } else {
        let lastName = document.getElementById("lastName").value;
        document.getElementById("lastName").classList.add("is-success");
    }

    if (email.value == "") {
        document.getElementById("email").classList.add("is-error");
        document.getElementById("emailHint").innerHTML = "Please provide email address.";
        return false;
    } else {
        let emailID = email.value;
        atpos = emailID.indexOf("@");
        dotpos = emailID.lastIndexOf(".");
        if (atpos < 1 || (dotpos - atpos < 2)) {
            document.getElementById("email").classList.add("is-error");
            document.getElementById("emailHint").innerHTML = "Please enter email in correct format.";
            return false;
        } else {
            let email = document.getElementById("email").value;
            document.getElementById("email").classList.add("is-success");
        }
    }

    if (phone.value == "") {
        document.getElementById("phone").classList.add("is-error");
        document.getElementById("phoneHint").innerHTML = "Please provide phone number.";
        return false;
    } else {
        let phone = document.getElementById("phone").value;
        document.getElementById("phone").classList.add("is-success");
    }

    if (address.value == "") {
        document.getElementById("address").classList.add("is-error");
        document.getElementById("addressHint").innerHTML = "Please provide street address.";
        return false;
    } else {
        let address = document.getElementById("address").value;
        document.getElementById("address").classList.add("is-success");
    }

    if (city.value == "") {
        document.getElementById("city").classList.add("is-error");
        document.getElementById("cityHint").innerHTML = "Please provide city.";
        return false;
    } else {
        let city = document.getElementById("city").value;
        document.getElementById("city").classList.add("is-success");
    }

    if (state.value == "") {
        document.getElementById("state").classList.add("is-error");
        document.getElementById("stateHint").innerHTML = "Please provide state.";
        return false;
    } else {
        let state = document.getElementById("state").value;
        document.getElementById("state").classList.add("is-success");
    }

    if (zip.value == "" || !zip.value.match("^[0-9]{5}$")) {
        document.getElementById("zipCode").classList.add("is-error");
        document.getElementById("zipCodeHint").innerHTML = "Please provide 5-digit ZIP code.";
        return false;
    } else {
        let zip = document.getElementById("zipCode").value;
        document.getElementById("zipCode").classList.add("is-success");
    }

    return true;
}

function validateOrder() {
    let customer = document.newOrderForm.customer;
    let employee = document.newOrderForm.employee;
    let product = document.newOrderForm.product;
    let quantity = document.newOrderForm.q;
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