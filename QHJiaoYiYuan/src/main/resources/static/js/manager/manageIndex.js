$(document).ready(function(){
    init();

    /**
     * 初始化函数
     */
    function init() {
        /**
         * 启动bootstrap的 popover功能
         */
        $('[data-toggle="popover"]').popover();
        // 查看table是否存在，如果存在，对分页信息进行初始化
        let tableElement = $(".container table");
        if (tableElement.length >0){
            // 初始化分页html
            initPageHtml();
        }

        // 对查询按钮设置点击动作
        $("#conditionSearch").unbind("click",findPageResultByCondition);
        $("#conditionSearch").bind("click", findPageResultByCondition);

        // 为所有的操作按钮添加事件
        addClientEventForOptionButtons();
    }

    function addClientEventForOptionButtons() {
        let buttonElements = $(".container table tbody .articleOperation button");
        for(let buttonIndex=0;buttonIndex<buttonElements.length;buttonIndex++){
            addClickUpdateArticleEvent(buttonElements[buttonIndex]);
        }
    }

    /**
     * 添加点击事件
     * @param buttonElement
     */
    function addClickUpdateArticleEvent(buttonElement) {
        $(buttonElement).unbind("click",ajaxUpdateArticleByAid);
        $(buttonElement).bind("click",ajaxUpdateArticleByAid);
    }

    /**
     * 更新作品的状态
     */
    function ajaxUpdateArticleByAid() {
        // 获取状态
        let status = $(this).attr("id");
        let name = $(this).attr("name");
        // 获取aid
        let aid = $(this).parent().parent().parent("tr").children("td:first-child").attr("id");
        let data = {"status": status, "aid": aid};
        let dialogTitle="";
        let dialogBody = "";
        if (name=="pushArticle"){
            dialogTitle="发布";
            dialogBody="作品即将被公开，是否继续？"
            updateDialogAndShowDialog(dialogTitle,dialogBody,data);
        }else if(name=="cancelArticle"){
            dialogTitle="撤销";
            dialogBody="即将撤销发布，是否继续？";
            updateDialogAndShowDialog(dialogTitle,dialogBody,data);
        }else if (name=="deleteArticle"){
            dialogTitle="删除";
            dialogBody="该作品将永久被删除，是否继续？";
            updateDialogAndShowDialog(dialogTitle,dialogBody,data);
        }


    }

    /**
     * 展示dialog， 并将对话框显示出来
     * @param dialogTitle
     * @param dialogBody
     */
    function updateDialogAndShowDialog(dialogTitle,dialogBody,data) {
        // 设置标题
        $("#optionButtonModel #optionButtonModelLabel").text(dialogTitle);
        $("#optionButtonModel .modal-body").text(dialogBody);
        deleteModelHideInputAndAddNew(data);
        // 为确认按钮添加点击时间
        $("#optionButtonModel .modal-footer #optionOk").unbind("click", ajaxRunOptionFunction);
        $("#optionButtonModel .modal-footer #optionOk").bind("click", ajaxRunOptionFunction);
        $("#optionButtonModel").modal("show");
    }

    /**
     * 删除隐藏的input，并添加新的
     */
    function deleteModelHideInputAndAddNew(data) {
        $("#optionButtonModel .modal-footer #optionOk").siblings("input").remove();
        // 添加隐藏值
        let aidInput = $("<input type='hidden' name='aid'>");
        aidInput.val(data["aid"]);
        let statusInput = $("<input type='hidden' name='status'>");
        statusInput.val(data["status"]);
        $("#optionButtonModel .modal-footer #optionOk").parent().append(aidInput);
        $("#optionButtonModel .modal-footer #optionOk").parent().append(statusInput);
    }

    /**
     * 执行ajax操作
     * @param data
     */
    function ajaxRunOptionFunction() {
        // 将按钮不可用
        $("#optionButtonModel .modal-footer button").attr("disabled","true")
        // 获取隐藏值
        let aid = $(this).siblings("input[name='aid']").val();
        let status = $(this).siblings("input[name='status']").val();
        // ajax 参数
        let data = JSON.stringify({"aid":aid, "status": status});
        // 获取 token
        let token = $(".container nav #pageValue input[name='_csrf']").val();
        let headers = {"X-CSRF-TOKEN": token};
        let url = $.projectRootUrl() + "/managerController/updateOrDeleteArticleByStatus";
        $.ajax({
            type: "POST",
            dataTypes: "json",
            contentType: "application/json",
            url: url,
            data: data,
            headers: headers,
            success: function (response) {
                // 结果的状态
                let status = response.status;
                if (status == 0) {
                    findPageResultAndCurrentPageByCondition();
                } else {
                    console.error("Ajax Exception: " + url);
                }
                $("#optionButtonModel").modal("hide");
                $("#optionButtonModel .modal-footer button").removeAttr("disabled")
            }
        });
    }

    /**
     * 根据条件查询
     */
    function findPageResultByCondition() {
        // ajax 参数
        let data = JSON.stringify(searchData());
        // 获取 token
        let token = $(".container nav #pageValue input[name='_csrf']").val();
        let headers = {"X-CSRF-TOKEN": token};
        // 获取项目路径
        let url = $.projectRootUrl() + "/articleController/findPageArticleResult";
        ajaxFindPage(headers, data, url);
    }

    /**
     * 修改状态后加载当前页
     */
    function findPageResultAndCurrentPageByCondition() {
        // ajax 参数
        let params = searchData();

        // 获取当前页面数
        params["currentPage"]=$(".container nav #pageValue input[name='currentPageNum']").val();
        // 获取总页面数
        params["pageSize"] = $(".container nav #pageValue input[name='pageSize']").val();
        let data = JSON.stringify(params);
        // 获取 token
        let token = $(".container nav #pageValue input[name='_csrf']").val();
        let headers = {"X-CSRF-TOKEN": token};
        // 获取项目路径
        let url = $.projectRootUrl() + "/articleController/findPageArticleResult";
        ajaxFindPage(headers, data, url);
    }

    /**
     * 封装请求参数
     * @returns {{articleTitle: (string|*|jQuery), cids: *[], status: *[]}}
     */
    function searchData() {
        // 获取被选中对栏目id
        let cidOptions = $(".conditionSearch #channelIds option:selected");
        let cids = fetchOptionValue(cidOptions);
        // 获取被选中对状态
        let statusOptions = $(".conditionSearch #status option:selected");
        let status = fetchOptionValue(statusOptions);
        // 获取题目
        let articleTitle = $(".conditionSearch #articleTitle").val();
        let data = {"cids": cids, "status": status, "articleTitle": articleTitle};
        return data;
    }

    /**
     * 获取option中的值
     * @param optionArray
     * @returns {[]}
     */
    function fetchOptionValue(optionArray) {
        let vals = [];
        for (let i=0;i<optionArray.length;i++){
            if ($(optionArray[i]).val()){
                vals.push($(optionArray[i]).val());
            }
        }
        return vals;
    }

    /**
     * 对分页的html进行初始化
     */
    function initPageHtml() {
        // 获取总页面数
        let pageSize = $(".container nav #pageValue input[name='pageSize']").val();
        // 获取当前页面数
        let currentPageNum = $(".container nav #pageValue input[name='currentPageNum']").val();
        // 获取一页的记录条数
        let totalPageNum = $(".container nav #pageValue input[name='totalPageNum']").val();
        // 总共的记录条数
        let totalItemNum = $(".container nav #pageValue input[name='totalItemNum']").val();

        let pageUl = $("<ul class='pagination justify-content-center'></ul>");

        // 初始化： 一页显示多少条
        let pageSizeLi = $("<li class='pageSize pt-2'></li>");
        let pageSizeLabel = $("<label for='pageSize'>一页显示条数：</label>");
        pageSizeLi.append(pageSizeLabel);
        let pageSizeSelect = $("<select id='pageSize' name='pageSize'></select>");
        pageSizeSelect.append("<option value='10'>10</option>");
        pageSizeSelect.append("<option value='30'>30</option>");
        pageSizeSelect.append("<option value='50'>50</option>");
        pageSizeSelect.append("<option value='100'>100</option>");

        let options = pageSizeSelect.children("option");
        let pageSizeFlag = true;
        for (let i = 0; i < options.length; i++) {
            let optValue = $(options[i]).text();
            if (optValue == pageSize) {
                $(options[i]).prop("selected", true);
                pageSizeFlag = false;
            }
        }
        if (pageSizeFlag) {
            var defaultOpt = $("<option></option>");
            defaultOpt.attr("value", pageSize);
            defaultOpt.text(pageSize);
            defaultOpt.prop("selected", true);
            pageSizeSelect.prepend(defaultOpt);
        }

        pageSizeLi.append(pageSizeSelect);
        // // 将一页显示多少条添加到ul
        pageUl.append(pageSizeLi);
        // 上一页 设置可用或不可用
        if (currentPageNum <= 1) {
            let previousLi = $("<li class='page-item previous disabled'><span class='page-link disabled' name='lastPageNum'>&laquo;</span></li>");
            pageUl.append(previousLi);
        } else {
            let previousLi = $("<li class='page-item previous'><span class='page-link' name='lastPageNum'>&laquo;</span></li>");
            pageUl.append(previousLi);
        }

        // 中间的页码
        // 当前页
        if (currentPageNum >= 1) {
            let currentPageNumLi = $("<li class='page-item middlePageNum active'><span class='page-link' name='currentPageNum'>" + currentPageNum + "</span></li>");
            pageUl.append(currentPageNumLi);
        }
        // 中间的页码, 显示中间页码的次数
        for (let i = parseInt(currentPageNum) + 1, j = 0; i >= 1 && i <= totalPageNum && j < 2; i++, j++) {
            let currentPageNumLi = $("<li class='page-item middlePageNum'><span class='page-link'>" + i + "</span></li>");
            pageUl.append(currentPageNumLi);
        }

        // 下一页, 设置可用或不可用
        if (currentPageNum >= totalPageNum) {
            let nextLi = $("<li class='page-item next disabled'><span class='page-link disabled' name='nextPageNum'>&raquo;</span></li>");
            pageUl.append(nextLi);
        } else {
            let nextLi = $("<li class='page-item next'><span class='page-link' name='nextPageNum'>&raquo;</span></li>");
            pageUl.append(nextLi);
        }


        let currentPageValue = "总共 " + totalPageNum + "页, " + totalItemNum + " 条数据" ;
        let currentPageSpan = $("<span class='currentPage'></span>");
        currentPageSpan.text(currentPageValue);
        let currentPageLi = $("<li class='currentPage pt-2'></li>");
        currentPageLi.append(currentPageSpan);
        pageUl.append(currentPageLi);


        $(".container nav").append(pageUl);

        // 为分页按钮添加监听事件
        // 为一页显示多少条添加监听事件
        $(".container nav .pagination li select").unbind("change", findPageResultList);
        $(".container nav .pagination li select").bind("change", findPageResultList);
        // 为上一页、下一页、中间页码添加监听事件
        var pateItems = $(".container nav .pagination .page-item");
        $.each(pateItems, function (i, item) {
            if (!$(item).hasClass("disabled")) {
                $(item).unbind("click", findPageResultList);
                $(item).bind("click", findPageResultList);
            }
        });
    }

    /**
     * 查找一页的PageEdmApplyOrder
     */
    function findPageResultList() {
        // 获取当前页码
        let currentPageNumElement = $(".container nav .pagination .middlePageNum span[name='currentPageNum']");
        let oldCurrentPageNum=1;
        let currentPageNum=1;
        if (currentPageNumElement.length >0){
            oldCurrentPageNum = currentPageNumElement.text();
            currentPageNum= oldCurrentPageNum;
        }
        // 获取 一页的条数
        let pageSize = $(".container nav .pagination .pageSize select option:selected").attr("value");
        // selected 是否改变
        let selectChanged = false;
        // 判断点击的是否为上一页
        if ($(this).hasClass("previous")) {
            // 获取当前页码
            currentPageNum = parseInt(currentPageNum) - 1;
        }
        // 判断点击的是否为下一页
        else if ($(this).hasClass("next")) {
            // 获取当前页码
            currentPageNum = parseInt(currentPageNum) + 1;
        }
        // 判断是否点击为中间页码
        else if ($(this).hasClass("middlePageNum")) {
            // 获取当前页码的值
            currentPageNum = $(this).children("span").text();
        }
        // 判断点击为一页多少条
        else if ($(this)[0].tagName == "SELECT") {
            pageSize = $(this).val();
            currentPageNum = 1;
            selectChanged=true;
        }
        // ajax 参数
        let searchParam = searchData();
        searchParam["currentPage"]=currentPageNum;
        searchParam["pageSize"]=pageSize;

        let data = JSON.stringify(searchParam);
        // 获取 token
        let token = $(".container nav #pageValue input[name='_csrf']").val();
        let headers = {"X-CSRF-TOKEN": token};
        // 获取项目路径
        let url = $.projectRootUrl() + "/articleController/findPageArticleResult";
        if (parseInt(currentPageNum) == parseInt(oldCurrentPageNum) && !selectChanged) {
            console.log("CurrentPage not change");
        }else {
            ajaxFindPage(headers, data, url);
        }
    }

    /**
     * 分页查询 ajax
     * @param headers
     * @param data
     * @param url
     */
    function ajaxFindPage(headers, data, url) {
        $.ajax({
            type: "POST",
            dataTypes: "json",
            contentType: "application/json",
            url: url,
            data: data,
            headers: headers,
            success: function (response) {
                // 结果的状态
                let status = response.status;
                if (status == 0) {
                    let result = response.result;
                    // table 的tr
                    reloadTableTrs(result.pageObjList);
                    // 分页的html
                    reloadPageHtml(result);
                } else {
                    console.error("Ajax Exception: " + url);
                }
            }
        });
    }

    /**
     * 重新加载分页的html
     */
    function reloadPageHtml(pageResult) {
        // 修改隐藏域的值
        // 修改总页面数
        $(".container nav #pageValue input[name='pageSize']").val(pageResult.pageSize);
        // 修改当前页面数
        $(".container nav #pageValue input[name='currentPageNum']").val(pageResult.currentPageNum);
        // 修改一页的记录条数
        $(".container nav #pageValue input[name='totalPageNum']").val(pageResult.totalPageNum);
        // 修改一页的记录条数
        $(".container nav #pageValue input[name='totalItemNum']").val(pageResult.totalItemNum);
        // 移除就得分页html
        $(".container nav .pagination").remove();
        // 重新添加分页的标签
        initPageHtml();
    }


    /**
     * 刷新table下面tr
     */
    function reloadTableTrs(resultList) {
        // 查看详情的url
        let rootDescUrl = $.projectRootUrl() + "/articleController/showArticleContent/";
        let rootUpdateArticle = $.projectRootUrl() + "/managerController/updateArticleShow/";
        let tbody = $(".container table tbody");
        // 删除所有的tr
        tbody.children("tr").remove();
        // 添加新的tr
        for (let i=0; i<resultList.length; i++){
            let tr = $("<tr></tr>")
            let aidTh = $("<td scope='row' class='aid'></td>").text(i+1);
            aidTh.attr("id", resultList[i].aid);
            tr.append(aidTh);
            // 标题
            let contentTitleTd = $("<td scope='row' class='contentTitle'></td>");
            let contentTitleA = $("<a></a>").attr("href", rootDescUrl + resultList[i].aid);
            contentTitleA.text(resultList[i].contentTitle);
            contentTitleTd.append(contentTitleA);
            tr.append(contentTitleTd);
            // 作者
            let authorTd = $("<td scope='row' class='author'></td>").text(resultList[i].author);
            tr.append(authorTd);
            // 简介
            let aboutTd = $("<td scope='row' class='about'></td>");
            let showAboutVal = String(resultList[i].about);
            let popoverEl = null;
            if (showAboutVal.length>10){
                showAboutVal = showAboutVal.substr(0,10);
                let popoverButton = $("<button data-toggle='popover' title='概述' class='btn btn-link py-0 px-0' data-html='true'></button>").text('查看全部');
                popoverButton.attr("data-content", resultList[i].about);
                popoverEl = $("<span></span>");
                popoverEl.append(popoverButton);
                // 启动popover功能
                popoverButton.popover();
            }
            let spanEl = $("<span></span>").text(showAboutVal);
            aboutTd.append(spanEl);
            if (popoverEl!=null){
                aboutTd.append(popoverEl);
            }
            tr.append(aboutTd);
            let issueTimeTd = $("<td scope='row' class='issueTime'></td>").text($.date(resultList[i].issueTime));
            tr.append(issueTimeTd);
            // 状态
            let statusTd=$("<td scope='row' class='articleStatus'></td>");
            let statusValue = resultList[i].status;
            if(statusValue==0){
                statusTd.text('暂存');
            }else if(statusValue==1){
                statusTd.text('发布');
            }else {
                statusTd.text('删除');
            }
            tr.append(statusTd);

            // 操作
            let optionTd = $("<td scope='row' class='articleOperation'></td>");
            let caoZouDiv = $("<div></div>");
            let pushA = $("<button  name='pushArticle' id='1' class='btn btn-success btn-sm active' data-toggle='modal' role='button' aria-pressed='true'>发布</button>");
            // pushA.attr("href", rootDescUrl + resultList[i].aid);
            addClickUpdateArticleEvent(pushA);
            let chexiaoA = $("<button name='cancelArticle' id='0' class='btn btn-danger btn-sm active' data-toggle='modal' role='button' aria-pressed='true'>撤销</button>");
            // chexiaoA.attr("href", rootProgressUrl + resultList[i].aid);
            addClickUpdateArticleEvent(chexiaoA);
            // 更新或删除
            let updateA = $("<a class='btn btn-info btn-sm active ml-1' name='updateArticle' role='button' aria-pressed='true'>更新</a>");
            updateA.attr("href", rootUpdateArticle + resultList[i].aid);
            let deleteA = $("<button class='btn btn-warning btn-sm active ml-1' name='deleteArticle' id='2' data-toggle='modal' role='button' aria-pressed='true'>删除</button>");
            // deleteA.attr("href", rootProgressUrl + resultList[i].aid);
            addClickUpdateArticleEvent(deleteA);
            if (resultList[i].status==0){
                caoZouDiv.append(pushA);
            }else if (resultList[i].status==1){
                caoZouDiv.append(chexiaoA);
            }
            caoZouDiv.append(updateA);
            caoZouDiv.append(deleteA);
            optionTd.append(caoZouDiv);
            tr.append(optionTd);
            tbody.append(tr);
        }
    }





});