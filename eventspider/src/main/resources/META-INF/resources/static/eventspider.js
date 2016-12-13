/**
 * Javascript to help with styling form. Uses JQuery
 * Created by sebastian on 10/26/16.
 */
$(document).ready(function () {

    dateTime();
    dropDownMenu();
    indexSpiderHidden();
    togglePasswordHide();
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

// Disables tiny spider on the home page
function indexSpiderHidden() {
    if (document.title == "Home") {
        $("#spiderImg").attr("hidden", "hidden");
    }
}

// Toggles show and hide on the password field
function togglePasswordHide() {
    $("#showPassword").click(function () {
        $("#password").attr("type", "text");
        $(this).attr("hidden", "hidden");
        $("#hidePassword").removeAttr("hidden");
    });

    $("#hidePassword").click(function () {
        $("#password").attr("type", "password");
        $(this).attr("hidden", "hidden");
        $("#showPassword").removeAttr("hidden");
    });

}
