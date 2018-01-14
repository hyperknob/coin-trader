<#include "top.ftl"/>
    <title>Api Key加载</title>
<script src="${basePath}/static/js/index.js"></script>
</head>
<body>
<div class="container">

    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
            </ul>
        </nav>
        <h3 class="text-muted">Api Key Upload</h3>
    </div>
    <div class="row marketing">
        <div class="col-lg-10">
             <form class="form-horizontal" method="post">
                 <div class="form-group">
                     <label class="col-sm-4 control-label">ApiKeyName</label>
                     <div class="col-sm-8">
                         <input type="text" class="form-control" id="keyName" name="keyName"
                                value="OKEX" readonly="readonly">
                     </div>
                 </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">ApiKey</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="key" name="key"
                               placeholder="OK币行提供的apiKey">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">ApiSecret</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="secret" name="secret"
                               placeholder="OK币行提供的apiSecret">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <input type="button" class="btn btn-default" onclick="commitApiKey()" value="激活APIKey提供交易服务"></input>
                    </div>
                </div>
            </form>
        </div>
    </div>

<#include "footer.ftl"/>