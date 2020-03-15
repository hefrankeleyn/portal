(function ($) {

    $.projectRootUrl = function () {
        // 获取网址
        var wwwPath = window.document.location.href;
        // 获取主机之后的目录，
        var pathname = window.document.location.pathname;
        var pathnameIndex = wwwPath.indexOf(pathname);
        // 获取主机地址 http://localhost:8080
        var hostPath = wwwPath.substr(0,pathnameIndex);
        //获取项目名称
        var projectName = pathname.substr(0, pathname.substr(1).indexOf("/")+1);
        //
        return hostPath + projectName;
    }
})($);