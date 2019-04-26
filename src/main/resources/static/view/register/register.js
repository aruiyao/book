var app = angular.module("registerApp", []);
app.controller("registerCtrl", function ($scope, $rootScope, $timeout) {

  $timeout(function () {
    /**
     * https://www.cnblogs.com/v-weiwang/p/4834672.html?ptvd
     */
    $('#register_form').bootstrapValidator({
      message: 'This value is not valid',
      feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
      },
      fields: {
        userName: {
          validators: {
            notEmpty: {
              message: '用户名不能为空'
            },       
            remote: { 
              url: '/book/checkUser', //验证地址
              message: '用户名已存在', //提示消息
              delay: 1000, 
              type: 'GET', //请求方式
              data: function (validator) {
                return {
                  userName: $scope.userName,
                };
              }
            },
          }
        },
        password: {
          validators: {
            notEmpty: {
              message: '密码不能为空'
            },
            stringLength: {
              min: 6,
              max: 15,
              message: '密码长度必须在6到15之间'
            },
          }
        },
        repassword: {
          validators: {
            identical: {
              field: 'password',
              message: '两次输入的密码不一致'
            },
            notEmpty: {
              message: '密码不能为空'
            }
          }
        }
      }
    })
  })
  $scope.register = function () {
    var bootstrapValidator = $("#register_form").data('bootstrapValidator');
    //手动触发验证
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
      var req = {
        userName: $scope.userName,
        password: $scope.password
      };
      $.ajax({
        url: "/book/register",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(req),
        // dataType: "json",
        success: function (data) {
          $rootScope.$apply(function () {
            window.location.href = "../index/index.html"
          })
        },
        error: function (data) {

        }
      })
    }
  }
  $scope.cancle = function () {
    window.location.href = "../index/index.html"
  }
})