export default {
  install: function (Vue, options) {
    let cookie = {
      set: function (key, value, times) {
        if (typeof value === 'object') {
          value = JSON.stringify(value)
        }
        let expired = times || 24 * 60 * 60 * 1000;
        let exp = new Date();
        exp.setTime(exp.getTime() + expired);
        document.cookie = key + "=" + value + ";expires=" + exp.toGMTString();
      },
      get: function (key) {
        let arr, reg = new RegExp("(^| )" + key + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg)) {
          let tempValue = arr[2]
          if (!tempValue.match("^\{(.+:.+,*){1,}\}$")) {
            return tempValue
          }
          return JSON.parse(tempValue)
        } else {
          return null;
        }
      },
      delete: function (key) {
        let exp = new Date();
        exp.setTime(exp.getTime() - 1);
        let cval = this.get(key);
        if (cval != null) {
          document.cookie = key + "=" + cval + ";expires=" + exp.toGMTString();
        }
      }
    };

    Vue.prototype.$cookie = cookie;
  }
}
