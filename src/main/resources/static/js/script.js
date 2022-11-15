function validateCustomer() {
    if (document.newCustomerForm.firstName.value == "") {
        alert("Please provide first name.");
        document.newCustomerForm.firstName.focus();
        return false;
    } else {
        var firstName = document.getElementById("firstName").value;
    }

    if (document.newCustomerForm.lastName.value == "") {
        alert("Please provide last name.");
        document.newCustomerForm.lastName.focus();
        return false;
    } else {
        var lastName = document.getElementById("lastName").value;
    }

    if (document.newCustomerForm.email.value == "") {
        alert("Please provide email address.");
        document.newCustomerForm.email.focus();
        return false;
    } else {
        var emailID = document.newCustomerForm.email.value;
        atpos = emailID.indexOf("@");
        dotpos = emailID.lastIndexOf(".");
        if (atpos < 1 || (dotpos - atpos < 2)) {
            alert("Please enter correct email format.")
            document.myForm.EMail.focus();
            return false;
        } else {
            var email = document.getElementById("email").value;
        }
    }

    if (document.newCustomerForm.phone.value == "") {
        alert("Please provide phone number.");
        document.newCustomerForm.phone.focus();
        return false;
    } else {
        var phone = document.getElementById("phone").value;
    }

    if (document.newCustomerForm.address.value == "") {
        alert("Please provide address.");
        document.newCustomerForm.address.focus();
        return false;
    } else {
        var address = document.getElementById("address").value;
    }

    if (document.newCustomerForm.city.value == "") {
        alert("Please provide city.");
        document.newCustomerForm.city.focus();
        return false;
    } else {
        var city = document.getElementById("city").value;
    }

    if (document.newCustomerForm.state.value == "") {
        alert("Please provide state.");
        document.newCustomerForm.state.focus();
        return false;
    } else {
        var state = document.getElementById("state").value;
    }

    if (document.newCustomerForm.zip.value == "" || isNaN(document.newCustomerForm.zip.value) || document.newCustomerForm.zip.value != 5) {
        alert("Please provide 5-digit ZIP code.");
        document.newCustomerForm.zip.focus();
        return false;
    } else {
        var zip = document.getElementById("zipCode").value;
    }

    return true;
}

function validateEmployee() {
    if (document.newEmployeeForm.firstName.value == "") {
        alert("Please provide first name.");
        document.newCustomerForm.firstName.focus();
        return false;
    } else {
        var firstName = document.getElementById("firstName").value;
    }

    if (document.newEmployeeForm.lastName.value == "") {
        alert("Please provide last name.");
        document.newEmployeeForm.lastName.focus();
        return false;
    } else {
        var lastName = document.getElementById("lastName").value;
    }

    if (document.newEmployeeForm.email.value == "") {
        alert("Please provide email address.");
        document.newEmployeeForm.email.focus();
        return false;
    } else {
        var emailID = document.newEmployeeForm.email.value;
        atpos = emailID.indexOf("@");
        dotpos = emailID.lastIndexOf(".");
        if (atpos < 1 || (dotpos - atpos < 2)) {
            alert("Please enter correct email format.")
            document.newEmployeeForm.email.focus();
            return false;
        } else {
            var email = document.getElementById("email").value;
        }
    }

    if (document.newEmployeeForm.address.value == "") {
        alert("Please provide address.");
        document.newEmployeeForm.address.focus();
        return false;
    } else {
        var address = document.getElementById("address").value;
    }

    if (document.newEmployeeForm.city.value == "") {
        alert("Please provide city.");
        document.newEmployeeForm.city.focus();
        return false;
    } else {
        var city = document.getElementById("city").value;
    }

    if (document.newEmployeeForm.state.value == "") {
        alert("Please provide state.");
        document.newEmployeeForm.state.focus();
        return false;
    } else {
        var state = document.getElementById("state").value;
    }

    if (document.newEmployeeForm.zip.value == "" || isNaN(document.newEmployeeForm.zip.value) || document.newEmployeeForm.zip.value != 5) {
        alert("Please provide 5-digit ZIP code.");
        document.newEmployeeForm.zip.focus();
        return false;
    } else {
        var zip = document.getElementById("zipCode").value;
    }

    return true;
}