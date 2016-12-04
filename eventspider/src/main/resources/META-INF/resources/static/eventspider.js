/**
 * Javascript to help with styling form. Uses JQuery
 * Created by sebastian on 10/26/16.
 */
$(document).ready(function () {

    dateTime();
    dropDownMenu();
    $("#loginForm").validate();
    $("#registerForm").validate();
});

// Sets the date and time pickers on the event search page
function dateTime() {
    $(".date").datepicker();
    $(".time").timepicker();
}

// Animates the drop down menu in the nav bar
function dropDownMenu() {
    $(".dropdown-toggle").on('click', function () {
        $(".dropdown").toggleClass("open");
        var menu = $(this);
        if (menu.attr("aria-expanded") == "true") {
            menu.attr("aria-expanded", "false");
        } else {
            menu.attr("aria-expanded", "true");
        }
        return false;
    });
}
