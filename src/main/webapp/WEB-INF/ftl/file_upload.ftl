<#ftl encoding='UTF-8'>
<#include "base.ftl"/>

<#macro content>
    <div class="feed-container">
        <div class="feed">
            <h1>Files</h1>
            <form name="post_form" id="post_form" enctype="multipart/form-data">
                <div id="namer">
                    <div id="namer-input">
                        <input type="file" id="file" name="file"/>
                    </div>
                </div>
                <button type="button" class="btn btn-danger btn-lg" id="test_ajax" onclick="f()" value="Добавить товар">
                    load to server
                </button>
            </form>

        </div>
    </div>


    <script type="application/javascript">
        function f() {
            // Create an FormData object
            var form = $("#post_form")[0];
            var data = new FormData(form);
            console.log("form data " + data);
            $.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "/files",
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success : function(data) {
                    alert(data);
                },
                error : function(err) {
                    alert(err);
                }
            });
        };
    </script>
    <div>
        <#if message?has_content>
            <p>${message}</p>
        <#else>
        </#if>
    </div>
</#macro>
<#macro title>
    <title>Loading File</title>
</#macro>
<@display_page />
