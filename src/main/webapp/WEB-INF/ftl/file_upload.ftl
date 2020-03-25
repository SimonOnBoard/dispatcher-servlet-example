<#include "base.ftl"/>
<#macro head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</#macro>
<#macro content>
    <div class="feed-container">
        <div class="feed">
            <h1>Files</h1>
            <a href="/logout">Logout</a>
            <a href="/profile">Profile</a>
            <form name="post_form" id="post_form" enctype="multipart/form-data">
                <div id="namer">
                    <div id="namer-input">
                        <input type="file" id="file" name="file" placeholder="Choose your file"/>
                    </div>
                </div>
                <button type="button" class="btn btn-danger btn-lg" id="test_ajax" onclick="f()" value="Load file">Load file
                </button>
            </form>

            <div id="result">
                <table id="table">

                </table>
            </div>

            <div>
                <p>Previously uploaded files</p>
                <table>
                    <#list files as file>
                        <tr>
                            <td>${file.getOriginalFileName()}</td>
                            <td>${file.getSize()}</td>
                            <td><a href="${file.getUrl()}">Load file</a></td>
                        </tr>
                    </#list>
                </table>
            </div>
            <div>
                <#if message?has_content>
                    <p>${message}</p>
                <#else>
                </#if>
            </div>
        </div>
    </div>


    <script type="application/javascript">
        function f() {
            // Create an FormData object
            var form = $("#post_form")[0];
            var data = new FormData(form);
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            console.log("form data " + data);
            $.ajax({
                type: "POST",
                beforeSend: function(request) {
                    request.setRequestHeader(header, token);},
                enctype: 'multipart/form-data',
                url: "/files",
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success : function(data) {
                    var a = JSON.stringify(data);
                    var b = JSON.parse(a);
                    $("#table").append(
                        "<tr>\n" +
                        "                <td>" + b.originalName +"</td>\n" +
                        "                <td>" + b.size + "</td>\n" +
                        "                <td>" + "<a href=" + b.url + ">" + "Click to load" + "</a></td>\n" +
                        "            </tr>");
                    alert(a);
                    console.log(a);
                    console.log(b);
                },
                error : function(err) {
                    alert(err);
                }
            });
        };
    </script>

</#macro>
<#macro title>
    <title>Loading File</title>
</#macro>
<@display_page />
