$(document).ready(function(){

    init();

    function init() {

        // 为li添加点击事件
        channelListClick();
    }
    
    function channelListClick() {
        let channels = $("#channelsUl").children("li");
        for(let i=0;i<channels.length;i++){
            $(channels[i]).unbind("click",changeContent);
            $(channels[i]).bind("click",changeContent);
        }
    }


    function changeContent() {
        // 将当前的li变颜色
        activeEvenOfLi($(this));
        // 请求数据

        let cid = $(this).attr("id");
        let url = $.projectRootUrl() + "/managerController/findChannelByCid/"+cid;
        findChannelByCid(url)
    }


    function findChannelByCid(url) {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (response) {
                // 结果的状态
                let status = response.status;
                if (status == 0) {
                    let result = response.result;
                    updateChannelDetailInfo(result);
                } else {
                    console.error("Ajax Exception: " + url);
                }
            }
        });
    }

    /**
     * 改变详情的内容
     * @param data
     */
    function updateChannelDetailInfo(data) {
        $("#channelDetail #detailHead").text(data.channelName);
        $("#channelDetail #detailDesc").text(data.channelDes);
        let url = $.projectRootUrl() + "/managerController/updateChannelView/"+data.cid;
        $("#channelDetail #updateChannel").attr("href",url);

        $("#channelDetail #deleteChannelLabel").text('删除《'+data.channelName+'》');
        $("#channelDetail #deleteChannelModal .modal-body").text('将删除栏目《'+data.channelName+'》,是否继续?');
        let deleteUrl = $.projectRootUrl() + "/managerController/deleteChannel/"+data.cid;
        $("#channelDetail #deleteChannelModal #deleteChannelLink").attr("href",deleteUrl);
    }

    /**
     * li 的点击事件
     */
    function activeEvenOfLi(element) {
        let siblingsLi = $(element).siblings("li");
        for (let i=0;i<siblingsLi.length;i++){
            removeClass($(siblingsLi[i]),"active");
        }
        addClassName($(element),"active");
    }

    /**
     * 添加className
     * @param element
     * @param className
     */
    function addClassName(element,className) {
        if(!($(element).hasClass(className))){
            $(element).addClass(className);
        }
    }

    /**
     * 移除className
     * @param element
     * @param className
     */
    function removeClass(element,className) {
        if($(element).hasClass(className)){
            $(element).removeClass(className);
        }
    }

});