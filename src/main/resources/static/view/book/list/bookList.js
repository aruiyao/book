var app = angular.module("bookListApp", []);
app.controller("bookListCtrl", function ($scope, $rootScope, $location) {
  $scope.imgName = "";
  $scope.init = function () {
    $scope.type =  $location.$$search.type;
    $scope.tagName = $location.$$search.tagName;
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
    $scope.downUrl = "/book/uploadDownload/downloadImage"
    $scope.getBookList($scope.type);
  }
  $scope.chooseTag = function (id, name) {
    $scope.type = id;
    $scope.tagName = name;
    $scope.getBookList($scope.type);
  }
  $scope.getBookList = function (type) {
    var req = {
      type: type
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