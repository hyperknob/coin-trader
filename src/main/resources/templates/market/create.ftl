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

        <form name="currencyPairForm" method="post" class="form-inline">
            <div class="form-group">
                <label>交易对名称：</label>
                <input id="currencyPairName" name="currencyPairName"/>
            </div>

            <input type="submit" value="${op}"/>
        </form>

<#include "../footer.ftl"/>