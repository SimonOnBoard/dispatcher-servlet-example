<#include "base.ftl"/>

<#macro content>
    <p>${user.getNick()}</p>
    <p>${user.getMail()}</p>
    <#assign a = user.getBirthDay()>
    <p>${a?string.iso_m_nz}</p>
    <a href="/files">Look for files</a>
    <a href="/logout">Logout</a>
</#macro>

<#macro title>
    <title>Profile</title>
</#macro>
<@display_page />
