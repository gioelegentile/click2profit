/**
 * Created by david on 16/01/2016.
 */

function checkCookie(data) {
    if(data["usr_id"] != undefined && data["usr_email"] != undefined && data["usr_pwd"] != undefined
        && data["usr_ka"] != undefined && data["usr_is_admin"] != undefined)
        return true;
    else
        return false;
}