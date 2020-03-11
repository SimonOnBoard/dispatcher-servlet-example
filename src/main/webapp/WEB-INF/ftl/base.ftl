<#ftl encoding='UTF-8'>
<#macro content></#macro>
<#macro title></#macro>

<#macro display_page>
    <html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <link src="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link src="css/style.css" rel="stylesheet" type="text/css">
        <meta charset="utf-8">
        <@title />


    </head>

    <body>
    <@content />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    </body>
    </html>
</#macro>