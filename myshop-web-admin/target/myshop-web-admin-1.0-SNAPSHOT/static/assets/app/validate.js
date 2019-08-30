/**
 * 函数对象
 */
var Validate=function () {
    /**
     * 自定义手机号码验证器
     */
    var handlerInitCustomValidate = function () {
        $.validator.addMethod("mobile", function (value, element) {
            var length = value.length;
            var phone = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length == 11 && phone.test(value));
        }, "手机号码格式错误");
    };
    /**
     *初始化被验证的对象
     */
    var handlerInitValidate = function () {
       $("#inputForm").validate({
           errorElement: 'span',
           errorClass:'help-block',
           errorPlacement: function (error, element) {
               element.parent().parent().attr("class", "form-group has-error");
               error.insertAfter(element);
           }
       })
    }
    /**
     * 初始化
     */
    return {
        init:function () {
            handlerInitCustomValidate();
            handlerInitValidate();
        }
    }
}();
//初始化Validate
$(document).ready(function () {
    Validate.init();
});

