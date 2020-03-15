(function($){
    $.date = function(dateObject) {
        console.log(dateObject);
        let d = new Date(dateObject);
        let day = d.getDate();
        let month = d.getMonth() + 1;
        let year = d.getFullYear();
        let hour = d.getHours();
        let minute = d.getMinutes();
        let second = d.getSeconds();
        if (day < 10) {
            day = "0" + day;
        }
        if (month < 10) {
            month = "0" + month;
        }
        if (hour<10){
            hour = "0" + hour;
        }
        if (minute<10){
            minute = "0" + minute;
        }

        if (second<10){
            second = "0" + second;
        }
        // let date = year + "-" + month + "-" + day;
        let date = year + "-" + month + "-" + day +" " + hour+":" + minute+":" +second;
        console.log("hour: " + hour);
        return date;
    };
})($);