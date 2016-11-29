Vue.filter('reverse', function (value) {
    return value.split('').reverse().join('')
});

/*
 * 抽取关键字
 * */
Vue.filter('extract', function (value, keyToExtract) {
    return value.map(function (item) {
        return item[keyToExtract];
    });
});


Vue.filter('moment', function (value, formatString) {
    formatString = formatString || 'YYYY-MM-DD HH:mm:ss';
    return moment(value).format(formatString);
});