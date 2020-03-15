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
        let data = JSON.stringify({"currentPage": currentPageNum, "pageSize": pageSize});
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
        let rootProgressUrl = $.projectRootUrl() + "/managerController/manageIndex/";
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
            let pushA = $("<a class='btn btn-success btn-sm active' role='button' aria-pressed='true'>发布</a>");
            pushA.attr("href", rootDescUrl + resultList[i].aid);
            let chexiaoA = $("<a class='btn btn-danger btn-sm active' role='button' aria-pressed='true'>撤销</a>");
            chexiaoA.attr("href", rootProgressUrl + resultList[i].aid);
            // 更新或删除
            let updateA = $("<a class='btn btn-info btn-sm active ml-1' role='button' aria-pressed='true'>更新</a>");
            updateA.attr("href", rootDescUrl + resultList[i].aid);
            let deleteA = $("<a class='btn btn-warning btn-sm active ml-1' role='button' aria-pressed='true'>删除</a>");
            deleteA.attr("href", rootProgressUrl + resultList[i].aid);

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