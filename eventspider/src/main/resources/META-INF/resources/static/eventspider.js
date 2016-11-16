/**
 * Javascript to help with styling form. Uses JQuery
 * Created by sebastian on 10/26/16.
 */
$(document).ready(function () {

    dateTime();
    dropDownMenu();
});

// Sets the date and time pickers on the event search page
function dateTime() {
    $(".date").datepicker();
    $(".time").timepicker();
}

// Animates the drop down menu in the nav bar
function dropDownMenu() {
    $(".dropdown-toggle").click(function() {
        $(".dropdown").toggleClass("open");
        if ($(this).attr("aria-expanded")=="true") {
            $(this).attr("aria-expanded", "false");
        } else {
            $(this).attr("aria-expanded", "true");
        }

    });

}