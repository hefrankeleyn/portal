$(document).ready(function(){
    init();

    /**
     * 初始化
     */
    function init() {

        // 监听所有的channelItem
        $(".container .nav-scroller .navbar .collapse .channelItem").unbind("click",findArticleListByChannel);
        $(".container .nav-scroller .navbar .collapse .channelItem").bind("click",findArticleListByChannel);

        // 监听搜索按钮
        $(".container #searchForm #searchSubmitButton").unbind("click", searchArticleList);
        $(".container #searchForm #searchSubmitButton").bind("click", searchArticleList);

    }

    /**
     * 搜索文章内容
     */
    function searchArticleList() {
        // 获取文件标题
         let titleName = $(this).siblings("input[name='titleName']").val();
         if (titleName==null || String(titleName).trim().length==0) return;
        // 只查询已经发布的作品
        let data = JSON.stringify({"titleName":String(titleName).trim(), status:[1]});
        // 获取 token
        let token = $(".container #hiddenCsrf").val();
        let headers = {"X-CSRF-TOKEN": token};
        // 获取项目路径
        let url = $.projectRootUrl() + "/channelController/findArticleListByCondition";
        ajaxFindArticleList(headers, data, url)
        // 将搜索框的内容清空
        $(this).siblings("input[name='titleName']").val("");
    }

    /**
     * 查询文件列表
     */
    function findArticleListByChannel() {
        //改变颜色
        addOrRemoveActiveClass($(this));
        // cid
        let cidValue = $(this).children(".channelTitle").attr("id");
        // 只查询已经发布的作品
        let data = JSON.stringify({"cid":cidValue, status:[1]});
        // 获取 token
        let token = $(".container #hiddenCsrf").val();
        let headers = {"X-CSRF-TOKEN": token};
        // 获取项目路径
        let url = $.projectRootUrl() + "/channelController/findArticleListByCondition";
        ajaxFindArticleList(headers, data, url)
    }

    /**
     * 分页查询 ajax
     * @param headers
     * @param data
     * @param url
     */
    function ajaxFindArticleList(headers, data, url) {
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
                    reLoadArticleList(result);
                } else {
                    console.error("Ajax Exception: " + url);
                }
            }
        });
    }

    /**
     * 判断属性是否存在
     * @param element
     * @param attrName
     */
    function attrExists(element,attrName) {
        let attrObj = $(element).attr(attrName);
        if (typeof attrObj != typeof undefined && attrObj!=null){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 移除阅读列表
     */
    function deleteArticleListEl() {
        let articleListDivEl = $(".container #articleListDiv");
        if($(articleListDivEl).children("#articleList")){
            $(articleListDivEl).children("#articleList").remove();
        }
    }

    /**
     * 刷新articleList
     * @param articleList
     */
    function reLoadArticleList(articleList) {
        // 查看隐藏元素
        let emptyEl = $(".container #articleListDiv #emptyArticleList");
        // 删除阅读列表，重新更新
        deleteArticleListEl();
        // 判断准备更新的列表是否为空
        if (articleList==null || articleList.length==0) {
            if (attrExists($(emptyEl),"hidden")){
                $(emptyEl).removeAttr("hidden");
            }
            return;
        }
        let showArticleUrl = $.projectRootUrl() + "/articleController/showArticleContent/";
        let articleListEl = $("<div class='row' id='articleList'></div>");
        for (let articleIndex=0;articleIndex<articleList.length;articleIndex++){
            let article = articleList[articleIndex];
            let colEl = $("<div class='col-md-3'></div>");
            let articleA = $("<a target='_blank'></a>").attr("href", showArticleUrl+article.aid).text(article.contentTitle);
            colEl.append(articleA);
            articleListEl.append(colEl);
        }
        $(".container #articleListDiv").append(articleListEl);
        //判断是否有hidden属性，没有的话，添加hidden
        if (!attrExists($(emptyEl),"hidden")){
            $(emptyEl).attr("hidden","hidden");
        }
    }

    /**
     * 添加或移除active class
     */
    function addOrRemoveActiveClass(liElement) {
        // 判断里面是否包含class： active
        if (!$(liElement).hasClass("active")) {
            // 如果不包含active 的class ，添加active，并移除其他元素中的active
            let siblingsList = $(liElement).siblings("li");
            $.each(siblingsList, function (i, item) {
                removeClass(item,"active");
            });
            let headPageLink = $(".container .nav-scroller .navbar #headPageLink");
            removeClass(headPageLink, "active");
            // 添加 active
            $(liElement).addClass("active");

        }
    }

    /**
     * 添加className
     * @param element
     * @param className
     */
    function addClassName(element, className) {
        if (!($(element).hasClass(className))) {
            $(element).addClass(className);
        }
    }

    /**
     * 移除className
     * @param element
     * @param className
     */
    function removeClass(element, className) {
        if ($(element).hasClass(className)) {
            $(element).removeClass(className);
        }
    }
});