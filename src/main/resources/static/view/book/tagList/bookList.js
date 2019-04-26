var app = angular.module("bookListApp", []);
app.controller("bookListCtrl", function ($scope, $rootScope, $location) {
  $scope.imgName = "";
  $scope.init = function () {
    $scope.hotList = ["小说", "随笔", "散文", "日本文学", "童话", "诗歌", "名著", "港台", "漫画", "绘本", "推理", "青春", "言情", ]
    $scope.downUrl = "/book/uploadDownload/downloadImage"
    $scope.getBookList();
  }

  $scope.getBookList = function () {
    var req = {
      type: ""
    }
    $.ajax({
      url: "/book/queryBookByCond",
      type: "GET",
      contentType: "application/json",
      data: req,
      success: function (data) {
        $rootScope.$apply(function () {
          $scope.bookList = data.bookList;
        })
      },
      error: function (data) {

      }
    })
  }
  $scope.gotoDetail = function (id) {
    window.location.href = "../detail/bookDetail.html?bookID=" + id
  }
})
app.config(['$locationProvider', function ($locationProvider) {
  $locationProvider.html5Mode({
    enabled: true,
    requireBase: false
  });
}]);
app.directive('repeatFinish', function () {
  return {
    link: function (scope, element, attr) {
      if (scope.$last == true) {
        scope.$emit('renderFinish');
      }
    }
  }
})