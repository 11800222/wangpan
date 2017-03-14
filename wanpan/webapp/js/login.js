/**
 * Created by flywu_000 on 2014/4/14.
 */
$(document).ready(function(){
	 $.backstretch("images/backgrounds/2.jpg");
	
    if (GetCookie("loginName") != null) {
        document.getElementById("RememberMe").checked = true;
    }
    if (GetCookie("loginName") != null)document.getElementById("form-username").value = GetCookie("loginName");
    if (GetCookie("password") != null)document.getElementById("form-password").value = GetCookie("password");

});

function RememberMeClick() {
    if (document.getElementById("RememberMe").checked == false) {
    DelCookie("loginName");
    DelCookie("password");
    }
    }
function submitForm() {

    if ($.trim($("#form-username").val()) == "") {
    alert("请输入用户名!");
    $("#username").focus();
        return false;
    }
    if ($.trim($("#form-password").val()) == "") {
    alert("请输入密码!");
    $("#password").focus();
        return false;
    }
    if ($.trim($("#ValidateCode").val()) == "") {
    alert("请输入验证码!");
    $("#ValidateCode").focus();
        return false;

    }

    if (document.getElementById("RememberMe").checked == true) {
    SetCookie("loginName", document.getElementById("form-username").value, 60 * 60 * 24 * 365);
    SetCookie("password", document.getElementById("form-password").value, 60 * 60 * 24 * 365);
    }
    return true;

    }


