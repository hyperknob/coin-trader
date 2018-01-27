<#include "../top.ftl"/>
</head>
<body>

    <div class="container">

        <div class="header clearfix">
            <nav>
                <ul class="nav nav-pills pull-right">
                </ul>
            </nav>
            <h3 class="text-muted">OAuth2 Server 应用</h3>
        </div>

        <form name="client" method="post" class="form-inline">
            <input name="id" value="${client.id!}" style="display: none"/>
            <input name="clientId" value="${client.clientId!}" style="display: none"/>
            <input name="clientSecret" value="${client.clientSecret!}" style="display: none"/>
            <input name="status" value="${client.status!}" style="display: none"/>

            <div class="form-group">
                <label>应用名：</label>
                <input name="clientName" value="${client.clientName!}"/>
            </div>

            <input type="submit" value="${op}"/>
        </form>

<#include "../footer.ftl"/>