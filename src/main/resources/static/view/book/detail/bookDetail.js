var app = angular.module("bookDetailApp", []);
app.controller("bookDetailCtrl", function ($scope, $rootScope, $location, $timeout) {
  $timeout(function () {
    /**
     * https://www.cnblogs.com/v-weiwang/p/4834672.html?ptvd
     */
    $('#form_comment').bootstrapValidator({
      message: 'This value is not valid',
      feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
      },
      fields: {
        review: {
          validators: {
            notEmpty: {
              message: '评论不能为空'
            }
          }
        }
      }
    })
  })

  $scope.init = function () {
    $scope.total = 10;
    $scope.score = 10;
    $scope.bookID = $location.search().bookID;
    $scope.downUrl = "/book/uploadDownload/downloadImage";
    $scope.hotList = [{
      id: "1",
      name: "小说"
    }, {
      id: "2",
      name: "随笔"
    }, {
      id: "3",
      name: "散文"
    }, {
      id: "4",
      name: "日本文学"
    }, {
      id: "5",
      name: "童话"
    }, {
      id: "6",
      name: "诗歌"
    }, {
      id: "7",
      name: "名著"
    }, {
      id: "8",
      name: "港台"
    }, {
      id: "9",
      name: "漫画"
    }, {
      id: "10",
      name: "绘本"
    }, {
      id: "11",
      name: "推理"
    }, {
      id: "12",
      name: "青春"
    }, {
      id: "13",
      name: "言情"
    }]
    $scope.getBookInfo();
    $scope.getReviewList();
  }
  $('#comment').on('hide.bs.modal', function () {
    $('#form_comment').bootstrapValidator('resetForm');
  });
  $scope.getBookInfo = function () {
    var req = {
      id: $scope.bookID
    }
    $.ajax({
      url: "/book/queryBookByCond",
      type: "GET",
      contentType: "application/json",
      data: req,
      success: function (data) {
        $rootScope.$apply(function () {
          $scope.book = data.bookList[0];
        })
      },
      error: function (data) {

      }
    })
  }

  $scope.chooseTag = function (type, name) {
    window.location.href = "../list/bookList.html?type=" + type + "&tagName=" + name
  }
  $scope.getReviewList = function () {
    var req = {
      book: {
        id: $scope.bookID
      },
      user: {}
    }
    $.ajax({
      url: "/book/queryReviewByCond",
      type: "post",
      contentType: "application/json",
      data: JSON.stringify(req),
      success: function (data) {
        $rootScope.$apply(function () {
          $scope.total = data.total;
          $scope.reviewList = data.reviewList;
        })
      },
      error: function (data) {

      }
    })
  }

  $scope.$watch('score', function () {
    if ($scope.score > 10) {
      $scope.score = 10
    }
    if ($scope.score < 2) {
      $scope.score = 1
    }
  })
  $scope.dateTimeFormate = function () {
    var obj = {};
    var d = new Date();
    obj.year = d.getFullYear();
    obj.month = ('0' + (d.getMonth() + 1)).slice(-2);
    obj.day = ('0' + (d.getDate())).slice(-2);
    obj.hour = ('0' + (d.getHours())).slice(-2);
    obj.minutes = ('0' + (d.getMinutes())).slice(-2);
    obj.seconds = ('0' + (d.getSeconds())).slice(-2);
    return obj
  }
  $scope.submit = function () {
    var bootstrapValidator = $("#form_comment").data('bootstrapValidator');
    //手动触发验证
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
      var date = $scope.dateTimeFormate();
      var req = {
        userId: $scope.userId,
        userName: $scope.userName,
        bookId: $scope.bookID,
        bookName: $scope.book.name,
        score: $scope.score,
        review: $scope.review,
        createTime: date.year + date.month + date.day + date.hour + date.minutes + date.seconds,
      }
      $.ajax({
        url: "/book/addReview",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(req),
        success: function (data) {
          $rootScope.$apply(function () {
            $scope.review = "";
            $scope.score = 10;
            $scope.getReviewList();
            $scope.getBookInfo();
            $('#comment').modal("hide");
          })
        },
        error: function (data) {
          $("#comment").modal('hide');
        }
      })
    }
  }

  $scope.showAdd = function () {
    $.ajax({
      url: "/book/getUserInfo",
      type: "GET",
      contentType: "application/json",
      // dataType: "json",
      success: function (data) {
        $rootScope.$apply(function () {
          $scope.userName = data.userName
          $scope.userId = data.id
          if ($scope.userName !== null && $scope.userName !== '') {
            $('#comment').modal();
          } else {
            top.location.href = "/book/view/login/login.html";
          }
        })
      },
      error: function (data) {

      }
    })
  }

})

app.config(['$locationProvider', function ($locationProvider) {
  $locationProvider.html5Mode({
    enabled: true,
    requireBase: false
  });
}]);

app.filter("newdate", function () {
  return function (date) {
    return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " " + date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12)
  }
})
app.filter("numfilter", function () {
  return function (data) {
    var score = data + ""
    if (data && score.indexOf(".") === -1) {
      return score + ".0"
    }else{
      return data;
    }
  }
})