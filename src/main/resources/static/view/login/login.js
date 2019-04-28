var app = angular.module("loginApp", []);
app.controller("loginCtrl", function ($scope, $rootScope, $timeout) {
  $timeout(function () {
    /**
     * https://www.cnblogs.com/v-weiwang/p/4834672.html?ptvd
     */
    $('#login_form').bootstrapValidator({
      message: 'This value is not valid',
      feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
      },
      submitButtons: '#login-submit',
      fields: {
        userName: {
          validators: {
            notEmpty: {
              message: '用户名不能为空'
            }
          }
        },
        password: {
          validators: {
            notEmpty: {
              message: '密码不能为空'
            }
          }
        }
      }
    })
  })
  $scope.init = function () {

  };

  $scope.enterLogin = function (e) {
    var keycode = window.event?e.keyCode:e.which;
    if (keycode == 13) {
      $scope.login()
    }
  }

  $scope.login = function () {
    var bootstrapValidator = $("#login_form").data('bootstrapValidator');
    //手动触发验证
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
      if ($scope.userName == '101') {
        $scope.error = "用户名或密码错误";
        return;
      }
      var req = {
        user: {
          userName: $scope.userName,
          password: $scope.password
        }
      };
      $.ajax({
        url: "/book/login",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(req),
        dataType: "json",
        success: function (data) {
          $rootScope.$apply(function () {
            if (data.id !== null) {
              $scope.error = "";
              window.location.href = "../index/index.html"
            } else {
              $scope.error = "用户名或密码错误";
            }
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