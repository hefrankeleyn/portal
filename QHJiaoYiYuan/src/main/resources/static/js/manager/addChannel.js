$(document).ready(function () {

    init();

    function init() {

        // 验证表单
        $("#submitChannelButton").unbind("click", validForm);
        $("#submitChannelButton").bind("click", validForm);
    }

    /**
     * 验证表单
     */
    function validForm(e) {
        // 获取 token
        let token = $(".container form input[name='_csrf']").val();
        let channelName = $("#channelName").val();
        let headers = {"X-CSRF-TOKEN": token};
        let data = JSON.stringify({"channelName": channelName});
        let url = $.projectRootUrl() + "/managerController/findChannelByChannelName";
        let channel = findChannelByName(headers, data, url);
        console.log(channel);
        if (channel != null && channel.cid != null) {
            // 阻止表单提交
            // e.preventDefault();
            let channelName = $("#channelName");
            $(channelName).siblings(".invalid-feedback").text("栏目已经存在！");
            addClassName(channelName, "is-invalid");
            $(channelName).unbind("change", inputChange);
            $(channelName).bind("change", inputChange);
            return false;
        }
        return true;
    }

    /**
     * input改变事件
     */
    function inputChange(element) {
        removeClass($(this), "is-invalid");
        $(this).siblings(".invalid-feedback").text("请输入栏目名称！");
    }

    function findChannelByName(headers, data, url) {
        let channel = null;
        $.ajaxSetup({
            contentType: 'application/json'
        });

        $.ajax({
            type: "POST",
            dataTypes: "json",
            contentType: "application/json",
            async: false,
            url: url,
            data: data,
            headers: headers,
            success: function (response) {
                // 结果的状态
                channel = response.result;
            }
        });
        return channel;
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