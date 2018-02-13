<#include "../top.ftl"/>
</head>
<body>

<div class="container">

    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
            </ul>
        </nav>
        <h3 class="text-muted">Market列表</h3>
    </div>

    <#if msg??>
        <div class="alert alert-danger" role="alert">${msg}</div>
    </#if>

    <div>
        <h3><a href="${basePath}/createCurrencyPair">新增交易对</a></h3>
    </div>

    <table class="table table-bordered table-hover table-condensed">
        <thead>
            <tr>
                <th>币行</th>
                <th>交易对</th>
                <th>汇率</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <#list currencyPairList as currencyPair>
                <tr>
                    <td>${currencyPair.exchange}</td>
                    <td>${currencyPair.currencyPair}</td>
                    <td>----</td>
                    <td>
                        <a href="javascript：void(0)">交易</a>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>

<#include "../footer.ftl"/>