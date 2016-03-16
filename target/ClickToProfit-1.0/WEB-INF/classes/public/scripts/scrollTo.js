/**
 * Created by Gioele on 03/01/2016.
 */

// This is a functions that scrolls to #{blah}link
function goToByScroll(id){
    // Remove "link" from the ID
    id = id.replace("link", "");
    // Scroll
    $('html,body').animate({
            scrollTop: $("#"+id).offset().top-63},
        'slow');
}

$("#sidebar > ul > li > a").click(function(e) {
    // Prevent a page reload when a link is pressed
    e.preventDefault();
    // Call the scroll function
    goToByScroll(this.id);
});
