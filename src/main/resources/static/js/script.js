function validateCustomer() {
    var firstName = document.newCustomerForm.firstName;
    var lastName = document.newCustomerForm.lastName;
    var email = document.newCustomerForm.email;
    var phone = document.newCustomerForm.phone;
    var address = document.newCustomerForm.address;
    var city = document.newCustomerForm.city;
    var state = document.newCustomerForm.state;
    var zip = document.newCustomerForm.zipCode;

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

function validateEmployee() {
    if (document.newEmployeeForm.firstName.value == "") {
        alert("Please provide first name.");
        document.newCustomerForm.firstName.focus();
        return false;
    } else {
        let firstName = document.getElementById("firstName").value;
    }

    if (document.newEmployeeForm.lastName.value == "") {
        alert("Please provide last name.");
        document.newEmployeeForm.lastName.focus();
        return false;
    } else {
        let lastName = document.getElementById("lastName").value;
    }

    if (document.newEmployeeForm.email.value == "") {
        alert("Please provide email address.");
        document.newEmployeeForm.email.focus();
        return false;
    } else {
        let emailID = document.newEmployeeForm.email.value;
        atpos = emailID.indexOf("@");
        dotpos = emailID.lastIndexOf(".");
        if (atpos < 1 || (dotpos - atpos < 2)) {
            alert("Please enter correct email format.")
            document.newEmployeeForm.email.focus();
            return false;
        } else {
            let email = document.getElementById("email").value;
        }
    }

    if (document.newEmployeeForm.address.value == "") {
        alert("Please provide address.");
        document.newEmployeeForm.address.focus();
        return false;
    } else {
        let address = document.getElementById("address").value;
    }

    if (document.newEmployeeForm.city.value == "") {
        alert("Please provide city.");
        document.newEmployeeForm.city.focus();
        return false;
    } else {
        let city = document.getElementById("city").value;
    }

    if (document.newEmployeeForm.state.value == "") {
        alert("Please provide state.");
        document.newEmployeeForm.state.focus();
        return false;
    } else {
        let state = document.getElementById("state").value;
    }

    if (document.newEmployeeForm.zip.value == "" || isNaN(document.newEmployeeForm.zip.value) || document.newEmployeeForm.zip.value != 5) {
        alert("Please provide 5-digit ZIP code.");
        document.newEmployeeForm.zip.focus();
        return false;
    } else {
        let zip = document.getElementById("zipCode").value;
    }

    return true;
}