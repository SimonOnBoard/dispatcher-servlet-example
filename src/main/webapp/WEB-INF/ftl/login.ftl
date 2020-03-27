<#include "base.ftl"/>

<#macro content>
    <form name='f' action="login" method='POST'>
        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='username' value=''></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td>Remember Me:</td>
                <td><input type="checkbox" name="remember-me" /></td>
            </tr>
            <input type="hidden" name="${_csrf.parameterName}" value= "${_csrf.token}"/>
            <tr>
                <td><input name="submit" type="submit" value="submit" /></td>
            </tr>
        </table>
    </form>
</#macro>
<#macro title>
    <title>Login</title>
</#macro>
<@display_page />