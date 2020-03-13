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
    }
});